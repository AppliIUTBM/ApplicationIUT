package com.iutbm.applicationiut.Receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.MailsCheckInterface;
import com.iutbm.applicationiut.Mail;


public class MailsCheckReceiver extends BroadcastReceiver {

    private MailsCheckInterface checkInterface;

    public MailsCheckReceiver(MailsCheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int nbMails = intent.getIntExtra(MailsConstants.KEY_NUMBER_MAILS, 0);
        Mail lastMail = (Mail) intent.getSerializableExtra(MailsConstants.KEY_LAST_MAIL);
        checkInterface.checkNumberMails(nbMails, lastMail);
    }
}
