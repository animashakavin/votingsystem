package com.tmobile.vote.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Result {
	
	private long id;
	private long motionId;
	private State state;
	private long positveVotes;
	private long negitveVotes;
	private long totalVotes;
	
	

	public long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}

	private LocalDateTime openDate;
	private LocalDateTime closeDate;
	
	public long getId() {
		return id;
	}
	public long getPositveVotes() {
		return positveVotes;
	}
	public void setPositveVotes(long positveVotes) {
		this.positveVotes = positveVotes;
	}
	public long getNegitveVotes() {
		return negitveVotes;
	}
	public void setNegitveVotes(long negitveVotes) {
		this.negitveVotes = negitveVotes;
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
	public void setId(long id) {
		this.id = id;
	}
	public long getMotionId() {
		return motionId;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public void setMotionId(long motionId) {
		this.motionId = motionId;
	}
	
	public String toString() {
		return "Motion ID : "+motionId+" State :"+state;
	}
	

}
