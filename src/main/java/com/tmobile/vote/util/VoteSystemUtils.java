package com.tmobile.vote.util;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import com.tmobile.vote.model.Voting;
import com.tmobile.vote.model.Motion;
import com.tmobile.vote.model.Role;
import com.tmobile.vote.model.State;
import com.tmobile.vote.model.Voter;

public class VoteSystemUtils {
	
	
	public static Motion currentMotion = null;
	public static Voting voting = null;
	
	
	
	
	public static long getMotionNextId() {
		return VoteSystemConstants.MOTION_ID++;
	}
	
	public static long getVotingNextId() {
		return VoteSystemConstants.VOTING_ID++;
	}
	public static long getVoterNextId() {
		return VoteSystemConstants.VOTER_ID++;
	}

	public static boolean isSpecialVoter(Voter voter) {
		
		if ( voter != null && voter.getRoles() != null) {
			Set<Role> roles = voter.getRoles();
			for (Role role : roles) {
				if ( 2L == role.getId()){
					return true;
				}
			}
		}
		return false;
	}
	
    public static long timeDifferece(LocalDateTime fromDate, LocalDateTime toDate) {
		
    		return ChronoUnit.MINUTES.between(fromDate,toDate);
		
	}
    
    public static Voter createNormalVoter(String name) {
		Voter voter = new Voter(VoteSystemUtils.getVoterNextId(),name,new Role(1,"Normal"));
		return voter;
	}

	public static Voter createSpecialVoter(String name) {
		Voter voter = new Voter(VoteSystemUtils.getVoterNextId(),name,new Role(2,"VP"));
		return voter;
	}
	
  
	

}
