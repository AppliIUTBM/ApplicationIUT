package com.iutbm.applicationiut.edt;

import android.app.Fragment;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iutbm.applicationiut.R;

import java.util.ArrayList;
import java.util.List;


public class SwitchFragment extends Fragment implements RefreshUI {

    private int pageNumber;

    private LinearLayout llEDT;
    private String[] dataParse;
    private String keyIntent;
    private String regEx;

    private Receiver receiver;
    private IntentFilter filter;

    private List<Cours> planning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edt, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llEDT = (LinearLayout) view.findViewById(R.id.ll_edt);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        pageNumber = args.getInt(getResources().getString(R.string.key_fragment));

        dataParse = getResources().getStringArray(R.array.data_parse_edt);

        regEx = dataParse[1];
        keyIntent = String.format(dataParse[2],pageNumber);

        receiver = new Receiver(this);
        filter = new IntentFilter(keyIntent);
    }

    @Override
    public void onResume() {
        super.onResume();

        llEDT.removeAllViews();

        getActivity().registerReceiver(receiver,filter);

        Backup backup = new Backup(getActivity());
        int idFormation = backup.readData();

        String url = String.format(dataParse[0],idFormation,pageNumber*7);

        if(idFormation!=0){
            MyAsyncTask asyncTask = new MyAsyncTask(url,regEx,keyIntent,getActivity());
            asyncTask.execute();
        }
        else
            Toast.makeText(getActivity(),"Veuillez selectionner une formation dans les paramètres",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void refreshUI(ArrayList<String> result) {
        planning = new ArrayList<>();

        for(String line : result)
            planning.add(new Cours(line));

        View itemEDT = null;
        LinearLayout llCours = null;
        int margin = (int) getResources().getDimension(R.dimen.small_padding);


        int currentNumJour = 0;

        if(!planning.isEmpty()){
            for(Cours cours : planning){

                if(currentNumJour<cours.getNumJour()) {
                    if (currentNumJour != 0) {
                        llEDT.addView(itemEDT);
                    }

                    itemEDT = View.inflate(getActivity(), R.layout.item_edt, null);
                    llCours = (LinearLayout) itemEDT.findViewById(R.id.ll_cours);
                    LinearLayout.LayoutParams lls = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lls.setMargins(margin,margin,margin,0);
                    llCours.setLayoutParams(lls);
                    TextView tvJour = (TextView) itemEDT.findViewById(R.id.tv_jour);
                    tvJour.setText(cours.getJour() + " " + String.valueOf(cours.getNumJour()) + " " + cours.getMois());
                }

                View subItemEDT = View.inflate(getActivity(),R.layout.sub_item_edt,null);
                TextView tvHeure = (TextView) subItemEDT.findViewById(R.id.tv_heure);
                TextView tvCours = (TextView) subItemEDT.findViewById(R.id.tv_cours);
                tvHeure.setText(cours.gethDebut() + " " + cours.gethFin());
                tvCours.setText(cours.getLibelle() + " " + cours.getLieu());
                llCours.addView(subItemEDT);

                currentNumJour = cours.getNumJour();

            }

            llEDT.addView(itemEDT);
        }
        else{
            itemEDT = View.inflate(getActivity(),R.layout.item_edt,null);
            TextView tvJour = (TextView) itemEDT.findViewById(R.id.tv_jour);
            tvJour.setText("Vous n'avez pas cours cette semaine");
            llEDT.addView(itemEDT);
        }

    }

}
