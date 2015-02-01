package com.iutbm.applicationiut.edt;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.iutbm.applicationiut.ConfigEDTActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by romain on 07/01/2015.
 */
public class Backup {

    private final String KEY_ID = "ID_FORMATION";

    private Context context;
    private SharedPreferences myPreferences;

    public Backup(Activity mainActivity){
        this.context = mainActivity;
        this.myPreferences = mainActivity.getSharedPreferences("FORMATION_ETUDIANT",Context.MODE_PRIVATE);
    }

    public void saveData(int id){

        Log.v("IUT", "writting "+id);

        SharedPreferences.Editor editor = this.myPreferences.edit();
        editor.putInt(KEY_ID,id);
        editor.commit();
    }

    public int readData(){

        int defaultValue = 0;

        int id = this.myPreferences.getInt(KEY_ID,defaultValue);

        Log.v("IUT", id+" readed");

        return id;
    }

}
