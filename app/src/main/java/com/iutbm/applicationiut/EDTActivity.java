package com.iutbm.applicationiut;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.iutbm.applicationiut.edt.Backup;
import com.iutbm.applicationiut.edt.Group;
import com.iutbm.applicationiut.edt.MyAsyncTask;
import com.iutbm.applicationiut.edt.MyExpandableListAdapter;
import com.iutbm.applicationiut.edt.MyPlanning;
import com.iutbm.applicationiut.edt.Receiver;
import com.iutbm.applicationiut.edt.Sender;

import java.util.ArrayList;


public class EDTActivity extends Activity {

    private IntentFilter filtre = null;
    private Receiver receiver = null;

    TableLayout tableLayout;

    Button lastWeek;
    Button nextWeek;

    int semaine = 7;
    int id = 4245;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edt);

        Log.v("IUT","Activity EDT Created");

        tableLayout = (TableLayout)findViewById(R.id.table_layout);

        lastWeek = (Button)findViewById(R.id.button_semaine_precedente);
        nextWeek = (Button)findViewById(R.id.button_semaine_suivante);

        lastWeek.setOnClickListener(lastWeekEvent);
        nextWeek.setOnClickListener(nextWeekEvent);

        filtre = new IntentFilter(Sender.INTENT);
        receiver = new Receiver(this);

    }

    View.OnClickListener lastWeekEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(semaine>7){
                semaine -= 7;
                new MyAsyncTask(EDTActivity.this).execute(id,semaine);
            }
            else
                Toast.makeText(getApplicationContext(), "Impossible de voir en arrière !", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener nextWeekEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            semaine += 7;
            new MyAsyncTask(EDTActivity.this).execute(id,semaine);
        }
    };

    public void afficherResultat(ArrayList<String> listPlanning){
        tableLayout.removeAllViews();
        MyPlanning myPlanning = new MyPlanning(this,tableLayout);
        if(!listPlanning.isEmpty()){
            for(String row : listPlanning)
                myPlanning.addRow(row);
        }
        else
            myPlanning.addRow("Pas cours cette semaine");
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(receiver, filtre);
        Backup backup = new Backup(this);
        id = backup.readData();
        new MyAsyncTask(EDTActivity.this).execute(id,semaine);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.calendar_action) {
            Intent intent = new Intent(this,ConfigEDTActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
