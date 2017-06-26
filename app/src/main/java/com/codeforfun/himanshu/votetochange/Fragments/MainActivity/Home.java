package com.codeforfun.himanshu.votetochange.Fragments.MainActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.codeforfun.himanshu.votetochange.R;
import com.codeforfun.himanshu.votetochange.SelectCandidate;


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


        view.findViewById(R.id.registerACandidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Use a dialog box and take a name for the candidate.
                //The name along with the username name should be registered with the server.

                //After the registration of candidate completes notify the user.

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
        //After that we need to check whether the user has already voted or not in that election.



        //We change the activity.
        //In the new activity we make a network call to get the list of candidates for that election.
        //Then the user can select any one of them and click vote.


    }

    public void getElectionKeyInput(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_for_election_key_input,null);
        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setTitle("Vote");
        final EditText key = (EditText) dialogView.findViewById(R.id.electionKeyInput);

        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mElectionKey = key.getText().toString();
                        //Network Call
                        //Check if key is present
                        boolean result=true;//function
                        if(result)
                        {
                            Intent i=new Intent(getContext(), SelectCandidate.class);
                            i.putExtra("electionKey",mElectionKey);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getContext(),"Invalid Key",Toast.LENGTH_SHORT);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}
