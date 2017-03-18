package com.codeforfun.himanshu.votetochange.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codeforfun.himanshu.votetochange.DataObjetcs.Election_data;
import com.codeforfun.himanshu.votetochange.R;

import java.util.List;

/**
 * Created by JAYESH WALAVALKAR on 18/03/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    List<Election_data> list;

    public MyRecyclerViewAdapter(List<Election_data> list) {
        this.list = list;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {

        Election_data data = list.get(position);

        holder.voteCount.setText(data.getVoteCount());
        holder.startDate.setText(data.getStartDate());
        holder.electionName.setText(data.getElectionName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView electionName,startDate,voteCount;

        public ViewHolder(View itemView) {
            super(itemView);

            electionName = (TextView) itemView.findViewById(R.id.electionName);
            startDate = (TextView) itemView.findViewById(R.id.startDate);
            voteCount = (TextView) itemView.findViewById(R.id.voteCount);
        }
    }
}
