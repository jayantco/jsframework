package com.lara;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.action.JSAction;
import com.js.action.JSBean;
import com.js.action.JSForward;
import com.js.asset.FileUpload;
import com.js.asset.FileUploadConfig;
import com.js.controller.Processor;
import com.js.db.JSConfiguration;
import com.js.db.JSSession;

public class LoginAction extends JSAction{
@Override
public JSForward process(JSBean bean, Processor process,
		HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	LoginBean login = (LoginBean)bean;
	System.out.println(login.getUsername());
	System.out.println(login.getPassword());
	System.out.println(login.getId());
	//System.out.println(login.getEducation()[0]);
	// you have to pass key what you have give in DataConfig.xml's dbtype
	
	JSConfiguration c1 =new JSConfiguration();
	c1.configure();
	JSSession s1= c1.getSession();
	s1.save();
	System.out.println(s1);
	FileUploadConfig config =  getUploadConfig();
	FileUpload fileupload[] = login.getFileupload();

	process.startFileUpload(config, fileupload, null);
	//success is name of forward page in login request tag in moduleconfig.xml file 
	return process.getForwardPath("success");
}
}
