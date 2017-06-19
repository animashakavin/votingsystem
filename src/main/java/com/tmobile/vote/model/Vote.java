package com.tmobile.vote.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Vote {

	
	public Choice getChoice() {
		return choice;
	}

	private long id;
	private Choice choice;
	private LocalDateTime castedDate;
	private Voter castedBy;

	
	public Vote(Choice choice,Voter castedBy) {
		this.choice = choice;
		this.castedBy = castedBy;
		castedDate = LocalDateTime.now();
	}

	public Voter getCastedBy() {
		return castedBy;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((castedBy == null) ? 0 : castedBy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (castedBy == null) {
			if (other.castedBy != null)
				return false;
		} else if (!castedBy.equals(other.castedBy))
			return false;
		return true;
	}


}
