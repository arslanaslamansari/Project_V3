package com.example.final_year_project_1.Helper_Classes;

import android.widget.VideoView;

import com.example.final_year_project_1.R;

public class offline_search_helper_class {

    public int search(String search) {
        if (search.equals("a")) {
            return R.raw.alif;
        } else if (search.equals("b")) {
            return R.raw.bay;
        } else
            return 0;
    }
}
