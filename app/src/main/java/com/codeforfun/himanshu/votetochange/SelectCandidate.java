package com.codeforfun.himanshu.votetochange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.codeforfun.himanshu.votetochange.Adapters.RecyclerViewAdapters.CandidateRecyclerViewAdapter;
import com.codeforfun.himanshu.votetochange.DataObjetcs.CandidateData;

import java.util.ArrayList;
import java.util.List;

public class SelectCandidate extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CandidateRecyclerViewAdapter mRecyclerViewAdapter;
    String mElectionKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_candidate);

        Bundle bundle=getIntent().getExtras();
        mElectionKey=bundle.getString("electionKey");
        Log.i("Select Candidate",mElectionKey);

        mRecyclerView = (RecyclerView) findViewById(R.id.candidateRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new CandidateRecyclerViewAdapter(this, getCandidateData(), new OnClickToVote() {
            @Override
            public void clickToVote(String CandidateUsername) {



            }
        });

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    public void voteForCandidate(String username){

        //Make a network call to record this users vote for the candidate with 'username'.



        //Show the user the vote is successfully recorded and then go to home screen.

    }

    //Candidate List
    public List<CandidateData> getCandidateData(){
        List<CandidateData> list = new ArrayList<>();
        CandidateData c=new CandidateData();
        c.setName("Sfsadf");
        c.setUsername("Sanket");
        list.add(c);
        list.add(c);
        list.add(c);
        list.add(c);
        //Network Call
        list=getDataFromCall();
        return list;
    }
    //Network call for candidate list
    public List<CandidateData> getDataFromCall()
    {
        List<CandidateData> list=new ArrayList<>();

        return list;
    }
}