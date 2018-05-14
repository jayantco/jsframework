package com.lara;

import com.js.action.JSBean;
import com.js.action.JSError;
import com.js.action.JSErrors;
import com.js.asset.FileUpload;

public class LoginBean extends JSBean{
	private String id;
	
	private String username;
	private String password;
	private FileUpload fileupload[];
	private String education[];
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String[] getEducation() {
		return education;
	}


	public void setEducation(String[] education) {
		this.education = education;
	}


	public FileUpload[] getFileupload() {
		return fileupload;
	}


	public void setFileupload(FileUpload[] fileupload) {
		this.fileupload = fileupload;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

	@Override
	public JSErrors validate() {
		// TODO Auto-generated method stub
		JSErrors errors = new JSErrors();
		JSError error = null;

		if (username == null || username.length()==0) {
			error = new JSError("Username is required");
			errors.addErrors("username", error);
		}
		if (password == null || password.length()==0) {
			error = new JSError("Password is required");
			errors.addErrors("password", error);

		}
		

		return errors;
	}

}
