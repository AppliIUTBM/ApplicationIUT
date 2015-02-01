package com.iutbm.applicationiut.edt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.iutbm.applicationiut.R;

import java.util.ArrayList;


public class Receiver extends BroadcastReceiver {

    private RefreshUI uiToRefresh;

    public Receiver(RefreshUI uiToRefresh) {
        this.uiToRefresh = uiToRefresh;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String keyResult = context.getResources().getString(R.string.key_result);
        ArrayList<String> result = intent.getStringArrayListExtra(keyResult);
        uiToRefresh.refreshUI(result);
    }

}
