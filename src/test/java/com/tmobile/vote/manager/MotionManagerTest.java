package com.tmobile.vote.manager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import com.tmobile.vote.model.Motion;
import com.tmobile.vote.model.State;

public class MotionManagerTest {
	
	MotionManager motionManager = null;
	@Before
	public void setUP() {
		motionManager = new MotionManagerImpl();
	}
	
	
	
	@Test
	public void createMotionTest() {
		Motion motion = motionManager.createMotion("Is Constrcution Required");
		assertNotNull(motion);
		assertEquals(motion.getName(), "Is Constrcution Required");
		
	}
	
	@Test
	public void changeMotionTest() {
		Motion motion = motionManager.createMotion("Is Constrcution Required");
		Motion afterChange = motionManager.updteMotion(motion, State.PASSED);
		assertEquals(afterChange.getState(), State.PASSED);
			
	}
	
	
	@After
	public void tearDown() {
		motionManager = null;
	}
	

}
