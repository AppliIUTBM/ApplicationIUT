package com.iutbm.applicationiut;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.iutbm.applicationiut.AsyncTask.MailsConnectAsyncTask;
import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.MailsConnectInterface;
import com.iutbm.applicationiut.Receiver.MailsConnectReceiver;
import com.iutbm.applicationiut.Service.MailsCheckService;


public class MailsConnectActivity extends Activity implements MailsConnectInterface {

    private MailsConnectReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mails_connect);

        Button buttonConnect = (Button) findViewById(R.id.bt_connect);
        buttonConnect.setOnClickListener(eventConnect);

        receiver = new MailsConnectReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(MailsConstants.ACTION_CONNECT);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener eventConnect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editTextMail = (EditText) findViewById(R.id.et_email);
            EditText editTextPassword = (EditText) findViewById(R.id.et_password);
            String user = null;
            String password = null;
            boolean error = false;

            if(editTextMail.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Veuillez entrer une adresse mail", Toast.LENGTH_SHORT).show();
                error = true;
            } else
                user = editTextMail.getText().toString();

            if(editTextPassword.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot de passe", Toast.LENGTH_SHORT).show();
                error = true;
            } else
                password = editTextPassword.getText().toString();

            if(!error){
                MailsConnectAsyncTask asyncTaskConnection = new MailsConnectAsyncTask(user, password, getApplicationContext());
                asyncTaskConnection.execute();
            }
        }
    };

    @Override
    public void checkIfUserConnected(boolean isConnected, String user, String password) {
        if(isConnected){
            CheckBox checkBoxRemember = (CheckBox) findViewById(R.id.cb_remember);
            if(checkBoxRemember.isChecked()){
                SharedPreferences sharedPref = getSharedPreferences(MailsConstants.KEY_PREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(MailsConstants.KEY_USER, user);
                editor.putString(MailsConstants.KEY_PASSWORD, password);
                editor.commit();
                Intent service = new Intent(this, MailsCheckService.class);
                Log.v("MailsConnectActivity","starting service ...");
                startService(service);
            }
            Toast.makeText(getApplicationContext(), String.format(getResources().getString(R.string.connected), user), Toast.LENGTH_SHORT).show();
            finish();
        } else
            Toast.makeText(getApplicationContext(), R.string.not_connected, Toast.LENGTH_SHORT).show();
    }
}
