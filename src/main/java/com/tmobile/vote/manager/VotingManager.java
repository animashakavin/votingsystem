package com.tmobile.vote.manager;

import com.tmobile.vote.exception.VotingSystemException;
import com.tmobile.vote.model.Choice;
import com.tmobile.vote.model.Result;
import com.tmobile.vote.model.State;
import com.tmobile.vote.model.Voter;
import com.tmobile.vote.model.VotingState;

public interface VotingManager {
	MotionManager motionManager = new MotionManagerImpl();
		
	public void openVoting();
	public void castVote(Voter voter,Choice choice) throws VotingSystemException;
	public Result closeVoting() throws VotingSystemException;
	public State currentMotionState();
	public VotingState votingStatus();
	
	
	
	

	
	
}
