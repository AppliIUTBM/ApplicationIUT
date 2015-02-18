package com.iutbm.applicationiut.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Mail;

import java.util.Collections;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;


public class MailsCheckTimerTask extends TimerTask {

    private String user;
    private String password;
    private Context context;

    public MailsCheckTimerTask(String user, String password, Context context) {
        this.user = user;
        this.password = password;
        this.context = context;
    }

    @Override
    public void run() {

        int nbMails = 0;
        Mail lastMail = null;

        try{
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", MailsConstants.PROTOCOL);

            Session emailSession = Session.getDefaultInstance(properties);

            Store emailStore = emailSession.getStore();
            emailStore.connect(MailsConstants.HOST, user, password);

            Folder inbox = emailStore.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            nbMails = messages.length;

            if(nbMails>0)
                lastMail = new Mail(messages[nbMails-1]);

            inbox.close(false);
            emailStore.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Log.v("MailsCheckTimerTask","nbMails : "+String.valueOf(nbMails));
        Log.v("MailsCheckTimerTask","lastSubject : "+lastMail.getSubject());
        Log.v("MailsCheckTimerTask","lastSender : "+lastMail.getSender());

        Intent intent = new Intent(MailsConstants.ACTION_CHECK);
        intent.putExtra(MailsConstants.KEY_NUMBER_MAILS, nbMails);
        intent.putExtra(MailsConstants.KEY_LAST_MAIL, lastMail);
        context.sendBroadcast(intent);

    }
}
