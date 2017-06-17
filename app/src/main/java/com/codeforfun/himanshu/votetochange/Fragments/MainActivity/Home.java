package com.codeforfun.himanshu.votetochange.Fragments.MainActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.codeforfun.himanshu.votetochange.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private String mElectionKey = null;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        view.findViewById(R.id.VoteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                casteVote();
            }
        });

        return view;
    }


    public void casteVote(){

        //We first need the user to input the election key
        getElectionKeyInput();

        //If the user had cancelled the dialog box then election key will be null.
        if(mElectionKey == null)
            return;

        //Now we need to make a network call to check wether the entered key is valid or not.
        //After that we need to check wether the user has already voted or not.


        //If the user has already not voted then we need to get the candidate list.
        //We change the activity after that.

    }

    public void getElectionKeyInput(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_for_election_key_input,null);
        alertDialogBuilder.setView(dialogView);

        final EditText key = (EditText) dialogView.findViewById(R.id.electionKeyInput);

        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mElectionKey = key.getText().toString();
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



}
