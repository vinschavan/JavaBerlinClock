package com.ubs.opsit.interviews.exception;

/**
 * @author Vinayak Chavan
 *
 */
public class TimeNotProvidedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Takes the message as parameter
	 * 
	 * @param msg
	 */
	public TimeNotProvidedException(String msg) {
		super(msg);
	}

}
