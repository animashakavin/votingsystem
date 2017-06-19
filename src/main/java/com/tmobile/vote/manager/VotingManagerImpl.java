package com.tmobile.vote.manager;

import java.time.LocalDateTime;

import com.tmobile.vote.exception.UpdateStatusNotAllowed;
import com.tmobile.vote.exception.VotingSystemException;
import com.tmobile.vote.model.Choice;
import com.tmobile.vote.model.Motion;
import com.tmobile.vote.model.Result;
import com.tmobile.vote.model.State;
import com.tmobile.vote.model.Vote;
import com.tmobile.vote.model.Voter;
import com.tmobile.vote.model.Voting;
import com.tmobile.vote.model.VotingState;
import com.tmobile.vote.util.VoteSystemUtils;

public class VotingManagerImpl implements VotingManager {

	Voting voting;
	
	public VotingManagerImpl(String motionName) {
		Motion motion = motionManager.createMotion(motionName);
		voting = new Voting(VoteSystemUtils.getVotingNextId(), motion);
	}

	/*public void attacMotionForVoting(String motionName) {
		Motion motion = motionManager.createMotion(motionName);
		voting.setMotion(motion);
	}*/

	public void openVoting() {
		voting.setState(VotingState.OPEN);
		voting.setOpenDate(LocalDateTime.now());
	}

	public Result closeVoting() throws VotingSystemException {
		if (RuleManager.timeRule(voting)) {
			Result result = motionManager.getMotionResults(this.voting
					.getMotion());
			if (result.getState() != State.TIED) {
				if (RuleManager.timeRule(voting)) {
					updateStatus(VotingState.CLOSE);
					motionManager.updteMotion(this.voting.getMotion(),
							result.getState());
				}

			} else {
				motionManager.updteMotion(this.voting.getMotion(), State.TIED);
			}
			return result;
		} else {
			throw new UpdateStatusNotAllowed("Closing Not Allowed !");
		}

	}

	
	private Voting updateStatus(VotingState state) {
		voting.setState(state);
		if (VotingState.OPEN == state) {
			this.voting.setOpenDate(LocalDateTime.now());
		} else if (VotingState.CLOSE == state) {
			this.voting.setCloseDate(LocalDateTime.now());
		}
		return voting;
	}

	public void castVote(Voter voter, Choice choice) throws VotingSystemException {
		Vote vote = new Vote(choice, voter);
		RuleManager.validateRule(this.voting,vote);
	}

	public void changeStateVIPNotAvailable() {
		Result result = votingResults();
		if (result.getState() == State.TIED) {
			updateStatus(VotingState.CLOSE);
			motionManager.updteMotion(voting.getMotion(), State.FAILD);
		}
	}

	public Result votingResults() {
		Result result = motionManager.getMotionResults(this.voting.getMotion());
		result.setCloseDate(this.voting.getCloseDate());
		result.setOpenDate(this.voting.getOpenDate());
		return result;
	}

	public State currentMotionState() {
		return motionManager.getMotionState(this.voting.getMotion());
	}

	public VotingState votingStatus() {
		return this.voting.getState();
	}

	

}
