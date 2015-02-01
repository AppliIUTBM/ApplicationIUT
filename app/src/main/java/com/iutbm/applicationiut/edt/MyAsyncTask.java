package com.iutbm.applicationiut.edt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.iutbm.applicationiut.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyAsyncTask extends AsyncTask<Void,Void,ArrayList<String>> {

    private String url;
    private String regEx;
    private String keyIntent;
    private Context context;

    public MyAsyncTask(String url, String regEx, String keyIntent, Context context) {
        this.url = url;
        this.regEx = regEx;
        this.keyIntent = keyIntent;
        this.context = context;
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {

        ArrayList<String> result = new ArrayList<String>();

        try {
            Document document = Jsoup.connect(url).get();
            String contener = document.body().text();
            Matcher matcher = Pattern.compile(regEx).matcher(contener);

            while(matcher.find())
                result.add(matcher.group(1));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list) {
        String keyResult = context.getResources().getString(R.string.key_result);
        Intent intent = new Intent(keyIntent);
        intent.putStringArrayListExtra(keyResult,list);
        context.sendBroadcast(intent);
    }
}