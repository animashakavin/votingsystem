package com.tmobile.vote.manager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tmobile.vote.exception.DuplicateVoteNotAllowed;
import com.tmobile.vote.exception.UpdateStatusNotAllowed;
import com.tmobile.vote.exception.VotingNotAllowed;
import com.tmobile.vote.exception.VotingSystemException;
import com.tmobile.vote.model.Choice;
import com.tmobile.vote.model.Result;
import com.tmobile.vote.model.State;
import com.tmobile.vote.model.Voter;
import com.tmobile.vote.model.VotingState;
import com.tmobile.vote.util.VoteSystemConstants;
import com.tmobile.vote.util.VoteSystemUtils;

public class VotingManagerTest {

	VotingManager votingManager = null;

	@Before
	public void setUP() {
		votingManager = new VotingManagerImpl("Can we open session in Septemper?");
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = 15;
	}

	@Test(expected = VotingNotAllowed.class)
	public void votingNotAllowed() throws VotingSystemException {
		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Choice choice = new Choice(1, "Yes");
		votingManager.castVote(normalVoter, choice);

	}

	@Test(expected = UpdateStatusNotAllowed.class)
	public void expectErrorPreMetureclosing() throws VotingSystemException {
		votingManager.openVoting();
		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Choice choice = new Choice(1, "Yes");
		try {
			votingManager.castVote(normalVoter, choice);
		} catch (VotingNotAllowed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Result result = votingManager.closeVoting();

	}

	@Test(expected = DuplicateVoteNotAllowed.class)
	public void restrictDuplicateVotes() throws VotingSystemException {
		votingManager.openVoting();
		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Choice choice = new Choice(1, "Yes");

		votingManager.castVote(normalVoter, choice);
		votingManager.castVote(normalVoter, choice);

	}

	@Test()
	public void expectResultAfterclosing() throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this
		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Choice choice = new Choice(1, "Yes");
		try {
			votingManager.castVote(normalVoter, choice);
		} catch (VotingNotAllowed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Result result = votingManager.closeVoting();
		assertEquals(result.getPositveVotes(), 1);
		assertEquals(result.getNegitveVotes(), 0);
		assertEquals(result.getState(), State.PASSED);
	}

	@Test()
	public void motionStateChangeForTIED() throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this

		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Voter normalVoter2 = VoteSystemUtils.createNormalVoter("Kiran");
		Voter normalVoter3 = VoteSystemUtils.createNormalVoter("Ramesh");
		Voter normalVoter4 = VoteSystemUtils.createNormalVoter("Ravi");

		Choice yes = new Choice(1, "Yes");
		Choice no = new Choice(2, "No");

		votingManager.castVote(normalVoter, yes);
		votingManager.castVote(normalVoter2, no);
		votingManager.castVote(normalVoter3, yes);
		votingManager.castVote(normalVoter4, no);
		votingManager.closeVoting();

		State state = votingManager.currentMotionState();
		assertEquals(state, State.TIED);
	}

	@Test(expected = VotingNotAllowed.class)
	public void restrictVPWhenMotionNotInTiedState()
			throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this

		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Voter normalVoter2 = VoteSystemUtils.createNormalVoter("Kiran");
		Voter normalVoter3 = VoteSystemUtils.createNormalVoter("Ramesh");
		Voter normalVoter4 = VoteSystemUtils.createNormalVoter("Ravi");

		Voter superVoter = VoteSystemUtils.createSpecialVoter("VP");

		Choice yes = new Choice(1, "Yes");
		Choice no = new Choice(2, "No");

