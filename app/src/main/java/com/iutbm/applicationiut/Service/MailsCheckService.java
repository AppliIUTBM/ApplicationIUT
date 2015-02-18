package com.iutbm.applicationiut.Service;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.MailsCheckInterface;
import com.iutbm.applicationiut.Interface.StopMailsCheckServiceInterface;
import com.iutbm.applicationiut.Mail;
import com.iutbm.applicationiut.Receiver.MailsCheckReceiver;
import com.iutbm.applicationiut.Receiver.StopMailsCheckServiceReceiver;
import com.iutbm.applicationiut.TimerTask.MailsCheckTimerTask;
import com.iutbm.applicationiut.R;

import java.util.Timer;

public class MailsCheckService extends Service implements MailsCheckInterface, StopMailsCheckServiceInterface {

    private Timer timer;
    private MailsCheckReceiver receiver;
    private StopMailsCheckServiceReceiver receiverToStop;

    private int currentNbMails = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MailsCheckService","service started !");
        receiver = new MailsCheckReceiver(this);
        receiverToStop = new StopMailsCheckServiceReceiver(this);
        IntentFilter filter = new IntentFilter(MailsConstants.ACTION_CHECK);
        IntentFilter filterToStop = new IntentFilter(MailsConstants.ACTION_STOP_SERVICE);
        registerReceiver(receiver, filter);
        registerReceiver(receiverToStop, filterToStop);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boolean userIsRegistered = true;

        SharedPreferences sharedPref = getSharedPreferences(MailsConstants.KEY_PREFERENCES,Context.MODE_PRIVATE);
        String user = sharedPref.getString(MailsConstants.KEY_USER, "");
        String password = sharedPref.getString(MailsConstants.KEY_PASSWORD, "");

        if(user.isEmpty())
            userIsRegistered = false;

        if(password.isEmpty())
            userIsRegistered = false;

        if(userIsRegistered){
            timer = new Timer();
            timer.scheduleAtFixedRate(new MailsCheckTimerTask(user, password, getApplicationContext()), 0, MailsConstants.PERIOD_INTERVAL);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        unregisterReceiver(receiverToStop);
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void checkNumberMails(int nbMails, Mail lastMail) {

        Log.v("MailsCheckService","currentNbMails : "+String.valueOf(currentNbMails));
        Log.v("MailsCheckService","nbMails : "+String.valueOf(nbMails));
        Log.v("MailsCheckService","lastSubject : "+lastMail.getSubject());
        Log.v("MailsCheckService","lastSender : "+lastMail.getSender());

        if(currentNbMails == -1)
            currentNbMails = nbMails;

        if(nbMails>currentNbMails){
            int nbNewMails = nbMails - currentNbMails;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail-edu.univ-fcomte.fr"));
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext());

            notificationBuilder
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(pendingIntent);

            if(nbNewMails==1) {
                notificationBuilder
                        .setSmallIcon(R.drawable.icon_mail)
                        .setContentTitle(lastMail.getSender())
                        .setContentText(lastMail.getSubject());
            } else {
                notificationBuilder
                        .setSmallIcon(R.drawable.icon_mail)
                        .setContentTitle("Vous avez " + nbNewMails + " nouveaux mails")
                        .setContentText("Appuyez pour ouvrir");
            }

            notificationManager.notify(1, notificationBuilder.build());
        }

        if(currentNbMails != nbMails)
            currentNbMails = nbMails;

    }

    @Override
    public void stopService(boolean stopService) {
        if(stopService)
            stopSelf();
    }
}
