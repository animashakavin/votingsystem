package com.tmobile.vote.model;

public enum VotingState {
	
	OPEN("OPEN"),
	CLOSE("CLOSE"),
	CREATED("CREATED");
	
	String votingState;
	
	VotingState(String votingState){
		this.votingState = votingState;
	}
	
	String votingState(){
		return votingState;
	}
}
