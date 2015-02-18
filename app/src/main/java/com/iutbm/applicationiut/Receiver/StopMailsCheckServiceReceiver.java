package com.iutbm.applicationiut.Receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.StopMailsCheckServiceInterface;


public class StopMailsCheckServiceReceiver extends BroadcastReceiver {

    private StopMailsCheckServiceInterface stopServiceInterface;

    public StopMailsCheckServiceReceiver(StopMailsCheckServiceInterface stopServiceInterface){
        this.stopServiceInterface = stopServiceInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean stopService = intent.getBooleanExtra(MailsConstants.KEY_STOP_SERVICE, false);
        stopServiceInterface.stopService(stopService);
    }
}
