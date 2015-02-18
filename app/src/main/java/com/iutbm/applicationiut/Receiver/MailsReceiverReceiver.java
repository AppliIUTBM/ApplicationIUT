package com.iutbm.applicationiut.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.MailsReceiverInterface;
import com.iutbm.applicationiut.Mail;

import java.util.ArrayList;


public class MailsReceiverReceiver extends BroadcastReceiver {

    private MailsReceiverInterface receiverInterface;

    public MailsReceiverReceiver(MailsReceiverInterface receiverInterface) {
        this.receiverInterface = receiverInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String user = intent.getStringExtra(MailsConstants.KEY_USER);
        ArrayList<Mail> mails = (ArrayList<Mail>) intent.getSerializableExtra(MailsConstants.KEY_MAILS_RECEIVED);
        receiverInterface.displayMails(mails, user);
    }
}
