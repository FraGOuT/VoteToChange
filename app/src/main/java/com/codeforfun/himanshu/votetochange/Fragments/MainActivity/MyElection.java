package com.codeforfun.himanshu.votetochange.Fragments.MainActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeforfun.himanshu.votetochange.Adapters.MyRecyclerViewAdapter;
import com.codeforfun.himanshu.votetochange.DataObjetcs.Election_data;
import com.codeforfun.himanshu.votetochange.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyElection extends Fragment {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    public MyElection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_election, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.electionDataRecyclerView);

        List<Election_data> election_data = getData();

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(election_data);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.hasFixedSize();


        mRecyclerView.setAdapter(myRecyclerViewAdapter);

        return view;
    }


    public List<Election_data> getData(){

        Election_data data = new Election_data();



        return new ArrayList<>();
    }

}
