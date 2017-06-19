package com.tmobile.vote.model;

public enum State {
	
	PASSED("PASSED"),
	FAILD("FAILED"),
    TIED("TIED");
    
    String state;
    
    State(String state) {
		this.state = state;
	}

     String state(){
    	return state;
    }

}
