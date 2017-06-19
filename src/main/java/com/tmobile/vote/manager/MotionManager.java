package com.tmobile.vote.manager;

import com.tmobile.vote.model.Motion;
import com.tmobile.vote.model.Result;
import com.tmobile.vote.model.State;

public interface MotionManager {

	public Motion createMotion(String name);
	public Motion updteMotion(Motion motion, State state);
	public State  getMotionState(Motion motion);
	public Result getMotionResults(Motion motion);

	
}
