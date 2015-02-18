package com.iutbm.applicationiut.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iutbm.applicationiut.Mail;
import com.iutbm.applicationiut.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MailsReceiverAdapter extends BaseAdapter {

    private ArrayList<Mail> mails;
    private Context context;

    public MailsReceiverAdapter(ArrayList<Mail> mails, Context context){
        this.mails = mails;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mails.size();
    }

    @Override
    public Object getItem(int position) {
        return mails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.item_mail, null);
        TextView textViewDate = (TextView) view.findViewById(R.id.tv_date);
        TextView textViewSubject = (TextView) view.findViewById(R.id.tv_subject);
        TextView textViewSender = (TextView) view.findViewById(R.id.tv_sender);

        textViewDate.setText(mails.get(position).getDate());
        textViewSubject.setText(mails.get(position).getSubject());
        textViewSender.setText(mails.get(position).getSender());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail-edu.univ-fcomte.fr"));
                context.startActivity(intent);
            }
        });

        return view;
    }
}