		votingManager.castVote(normalVoter, yes);
		votingManager.castVote(normalVoter2, yes);
		votingManager.castVote(normalVoter3, yes);
		votingManager.castVote(normalVoter4, no);
		votingManager.closeVoting();
		votingManager.castVote(superVoter, yes);

	}

	@Test()
	public void allowVPWhenMotionInTiedState() throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this

		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Voter normalVoter2 = VoteSystemUtils.createNormalVoter("Kiran");
		Voter normalVoter3 = VoteSystemUtils.createNormalVoter("Ramesh");
		Voter normalVoter4 = VoteSystemUtils.createNormalVoter("Ravi");

		Voter superVoter = VoteSystemUtils.createSpecialVoter("Nayak");

		Choice yes = new Choice(1, "Yes");
		Choice no = new Choice(2, "No");

		votingManager.castVote(normalVoter, yes);
		votingManager.castVote(normalVoter2, yes);
		votingManager.castVote(normalVoter3, no);
		votingManager.castVote(normalVoter4, no);
		votingManager.closeVoting();
		votingManager.castVote(superVoter, yes);

	}

	@Test()
	public void verifyVotingStateAfterVPVoteAsClosed()
			throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this

		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Voter normalVoter2 = VoteSystemUtils.createNormalVoter("Kiran");
		Voter normalVoter3 = VoteSystemUtils.createNormalVoter("Ramesh");
		Voter normalVoter4 = VoteSystemUtils.createNormalVoter("Ravi");

		Voter superVoter = VoteSystemUtils.createSpecialVoter("Nayak");

		Choice yes = new Choice(1, "Yes");
		Choice no = new Choice(2, "No");

		votingManager.castVote(normalVoter, yes);
		votingManager.castVote(normalVoter2, yes);
		votingManager.castVote(normalVoter3, no);
		votingManager.castVote(normalVoter4, no);
		votingManager.closeVoting();
		votingManager.castVote(superVoter, yes);
		VotingState state = votingManager.votingStatus();
		assertEquals(state, VotingState.CLOSE);
	}

	@Test()
	public void verifyMotionStatePASSEDAfterVPVoteAsYes()
			throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this

		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Voter normalVoter2 = VoteSystemUtils.createNormalVoter("Kiran");
		Voter normalVoter3 = VoteSystemUtils.createNormalVoter("Ramesh");
		Voter normalVoter4 = VoteSystemUtils.createNormalVoter("Ravi");

		Voter superVoter = VoteSystemUtils.createSpecialVoter("Nayak");

		Choice yes = new Choice(1, "Yes");
		Choice no = new Choice(2, "No");

		votingManager.castVote(normalVoter, yes);
		votingManager.castVote(normalVoter2, yes);
		votingManager.castVote(normalVoter3, no);
		votingManager.castVote(normalVoter4, no);
		votingManager.closeVoting();
		votingManager.castVote(superVoter, yes);
		State state = votingManager.currentMotionState();
		assertEquals(state, State.PASSED);
	}

	@Test()
	public void verifyMotionStateFailAfterVPVoteAsNo()
			throws VotingSystemException {
		votingManager.openVoting();
		VoteSystemConstants.MINIMUM_DURATION_FOR_VOTING_COLSE = -1; // think
																	// about
																	// this

		Voter normalVoter = VoteSystemUtils.createNormalVoter("Arun");
		Voter normalVoter2 = VoteSystemUtils.createNormalVoter("Kiran");
		Voter normalVoter3 = VoteSystemUtils.createNormalVoter("Ramesh");
		Voter normalVoter4 = VoteSystemUtils.createNormalVoter("Ravi");

		Voter superVoter = VoteSystemUtils.createSpecialVoter("Nayak");

		Choice yes = new Choice(1, "Yes");
		Choice no = new Choice(2, "No");

		votingManager.castVote(normalVoter, yes);
		votingManager.castVote(normalVoter2, yes);
		votingManager.castVote(normalVoter3, no);
		votingManager.castVote(normalVoter4, no);
		votingManager.closeVoting();
		votingManager.castVote(superVoter, no);
		State state = votingManager.currentMotionState();
		assertEquals(state, State.FAILD);
	}

	@After
	public void tearDown() {
		votingManager = null;
	}

}
