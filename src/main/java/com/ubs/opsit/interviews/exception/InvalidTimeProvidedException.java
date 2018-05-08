package com.ubs.opsit.interviews.exception;

/**
 * @author Vinayak Chavan
 *
 */
public class InvalidTimeProvidedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Takes the message as parameter
	 * 
	 * @param msg
	 */
	public InvalidTimeProvidedException(String msg) {
		super(msg);
	}

}
