package com.iutbm.applicationiut.AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.iutbm.applicationiut.Constants.MailsConstants;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;


public class MailsConnectAsyncTask extends AsyncTask<Void,Void,Boolean> {

    private String user;
    private String password;
    private Context context;

    public MailsConnectAsyncTask(String user, String password, Context context){
        this.user = user;
        this.password = password;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        boolean isConnected = false;

        try{

            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", MailsConstants.PROTOCOL);

            Session emailSession = Session.getDefaultInstance(properties);

            Store emailStore = emailSession.getStore();
            emailStore.connect(MailsConstants.HOST, user, password);
            isConnected = emailStore.isConnected();

            emailStore.close();

        } catch (NoSuchProviderException e) {
            Log.v("Mail", e.getMessage());
        } catch (MessagingException e) {
            Log.v("Mail", e.getMessage());
        }

        return isConnected;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        Intent intent = new Intent(MailsConstants.ACTION_CONNECT);
        intent.putExtra(MailsConstants.KEY_IS_CONNECTED, aBoolean);
        intent.putExtra(MailsConstants.KEY_USER, user);
        intent.putExtra(MailsConstants.KEY_PASSWORD, password);
        context.sendBroadcast(intent);
    }
}
