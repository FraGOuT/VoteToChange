package com.codeforfun.himanshu.votetochange.Fragments.MainActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeforfun.himanshu.votetochange.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyElection extends Fragment {


    public MyElection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_election, container, false);
    }

}
