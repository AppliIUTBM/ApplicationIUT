package com.iutbm.applicationiut;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iutbm.applicationiut.edt.EDTPageAdapter;

public class EDTFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        EDTPageAdapter adapter = new EDTPageAdapter(getFragmentManager(),getActivity());
        viewPager.setAdapter(adapter);
    }
}
