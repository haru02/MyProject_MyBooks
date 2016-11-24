package com.fcteam6project.mybooks.mybooks;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fcteam6project.mybooks.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookFragment extends Fragment {


    public MyBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_book, container, false);


        return view;
    }

}
