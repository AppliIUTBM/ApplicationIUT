package com.iutbm.applicationiut.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.MailsConnectInterface;


public class MailsConnectReceiver extends BroadcastReceiver {

    private MailsConnectInterface connectInterface;

    public MailsConnectReceiver(MailsConnectInterface connectInterface){
        this.connectInterface = connectInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = intent.getBooleanExtra(MailsConstants.KEY_IS_CONNECTED, false);
        String user = intent.getStringExtra(MailsConstants.KEY_USER);
        String password = intent.getStringExtra(MailsConstants.KEY_PASSWORD);
        connectInterface.checkIfUserConnected(isConnected, user, password);
    }
}
