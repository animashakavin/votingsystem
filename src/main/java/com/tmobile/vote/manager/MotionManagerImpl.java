package com.tmobile.vote.manager;

import java.util.Set;

import com.tmobile.vote.model.Motion;
import com.tmobile.vote.model.Result;
import com.tmobile.vote.model.State;
import com.tmobile.vote.model.Vote;
import com.tmobile.vote.util.VoteSystemUtils;

public class MotionManagerImpl implements MotionManager{
	
	public static Motion currentMotion  = null;
	
	public Motion createMotion(String motionName) {
		Motion motion = new Motion(VoteSystemUtils.getMotionNextId(),motionName);
		return motion;
	}

	public Motion updteMotion(Motion motion, State state) {
		motion.setState(state);
		return motion;
	}
    // need to get clarity on this
	public State getMotionState(Motion motion) {
		Result result = getMotionResults(motion);
		return result.getState();
		
	}

	public Result getMotionResults(Motion motion) {
		Result result = new Result();
		int yesCount = 0;
		int noCount = 0;
		Set<Vote> votes = motion.getVotes();
		for (Vote vote : votes) {
				if (vote.getChoice().getId() == 1) {
					yesCount++;
				}else{
					noCount ++;
				}
				
		}
		
		if ( yesCount > noCount) {
			result.setState(State.PASSED);
		} else if(yesCount < noCount){
			result.setState(State.FAILD);
		} else {
			result.setState(State.TIED);
		}
		result.setPositveVotes(yesCount);
		result.setNegitveVotes(noCount);
		result.setTotalVotes(yesCount+noCount);
		return result;
	}



		

}
