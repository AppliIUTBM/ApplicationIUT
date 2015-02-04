package com.iutbm.applicationiut;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iutbm.applicationiut.edt.Backup;
import com.iutbm.applicationiut.edt.Cours;
import com.iutbm.applicationiut.edt.MyAsyncTask;
import com.iutbm.applicationiut.edt.Receiver;
import com.iutbm.applicationiut.edt.RefreshUI;

import java.util.ArrayList;
import java.util.List;


public class EDTActivity extends Activity implements RefreshUI {

    private int pageNumber;

    private LinearLayout llEDT;
    private Button buttonSemPrec;
    private Button buttonSemSuiv;

    private String[] dataParse;
    private String keyIntent;
    private String regEx;

    private Receiver receiver;
    private IntentFilter filter;

    private List<Cours> planning;

    private int idFormation;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edt);
        llEDT = (LinearLayout) findViewById(R.id.ll_edt);
        buttonSemPrec = (Button) findViewById(R.id.bt_sem_prec);
        buttonSemSuiv = (Button) findViewById(R.id.bt_sem_suiv);
        buttonSemPrec.setOnClickListener(eventSemPrec);
        buttonSemSuiv.setOnClickListener(eventSemSuiv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        pageNumber = 1;

        dataParse = getResources().getStringArray(R.array.data_parse_edt);

        regEx = dataParse[1];
        keyIntent = String.format(dataParse[2],pageNumber);

        receiver = new Receiver(this);
        filter = new IntentFilter(keyIntent);

        registerReceiver(receiver, filter);

        Backup backup = new Backup(this);
        idFormation = backup.readData();

        url = String.format(dataParse[0],idFormation,pageNumber*7);

        if(idFormation!=0){
            MyAsyncTask asyncTask = new MyAsyncTask(url,regEx,keyIntent,this);
            asyncTask.execute();
        }
        else
            Toast.makeText(this, "Veuillez selectionner une formation dans les paramètres", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void refreshUI(ArrayList<String> result) {
        llEDT.removeAllViews();
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

                    itemEDT = View.inflate(this, R.layout.item_edt, null);
                    llCours = (LinearLayout) itemEDT.findViewById(R.id.ll_cours);
                    LinearLayout.LayoutParams lls = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lls.setMargins(margin,margin,margin,0);
                    llCours.setLayoutParams(lls);
                    TextView tvJour = (TextView) itemEDT.findViewById(R.id.tv_jour);
                    tvJour.setText(cours.getJour() + " " + String.valueOf(cours.getNumJour()) + " " + cours.getMois());
                }

                View subItemEDT = View.inflate(this,R.layout.sub_item_edt,null);
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
            itemEDT = View.inflate(this,R.layout.item_edt,null);
            TextView tvJour = (TextView) itemEDT.findViewById(R.id.tv_jour);
            tvJour.setText("Vous n'avez pas cours cette semaine");
            llEDT.addView(itemEDT);
        }
    }

    private View.OnClickListener eventSemPrec = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pageNumber*7>7){
                pageNumber--;
                url = String.format(dataParse[0],idFormation,pageNumber*7);
                MyAsyncTask asyncTask = new MyAsyncTask(url,regEx,keyIntent,getApplicationContext());
                asyncTask.execute();
            }
            else
                Toast.makeText(getApplicationContext(),"Impossible de voir en arrière !",Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener eventSemSuiv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pageNumber++;
            url = String.format(dataParse[0],idFormation,pageNumber*7);
            MyAsyncTask asyncTask = new MyAsyncTask(url,regEx,keyIntent,getApplicationContext());
            asyncTask.execute();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.calendar_action) {
            Intent intent = new Intent(this,ConfigEDTActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
