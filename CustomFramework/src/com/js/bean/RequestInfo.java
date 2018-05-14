package com.js.bean;

import java.util.HashMap;

import com.js.action.JSForward;

import com.js.asset.FileUploadConfig;



public class RequestInfo {
	private HashMap<String, JSForward> paths = new HashMap<String,JSForward>();
	private String path;
	private String forward;
	private String beanclass;
	private String actionclass;
	private String validate;
	private String inputPage;
	private FileUploadConfig upload;
	
	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}
	private HashMap<String, String> globalforwards = new HashMap<String, String>();
	
	public FileUploadConfig getUpload() {
		return upload;
	}

	public void setUpload(FileUploadConfig upload) {
		this.upload = upload;
	}

	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

	public String isValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBeanclass() {
		return beanclass;
	}

	public void setBeanclass(String beanclass) {
		this.beanclass = beanclass;
	}

	public String getActionclass() {
		return actionclass;
	}

	public void setActionclass(String actionclass) {
		this.actionclass = actionclass;
	}
	
	public void putForward(String forward ,	JSForward jsforward)
	{
		paths.put(forward, jsforward);
	}
	
	public JSForward get(String forwardname)
	{
		return paths.get(forwardname);
	}
	public void setGlobalpage(String globalforwardname,String globalpage)
	{
		globalforwards.put(globalforwardname, globalpage);
	}
}
