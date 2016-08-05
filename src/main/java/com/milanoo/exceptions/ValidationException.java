package com.milanoo.exceptions;

public class ValidationException extends Exception {
	
	public ValidationException() {
	}

	
	public ValidationException(String msg) {
		super(msg);
	}
	
	/**
	 * 
	 * @param msg
	 * 
	 * <br>
	 * 
	 * 抛出数字不相等异常
	 */
	public ValidationException(Integer expect, Integer actual) {
		super("要验证的值和期望不同，期望为 " + expect + " 实际为 " + actual);
	}
	
	/**
	 * 
	 * @param msg
	 * 
	 * <br>
	 * 
	 * 抛出数字不相等异常,附加消息
	 */
	public ValidationException(Integer expect, Integer actual, String message) {
		super(message + "。 要验证的值和期望不同，期望为 " + expect + " 实际为 " + actual);
	}
}
