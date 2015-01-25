package com.iutbm.applicationiut.edt;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.iutbm.applicationiut.R;


public class MyPlanning {

    private Context context;
    private TableLayout tableLayout;

    public MyPlanning(Context context, TableLayout tableLayout){
        this.context = context;
        this.tableLayout = tableLayout;
    }

    public void addRow(String row){

        TableRow tableRow = new TableRow(this.context);
        TextView textView = new TextView(this.context);
        TableRow.LayoutParams lls = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        lls.setMargins(0,20,0,0);

        textView.setText(row);
        textView.setBackgroundDrawable(this.context.getResources().getDrawable(R.drawable.radius));
        textView.setTextColor(this.context.getResources().getColor(android.R.color.white));
        textView.setTextSize(15);
        textView.setLayoutParams(lls);

        tableRow.addView(textView);
        this.tableLayout.addView(tableRow);

    }

}
