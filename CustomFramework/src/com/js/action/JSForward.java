package com.js.action;

import java.util.HashMap;

public class JSForward {
	private String forwardpage;
	private String gforward;
	private HashMap<String, JSForward> gforwards = new HashMap<String, JSForward>();
	public String getGforward() {
		return gforward;
	}

	public void setGforward(String gforward) {
		this.gforward = gforward;
	}

	public String getForwardpage() {
		return forwardpage;
	}

	public void setForwardpage(String forwardpage) {
		this.forwardpage = forwardpage;
	}

	public void setGForward(String forwardname,JSForward gforward)
	{
		gforwards.put(forwardname, gforward);
	}
			
	public JSForward getGlobalForward(String forwardname)
	{
		return gforwards.get(forwardname);
	}
	}


