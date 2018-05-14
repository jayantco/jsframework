package com.js.controller;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import com.js.action.JSForward;
import com.js.asset.FileUpload;
import com.js.asset.FileUploadConfig;

import com.js.bean.RequestInfo;

import com.js.pool.ConnectionPool;

public class Processor {
	
	
	private Connection con = null;
	private RequestInfo req;

	private Vector<Connection> poolcon = null;
	private boolean status = true;
	private HashMap<String, JSForward> gforwards;
	private String key;

	public Processor() {
		// TODO Auto-generated constructor stub
		
		
	}

	public void setRequest(RequestInfo req) {
		// TODO Auto-generated method stub
		this.req = req;
	}

	public JSForward getForwardPath(String string) {
		// TODO Auto-generated method stub
		JSForward forward = req.get(string);
		
		
		if (forward == null && gforwards != null) {
			forward = gforwards.get(string);
		
		}
		return forward;
	}

	public boolean startFileUpload(FileUploadConfig config,
			FileUpload[] fileupload, FileUpload singleupload) {
		boolean status = false;
		if (config != null && fileupload != null) {

			for (FileUpload fileUpload : fileupload) {

				status = fileUpload(config, fileUpload);
			}

		} else if (config != null && (singleupload != null)) {
			status = fileUpload(config, singleupload);
		}
		return status;
	}

	private boolean fileUpload(FileUploadConfig config, FileUpload fileupload) {
		// TODO Auto-generated method stub
		if (!config.getFilestoragepath().endsWith("\\")) {
			config.setFilestoragepath(config.getFilestoragepath() + "\\");
		}
		File directory = new File(config.getFilestoragepath());
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}
		File file = new File(config.getFilestoragepath()
				+ fileupload.getFilename());
		try {
			fileupload.getFileitem().write(file);
			status = fileupload.getFileitem().isInMemory();
			fileupload.getFileitem().delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public void setGlobalForwards(HashMap<String, JSForward> globalForward) {
		// TODO Auto-generated method stub
		this.gforwards = globalForward;
	}

}
