package com.tyc.lottery.award.prizes.exception;

/**
 * ∂“Ω±“Ï≥£
 * @author anyi
 *
 */
@SuppressWarnings("serial")
public class AwardException extends Exception{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public AwardException() {
		
	}
	
	public AwardException(String message) {
		this.message = message;
	}
	
	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}
	
}
