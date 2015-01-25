package com.iutbm.applicationiut.edt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyAsyncTask extends AsyncTask<Integer,Void,Void> {

    private Context context;

    public MyAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Integer... params) {

        String id = String.valueOf(params[0]);
        String semaine = String.valueOf(params[1]);

        String url = "https://sedna.univ-fcomte.fr/jsp/custom/ufc/mplanif.jsp?id="+id+"&jours="+semaine;
        String regEx = "(\\w{2}\\s\\d+\\s\\w{3}\\s[\\d\\w[-]]+\\s[\\d\\w\\s[/][-][.][']]+[(][\\w\\s[.]]+[-]\\s[\\d\\s\\w[']]+[)])";

        try {
            Document document = Jsoup.connect(url).get();
            String contener = document.body().text();

            Matcher matcher = Pattern.compile(regEx).matcher(contener);

            ArrayList<String> listePlanning = new ArrayList<String>();

            while(matcher.find())
                listePlanning.add(matcher.group(1));

            Sender.sendMessage(this.context, listePlanning);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}