package com.tmobile.vote.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Motion {

	
	public Set<Vote> getVotes() {
		return votes;
	}


	private long id;
	private String name;
	private Set<Choice> options;
	private Set<Vote> votes;
	private State state;

	public String getName() {
		return name;
	}

	
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

	public Motion(long id, String name) {
		this.name = name;
		this.id = id;
		Choice yesOption = new Choice(1, "Yes");
		Choice noOption = new Choice(2, "No");
		this.options = new HashSet<Choice>(2);
		this.options.add(yesOption);
		this.options.add(noOption);
		this.votes = new HashSet<Vote>(100);
		createDate = LocalDateTime.now();
		updateDate = LocalDateTime.now();
	}

	public Set<Choice> getOptions() {
		return options;
	}

	
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	

	public long getId() {
		return id;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Motion :").append(this.name);
		for (Choice option : this.options) {
			buffer.append("\n").append(option.getValue());
		}
		return buffer.toString();
	}
	


}
