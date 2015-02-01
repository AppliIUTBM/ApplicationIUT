package com.iutbm.applicationiut.edt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import com.iutbm.applicationiut.R;

public class EDTPageAdapter extends FragmentPagerAdapter {

    private Context context;

    public EDTPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new SwitchFragment();
        Bundle args = new Bundle();
        args.putInt(context.getResources().getString(R.string.key_fragment), i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
