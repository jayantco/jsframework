package com.js.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.asset.FileUploadConfig;
import com.js.controller.Processor;

public abstract class JSAction {
	private FileUploadConfig config ;
	public JSForward process(JSBean bean, Processor process,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	public final void setUploadConfig(FileUploadConfig config, String string)
	{
		if(string.equals("true"))
		this.config = config;
	}
	public FileUploadConfig getUploadConfig()
	{
		return config;
	}
}
