package com.codeforfun.himanshu.votetochange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codeforfun.himanshu.votetochange.Adapters.RecyclerViewAdapters.CandidateRecyclerViewAdapter;
import com.codeforfun.himanshu.votetochange.DataObjetcs.CandidateData;

import java.util.ArrayList;
import java.util.List;

public class SelectCandidate extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CandidateRecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_candidate);

        mRecyclerView = (RecyclerView) findViewById(R.id.candidateRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new CandidateRecyclerViewAdapter(this, getCandidateData(), new OnClickToVote() {
            @Override
            public void clickToVote(String username) {



            }
        });

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    public void voteForCandidate(String username){

        //Make a network call to record this users vote for the candidate with 'username'.



        //Show the user the vote is successfully recorded and then go to home screen.

    }


    public List<CandidateData> getCandidateData(){
        List<CandidateData> list = new ArrayList<>();
        return list;
    }
}