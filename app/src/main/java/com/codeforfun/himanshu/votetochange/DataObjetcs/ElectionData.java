package com.codeforfun.himanshu.votetochange.DataObjetcs;

/**
 * Created by JAYESH WALAVALKAR on 18/03/2017.
 */

public class ElectionData {
    private String electionName;
    private String startDate;
    private int voteCount;
    private String electionStatus;
    private int electionId;
    private String electionDescription;

    public ElectionData() {
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setElectionStatus(String electionStatus) {
        this.electionStatus = electionStatus;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public void setElectionDescription(String electionDescription) {
        this.electionDescription = electionDescription;
    }

    public String getElectionName() {

        return electionName;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getElectionStatus() {
        return electionStatus;
    }

    public int getElectionId() {
        return electionId;
    }

    public String getElectionDescription() {
        return electionDescription;
    }
}