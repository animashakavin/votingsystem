package com.tmobile.vote.exception;

public class VotingSystemException extends Exception {
	private String message;

	public VotingSystemException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
