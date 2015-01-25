package com.iutbm.applicationiut.edt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romain on 16/12/2014.
 */
public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

}
