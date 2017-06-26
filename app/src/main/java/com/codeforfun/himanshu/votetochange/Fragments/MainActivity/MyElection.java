package com.codeforfun.himanshu.votetochange.Fragments.MainActivity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.codeforfun.himanshu.votetochange.ElectionDisplay;
import com.codeforfun.himanshu.votetochange.MainActivity;
import com.codeforfun.himanshu.votetochange.NetworkCalls.BackgroundNetworkCall;
import com.codeforfun.himanshu.votetochange.NetworkHelper.UrlData;
import com.codeforfun.himanshu.votetochange.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class MyElection extends Fragment {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    private String mElectionName;
    private String mCandidateKey;
    private String mElectionKey;
    private String mElectionDescription;
    private List<ElectionData> election_data;

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

        election_data = getData();
        //get information from database


        myRecyclerViewAdapter = new MyRecyclerViewAdapter(election_data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        mRecyclerView.setFocusable(false);

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
        alertDialogBuilder.setTitle("Create new Election");

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_for_creating_new_election,null);
        alertDialogBuilder.setView(dialogView);

        final EditText name = (EditText) dialogView.findViewById(R.id.electionKeyInput);
        final EditText description =(EditText) dialogView.findViewById(R.id.electionDescription);
        name.setHint("Enter Election Name");


        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mElectionName = name.getText().toString();
                        mElectionDescription =description.getText().toString();
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String username = preferences.getString(AppConstants.SHARED_PREFS_USERNAME, "DEFAULT");

                        boolean valid=true;
                        //valid=sendingData(username,mElectionName,mElectionDescription);
                        if(valid==true)
                        {
                            addData(mElectionName,mElectionDescription);

                            Intent i = new Intent(getContext(), ElectionDisplay.class);
                            i.putExtra("electionName",mElectionName);
                            i.putExtra("candidateKey",mCandidateKey);
                            i.putExtra("electionKey",mElectionName);

                            startActivity(i);
                        }
                        else
                        {

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

    public String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private boolean isUsernameValid(String username){
        return username.length() > 0;
    }

    private boolean isElectionNameValid(String password){
        return password.length() > 0;
    }

    public boolean sendingData(String username,String name,String description)
    {
        //Make sure that the username and password are valid.
        if(!isUsernameValid(username) || !isElectionNameValid(name)){
            Toast.makeText(getContext(), "Input data is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        List<String> queryData = new ArrayList<>();
        queryData.add("username");
        queryData.add(username);
        queryData.add("election_name");
        queryData.add(name);
        queryData.add("description");
        queryData.add(description);

        try {
            String electionResult = new BackgroundNetworkCall().execute(UrlData.REGISTER_ELECTION_URL,queryData,getContext());
            Log.i(AppConstants.TAG,"Election Result = "+electionResult);
            String resultarray[]=electionResult.split(" ");
            Log.i(AppConstants.TAG,"Result array = "+resultarray[3]);

            if(electionResult!=null && resultarray[3].equals("1") ){
                //Login is Success;

                mElectionKey=resultarray[0];
                mCandidateKey=resultarray[1];
                Log.i(AppConstants.TAG,"Election Created");

                return true;
            }
            else{
                //Login Failed
                Toast.makeText(getContext(), "Network error", Toast.LENGTH_SHORT).show();
                //notifyLoginFailed();
                return false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addData(String name,String description)
    {
        ElectionData data = new ElectionData();

        data.setElectionName(name);
        data.setStartDate(getCurrentDate());
        data.setVoteCount(0);
        data.setElectionDescription(description);

        election_data.add(data);
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
