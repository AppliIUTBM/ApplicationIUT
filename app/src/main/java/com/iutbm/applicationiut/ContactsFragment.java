package com.iutbm.applicationiut;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by romain on 05/02/15.
 */
public class ContactsFragment extends Fragment {

    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.listView = (ListView) view.findViewById(R.id.list_view);
        this.listAdapter = new ContactsListAdapter(getActivity());

        this.listView.setAdapter(this.listAdapter);
    }
}
