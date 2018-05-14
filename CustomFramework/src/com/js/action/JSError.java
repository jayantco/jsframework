package com.js.action;

public class JSError {
	private String error;
	public JSError(String error) {
		setError(error);
	}
	
	private void setError(String error2) {
		// TODO Auto-generated method stub
		this.error = error2;
		System.out.println(error);
	}

	public String getError()
	{
		return error;
	}
}
