package com.iutbm.applicationiut;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by romain on 05/02/15.
 */
public class ContactsListAdapter extends BaseAdapter implements SectionIndexer {
    private Context context;
    private HashMap<String,Integer> alphaIndexer;
    String[] sections;
    String[] items;
    String[] numero;
    String[] mail;

    public ContactsListAdapter(Context context) {

        this.context = context;

        items = context.getResources().getStringArray(R.array.libelle);
        numero = context.getResources().getStringArray(R.array.numero);
        mail = context.getResources().getStringArray(R.array.mail);

        alphaIndexer = new HashMap<>();
        int size = items.length;

        for (int x = 0; x < size; x++) {
            String s = items[x];
            String ch = s.substring(0, 1);
            ch = ch.toUpperCase();
            if (!alphaIndexer.containsKey(ch))
                alphaIndexer.put(ch, x);
        }

        Set<String> sectionLetters = alphaIndexer.keySet();
        ArrayList<String> sectionList = new ArrayList<String>(sectionLetters);
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        sectionList.toArray(sections);

    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = View.inflate(context,R.layout.item_annuaire,null);

        TextView tvLibelle = (TextView) rootView.findViewById(R.id.libelle);
        TextView tvNumero = (TextView) rootView.findViewById(R.id.numero);
        TextView tvMail = (TextView) rootView.findViewById(R.id.mail);

        tvLibelle.setText(items[position]);
        tvNumero.setText(numero[position]);
        tvMail.setText(mail[position]);

        return rootView;

    }

    @Override
    public Object[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return alphaIndexer.get(sections[sectionIndex]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}
