package com.tmobile.vote.manager;

import java.time.LocalDateTime;
import java.util.Set;

import com.tmobile.vote.exception.DuplicateVoteNotAllowed;
import com.tmobile.vote.exception.MaximumVotesException;
import com.tmobile.vote.exception.VotingNotAllowed;
import com.tmobile.vote.exception.VotingSystemException;
import com.tmobile.vote.model.Role;
import com.tmobile.vote.model.State;
import com.tmobile.vote.model.Vote;
import com.tmobile.vote.model.Voting;
import com.tmobile.vote.model.VotingState;
import com.tmobile.vote.util.VoteSystemConstants;
import com.tmobile.vote.util.VoteSystemUtils;

public class RuleManager {

	public static boolean isSpecialVoter(Set<Role> roles) {
		for (Role role : roles) {
			if (2 == role.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean timeRule(Voting voting) {
		return VoteSystemUtils.timeDifferece(voting.getOpenDate(),
				LocalDateTime.now()) > VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE;
	}

	public static void validateRule(Voting voting,Vote vote) throws VotingSystemException {
		if (voting.getState() == VotingState.OPEN) {
			if (!RuleManager.isSpecialVoter(vote.getCastedBy().getRoles() )){
				if (voting.getMotion().getVotes().size() < 100) {
					if (!voting.getMotion().getVotes().add(vote)) {
						throw new DuplicateVoteNotAllowed(
								"Duplicate Vote Not Allowed !");
					}
				} else {
					throw new MaximumVotesException("Maximum Votes Casted !");
				}
			} else {
				if (RuleManager.isSpecialVoter(vote.getCastedBy().getRoles())
						&&voting .getMotion().getState() == State.TIED) {
					if (voting.getMotion().getVotes().size() < 101) {
						voting.getMotion().getVotes().add(vote);
						if(vote.getChoice().getId() == 2){
							voting.getMotion().setState(State.PASSED);
						} else {
							voting.getMotion().setState(State.PASSED);	
						}
						voting.setState(VotingState.CLOSE);
					} else {
						throw new VotingNotAllowed("Maximum Votes Casted !");
					}
				} else {
					throw new VotingNotAllowed("Special user cannot Vote when Motion not in Tied state");
				}
			}
		} else {
			throw new VotingNotAllowed("Voting Not OPENED");
		}

		
		
	}

	
}
