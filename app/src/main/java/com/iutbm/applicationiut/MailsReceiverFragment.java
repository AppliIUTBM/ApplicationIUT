package com.iutbm.applicationiut;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iutbm.applicationiut.AsyncTask.MailsReceiverAsyncTask;
import com.iutbm.applicationiut.Constants.MailsConstants;
import com.iutbm.applicationiut.Interface.MailsReceiverInterface;
import com.iutbm.applicationiut.Receiver.MailsReceiverReceiver;
import com.iutbm.applicationiut.adapter.MailsReceiverAdapter;

import java.util.ArrayList;

public class MailsReceiverFragment extends Fragment implements MailsReceiverInterface {

    private View view;

    private MailsReceiverReceiver receiver;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mails_receiver, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        receiver = new MailsReceiverReceiver(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(MailsConstants.ACTION_RECEIVE);
        getActivity().registerReceiver(receiver, filter);

        boolean userIsRegistered = true;

        SharedPreferences sharedPref = getActivity().getSharedPreferences(MailsConstants.KEY_PREFERENCES, Context.MODE_PRIVATE);
        String user = sharedPref.getString(MailsConstants.KEY_USER, "");
        String password = sharedPref.getString(MailsConstants.KEY_PASSWORD, "");

        if(user.isEmpty())
            userIsRegistered = false;

        if(password.isEmpty())
            userIsRegistered = false;

        if(userIsRegistered){
            MailsReceiverAsyncTask asyncTaskReceiver = new MailsReceiverAsyncTask(user, password, getActivity());
            asyncTaskReceiver.execute();

            progressDialog = ProgressDialog.show(getActivity(),"Veuillez patienter","Récupération de vos mails",true);
        } else
            startActivity(new Intent(getActivity(), MailsConnectActivity.class));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void displayMails(ArrayList<Mail> mails, String user) {
        progressDialog.dismiss();
        ListView listViewMails = (ListView) view.findViewById(R.id.lv_mails);
        MailsReceiverAdapter adapter = new MailsReceiverAdapter(mails, getActivity());
        listViewMails.setAdapter(adapter);
    }
}
