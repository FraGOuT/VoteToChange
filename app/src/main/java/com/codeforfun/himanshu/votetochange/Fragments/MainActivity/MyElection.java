package com.codeforfun.himanshu.votetochange.Fragments.MainActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.codeforfun.himanshu.votetochange.Adapters.RecyclerViewAdapters.MyRecyclerViewAdapter;
import com.codeforfun.himanshu.votetochange.AppConstants;
import com.codeforfun.himanshu.votetochange.DataObjetcs.ElectionData;
import com.codeforfun.himanshu.votetochange.R;

import java.util.ArrayList;
import java.util.List;


public class MyElection extends Fragment {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    private String mElectionName;

    public MyElection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_election, container, false);

        mContext = getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.electionDataRecyclerView);

        List<ElectionData> election_data = getData();

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(election_data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(myRecyclerViewAdapter);

        view.findViewById(R.id.createElectionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewElection();
            }
        });


        return view;
    }

    public void startNewElection() {

        InputNewElectionName();

        if (mElectionName == null)
            return;
        else if (mElectionName.length() == 0){
            Toast.makeText(mContext, "Election Name Not Proper", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(mContext, "Election name : "+mElectionName , Toast.LENGTH_SHORT).show();


    }

    public void InputNewElectionName(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_for_election_key_input,null);
        alertDialogBuilder.setView(dialogView);

        final EditText name = (EditText) dialogView.findViewById(R.id.electionKeyInput);
        name.setHint("Enter Election Name");

        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mElectionName = name.getText().toString();
                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public List<ElectionData> getData(){

        ElectionData data = new ElectionData();
        data.setElectionName("First One");
        data.setStartDate("today");
        data.setVoteCount(55);

        List<ElectionData> da = new ArrayList<>();
        da.add(data);
        da.add(data);

        return da;
    }

}
