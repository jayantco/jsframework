package com.js.asset;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadConfig {
	private String filestoragepath;
	private int maxfilesize;
	private String tmpstoragepath;
	
	private int maxmemsize;
	
	
	public int getMaxmemsize() {
		return maxmemsize;
	}
	public void setMaxmemsize(int maxmemsize) {
		
		this.maxmemsize = maxmemsize;
		System.out.println(maxmemsize);
	}
	public String getFilestoragepath() {
		return filestoragepath;
	}
	public void setFilestoragepath(String filestoragepath) {
		this.filestoragepath = filestoragepath;
	}
	public int getMaxfilesize() {
		return maxfilesize;
	}
	public void setMaxfilesize(int maxfilesize) {
		
		this.maxfilesize = maxfilesize;
		System.out.println(maxfilesize);
	}
	public String getTmpstoragepath() {
		return tmpstoragepath;
	}
	public void setTmpstoragepath(String tmpstoragepath) {
		this.tmpstoragepath = tmpstoragepath;
	}

	}
	
	

