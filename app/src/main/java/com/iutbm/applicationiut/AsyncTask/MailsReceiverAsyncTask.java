package com.iutbm.applicationiut.AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Mail;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;


public class MailsReceiverAsyncTask extends AsyncTask<Void,Void,ArrayList<Mail>> {

    private String user;
    private String password;
    private Context context;

    public MailsReceiverAsyncTask(String user, String password, Context context) {
        this.user = user;
        this.password = password;
        this.context = context;
    }

    @Override
    protected ArrayList<Mail> doInBackground(Void... params) {

        ArrayList<Mail> mails = new ArrayList<>();

        try{

            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", MailsConstants.PROTOCOL);

            Session emailSession = Session.getDefaultInstance(properties);

            Store emailStore = emailSession.getStore();
            emailStore.connect(MailsConstants.HOST, user, password);

            Folder inbox = emailStore.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            ArrayUtils.reverse(messages);

            int nbMails = messages.length;

            if(nbMails>20)
                nbMails = 20;

            for(int i=0 ; i<nbMails ; i++)
                mails.add(new Mail(messages[i]));

            inbox.close(false);
            emailStore.close();

        } catch (NoSuchProviderException e) {
            Log.v("Mail", e.getMessage());
        } catch (MessagingException e) {
            Log.v("Mail", e.getMessage());
        }

        return mails;
    }

    @Override
    protected void onPostExecute(ArrayList<Mail> mails) {
        Intent intent = new Intent(MailsConstants.ACTION_RECEIVE);
        intent.putExtra(MailsConstants.KEY_USER, user);
        intent.putExtra(MailsConstants.KEY_MAILS_RECEIVED, mails);
        context.sendBroadcast(intent);
    }

}
