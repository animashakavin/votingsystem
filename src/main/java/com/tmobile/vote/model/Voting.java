package com.tmobile.vote.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class Voting {
	
	public void setMotion(Motion motion) {
		this.motion = motion;
	}
	private long votingId;
	private Motion motion;
	private VotingState state;
	private Result result;
	private LocalDateTime creationDate;
	private LocalDateTime openDate;
	private LocalDateTime closeDate;
	
	
	public Voting(long votingId, Motion motion){
		this.votingId = votingId;
		this.motion = motion;
		this.state = VotingState.CREATED;
		creationDate = LocalDateTime.now();
	}
	

	public Voting() {
	}

	
	

	public void setState(VotingState state) {
		this.state = state;
	}


	public Motion getMotion() {
		return motion;
	}
	public VotingState getState() {
		return state;
	}
	
	
	public void setResult(Result result) {
		this.result = result;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public LocalDateTime getOpenDate() {
		return openDate;
	}
	public void setOpenDate(LocalDateTime openDate) {
		this.openDate = openDate;
	}
	public LocalDateTime getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(LocalDateTime closeDate) {
		this.closeDate = closeDate;
	}
	

}
