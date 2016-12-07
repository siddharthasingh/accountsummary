package com.cub.ao.exception;


public class AccountSummaryException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2991074496227469004L;

	public AccountSummaryException(String errorMessage) {
		super(errorMessage);
	}	
	
}
