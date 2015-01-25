package com.iutbm.applicationiut.edt;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.iutbm.applicationiut.ConfigEDTActivity;
import com.iutbm.applicationiut.EDTActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by romain on 07/01/2015.
 */
public class Backup {

    private Activity activity;
    private String filename;

    public Backup(Activity mainActivity){
        this.activity = mainActivity;
        this.filename = "dataUser";
    }

    public void saveData(int id){
        FileOutputStream outputStream;
        File file = new File(activity.getFilesDir(),filename);

        try {
            outputStream = activity.openFileOutput(filename,Context.MODE_PRIVATE);
            outputStream.write(String.valueOf(id).getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readData(){
        int id = 0;

        FileInputStream inputStream;
        byte[] buffer = new byte[4];

        try {
            inputStream = activity.openFileInput(filename);
            inputStream.read(buffer);
            inputStream.close();
            id = Integer.parseInt(new String(buffer,"UTF-8"));
            Log.v("IUT",String.valueOf(id));
        } catch (FileNotFoundException e) {
            Toast.makeText(this.activity,"Vous n'avez pas encore configuré votre emploi du temps",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

}
