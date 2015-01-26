package com.iutbm.applicationiut.edt;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by romain on 22/12/2014.
 */
public class Sender {

    public static final String EXTRA = "com.example.romain.EXTRA";
    public static final String INTENT = "com.example.romain.INTENT";

    public static void sendMessage(Context context, ArrayList<String> listPlanning){
        Intent intent = new Intent(INTENT);
        intent.putExtra(EXTRA, listPlanning);
        context.sendBroadcast(intent);
    }

}
