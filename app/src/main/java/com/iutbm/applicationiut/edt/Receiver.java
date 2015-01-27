package com.iutbm.applicationiut.edt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iutbm.applicationiut.EDTActivity;

import java.util.ArrayList;


public class Receiver extends BroadcastReceiver {

    EDTActivity activity;

    public Receiver(EDTActivity mainActivity){
        this.activity = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<String> listPlanning = intent.getStringArrayListExtra(Sender.EXTRA);
        this.activity.afficherResultat(listPlanning);
    }

}
