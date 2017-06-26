package com.codeforfun.himanshu.votetochange;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codeforfun.himanshu.votetochange.Adapters.RecyclerViewAdapters.CandidateRecyclerViewAdapter;
import com.codeforfun.himanshu.votetochange.Adapters.RecyclerViewAdapters.MyRecyclerViewAdapter;
import com.codeforfun.himanshu.votetochange.DataObjetcs.CandidateData;

import java.util.ArrayList;
import java.util.List;

public class ElectionDisplay extends AppCompatActivity {

    private TextView mElectionName,mElectionVoteCount;
    private RecyclerView mCandidateRecyclerView;
    private CandidateRecyclerViewAdapter mCandidateRecyclerViewAdapter;
    private List<CandidateData> candidateData;
    private SwipeRefreshLayout mSwipeRefreshLayoutCandidate;

    private String LOG_TAG="ElectionDisplay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_display);

        mSwipeRefreshLayoutCandidate=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        mCandidateRecyclerView = (RecyclerView)findViewById(R.id.candidateList);
        mElectionName=(TextView)findViewById(R.id.electionName);


        Bundle bundle=getIntent().getExtras();
        String electionName = bundle.getString("electionName");
        final String candidateKey ="Candidate key"; //bundle.getString("candidateKey");
        final String electionKey ="Election Key";// bundle.getString("electionKey");

        mElectionName.setText(electionName);

        candidateData=getData();

        setupSwipeRefreshLayoutCandidate();

        setAdapter();

        OnClickButtonListner((Button)findViewById(R.id.candidateButton),candidateKey);
        OnClickButtonListner((Button)findViewById(R.id.electionKeyButton),electionKey);

    }

    public void setupSwipeRefreshLayoutCandidate()
    {
        //SwipeRefreshLayout
        mSwipeRefreshLayoutCandidate.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
            }
        });
        mSwipeRefreshLayoutCandidate.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    public void setAdapter()
    {
        //Candidate Adapter
        mCandidateRecyclerViewAdapter = new CandidateRecyclerViewAdapter(this, candidateData, new OnClickToVote() {
            @Override
            public void clickToVote(String username) {

            }
        });
        mCandidateRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCandidateRecyclerView.getLayoutManager().setAutoMeasureEnabled(true);
        mCandidateRecyclerView.setNestedScrollingEnabled(false);
        mCandidateRecyclerView.setHasFixedSize(false);
        mCandidateRecyclerView.setAdapter(mCandidateRecyclerViewAdapter);
    }

    public void OnClickButtonListner(Button but,final String value)
    {
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, value);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    public void update()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCandidateRecyclerView.setAdapter(mCandidateRecyclerViewAdapter);
                mSwipeRefreshLayoutCandidate.setRefreshing(false);
            }},4000);
    }

    public List<CandidateData> getData()
    {
        CandidateData data=new CandidateData();
        data.setName("Sanket");
        data.setUsername("Shetye");

        List<CandidateData> cd=new ArrayList<>();
        //Network Call
        cd=getNetworkCallData();
        cd.add(data);
        cd.add(data);
        cd.add(data);
        cd.add(data);
        cd.add(data);
        cd.add(data);
        cd.add(data);

        return cd;
    }

    public List<CandidateData> getNetworkCallData()
    {
        List<CandidateData> list=new ArrayList<>();

        return  list;
    }
}
