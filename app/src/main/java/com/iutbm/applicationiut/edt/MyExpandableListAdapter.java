package com.iutbm.applicationiut.edt;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.iutbm.applicationiut.ConfigEDTActivity;
import com.iutbm.applicationiut.R;

/**
 * Created by romain on 16/12/2014.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    int tabID[][] = {{4245},{4673,4674,4676,4675,4677,4678,4679,4680,4681,4682,4691,4692,4685,4686,4688,4689,4694,4695,4697,4698,4707,4708,4701,4702,4704,4705,4710,4711,4713,4714,10874,10875,10876,10877,12246,12247},{4637,4642},{4644,4645},{4652,4653},{338,336},{4655,4656},
            {4658,659},{4985,4986},{12316,12317},{4617,4629},{5133,5134},{5136,5139},{4773,4774},{5006,5007},{5009,5010}
            ,{2741,2743},{2748,2747},{8685},{9660,9658},{9669,9671},{8694,9089},{14424,11739},{13101,13100},{12838,12840},{4550,4551}
            ,{4553,4554},{4556,4666},{552,5031},{5082,5084},{5119,5120},{5137,5128},{5127,5126},{4811,4812},{5821,5823},{5969,5825},{14210},{12432,12434}
            ,{8988,9023},{9029,9052},{3784,10963},{10964,10965},{11574,11578},{11572,11573}
            ,{11580,12655},{12416,12417},{12423,12424},{5273,5274},{5634,5635},{7519,12446},{4819},{14432,14436},{4823,4824},{14438},{11777},{4234,6127}
            ,{4155,4158},{4242,4238},{4154,13930},{2749,5656},{5658,5659},{5660,5662},{4265,4267},{4136,4137},{4217,4218},{4220,4225},{10034},{4160,4162},{4248,4250}
            ,{4342,4343},{4259,4260}};
    int currentID = 0;

    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    public ConfigEDTActivity activity;

    private Backup backup;

    public MyExpandableListAdapter(ConfigEDTActivity act, SparseArray<Group> groups) {
        activity = act;
        this.groups = groups;
        inflater = act.getLayoutInflater();
        backup = new Backup(act);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details, null);
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentID = tabID[groupPosition][childPosition];
                backup.saveData(currentID);
                Toast.makeText(activity,"Formation sauvegardée avec succès",Toast.LENGTH_SHORT).show();
                Log.v("IUT", String.valueOf(currentID));
            }
        });
        return convertView;
    }

    public int getCurrentID(){
        return currentID;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }
        Group group = (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
