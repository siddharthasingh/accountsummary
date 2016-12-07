package com.cub.ao.exception;

public class RecordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1187703568856982003L;
	public RecordNotFoundException(){
		super();
	}
	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
	}	
}
