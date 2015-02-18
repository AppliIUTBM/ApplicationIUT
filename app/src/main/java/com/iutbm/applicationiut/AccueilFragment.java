package com.iutbm.applicationiut;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iutbm.applicationiut.edt.Backup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccueilFragment extends Fragment {
    private Button buttonMails;
    private Button buttonFormations;
    private Button buttonEcoCampus;
    private Button buttonAgenda;
    private Button buttonFacebook;
    private Button buttonIUT;
    private Button buttonUniversite;
    private LinearLayout linearLayoutEDT;
    private Animation vanish;
    TextView textView;

    Document document;
    int id = 4245;
    int semaine = 1;
    String url = "https://sedna.univ-fcomte.fr/jsp/custom/ufc/mplanif.jsp?id="+String.valueOf(id)+"&jours="+String.valueOf(semaine);
    String contener;
    String regEx = "(\\w{2}\\s\\d+\\s\\w{3}\\s[\\d\\w[-]]+\\s[\\d\\w\\s[/][-][.][']]+[(][\\w\\s[.]]+[-]\\s[\\d\\s\\w[']]+[)])";
    Matcher matcher;
    String current;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accueil, container, false);
        vanish = AnimationUtils.loadAnimation(this.getActivity(), R.anim.vanish);
        buttonMails = (Button) view.findViewById(R.id.buttonMails);
        buttonFormations = (Button) view.findViewById(R.id.buttonFormations);
        buttonEcoCampus = (Button) view.findViewById(R.id.buttonEcoCampus);
        buttonAgenda = (Button) view.findViewById(R.id.buttonAgenda);
        buttonFacebook = (Button) view.findViewById(R.id.buttonFacebook);
        buttonIUT = (Button) view.findViewById(R.id.buttonIUT);
        buttonUniversite = (Button) view.findViewById(R.id.buttonUniversite);
        textView = (TextView)view.findViewById(R.id.liveEDT);
        linearLayoutEDT = (LinearLayout) view.findViewById(R.id.linear_layout_edt);


        buttonFormations.setOnClickListener(toFormations);
        buttonEcoCampus.setOnClickListener(toEcoCampus);
        buttonIUT.setOnClickListener(toIUT);
        buttonUniversite.setOnClickListener(toUniversite);
        buttonAgenda.setOnClickListener(toAgenda);
        buttonMails.setOnClickListener(toMails);
        buttonFacebook.setOnClickListener(toFacebook);
        linearLayoutEDT.setOnClickListener(toEDT);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Backup backup = new Backup(this.getActivity());
        id = backup.readData();
        url = "https://sedna.univ-fcomte.fr/jsp/custom/ufc/mplanif.jsp?id="+String.valueOf(id)+"&jours="+String.valueOf(semaine);
        new loadPage().execute();
    }

    private class loadPage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Log.v("IUT",url);
            try {
                document = Jsoup.connect(url).get();
                contener = document.body().text();

                matcher = Pattern.compile(regEx).matcher(contener);

                while(matcher.find())
                    checkJour(matcher.group(1));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result){
            if(current==null)
                textView.setText("Vous n'avez pas cours actuellement");
            else
                textView.setText(current);
        }
    }

    public void checkJour(String line){

        Log.v("IUT",line);

        if(Character.isDigit(line.charAt(4)))
            checkHeure(line.substring(10));
        else
            checkHeure(line.substring(9));

    }

    public void checkHeure(String line){

        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH.mm");
        double heureCourante = Double.parseDouble(f.format(d));

        String sHeureDebut = "";
        int i=0;

        do{
            sHeureDebut += line.charAt(i);
            i++;
        }while(line.charAt(i)!='-');

        sHeureDebut = sHeureDebut.replace('h','.');
        double heureDebut = Double.parseDouble(sHeureDebut);

        String sHeureFin = "";
        i++;

        do{
            sHeureFin += line.charAt(i);
            i++;
        }while(line.charAt(i)!=' ');

        sHeureFin = sHeureFin.replace('h','.');
        double heureFin = Double.parseDouble(sHeureFin);

        if((heureDebut <= heureCourante)&&(heureFin > heureCourante))
            current = line;

    }

    private View.OnClickListener toEDT = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            ft.replace(R.id.container, new EDTFragment()).addToBackStack("retour9").commit();*/
            Log.v("IUT","Click");
            linearLayoutEDT.startAnimation(vanish);
            Log.v("IUT","Creation de l'intent");
            Intent myIntent = new Intent(getActivity(),EDTActivity.class);
            Log.v("IUT","Start intent");
            startActivity(myIntent);
        }
    };

    private View.OnClickListener toFormations = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonFormations.startAnimation(vanish);
            buttonFormations.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.container, new FormationsFragment()).addToBackStack("retour8").commit();
                }
            }, vanish.getDuration());
        }
    };

    private View.OnClickListener toEcoCampus = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            buttonEcoCampus.startAnimation(vanish);
            buttonEcoCampus.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.container, new EcoCampusFragment()).addToBackStack("retour9").commit();
                }
            }, vanish.getDuration());
        }
    };

    private View.OnClickListener toIUT = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonIUT.startAnimation(vanish);
            buttonIUT.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent toIut = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iut-bm.univ-fcomte.fr"));
                    startActivity(toIut);
                }
            }, vanish.getDuration());

        }
    };

    private View.OnClickListener toUniversite = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonUniversite.startAnimation(vanish);
            buttonUniversite.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent toUniversite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.univ-fcomte.fr"));
                    startActivity(toUniversite);
                }
            }, vanish.getDuration());

        }
    };


    private View.OnClickListener toAgenda = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonAgenda.startAnimation(vanish);
            buttonAgenda.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.container, new ContactsFragment()).addToBackStack("retour10").commit();
                }
            }, vanish.getDuration());
        }
    };


    private View.OnClickListener toMails = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonMails.startAnimation(vanish);
            buttonMails.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.container, new MailsReceiverFragment()).addToBackStack("retour11").commit();
                }
            }, vanish.getDuration());

        }
    };

    private View.OnClickListener toFacebook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonFacebook.startAnimation(vanish);
            buttonFacebook.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.container, new FacebookFragment()).addToBackStack("retour12").commit();
                }
            }, vanish.getDuration());

        }
    };

}