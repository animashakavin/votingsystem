package com.tmobile.vote.model;

import java.util.HashSet;
import java.util.Set;

public class Voter {
	
	private long id;
	private String voterName;
	private Set<Role> roles = new HashSet<Role>();
	private boolean available;
	
	public Voter(long id,String voterName,Role role){
		this.id = id;
		this.voterName = voterName;
		this.roles.add(role);
		this.available = true;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((voterName == null) ? 0 : voterName.hashCode());
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
		Voter other = (Voter) obj;
		if (id != other.id)
			return false;
		if (voterName == null) {
			if (other.voterName != null)
				return false;
		} else if (!voterName.equals(other.voterName))
			return false;
		return true;
	}
	
	
}
