package com.js.asset;

import java.io.File;
import java.io.OutputStream;

import org.apache.commons.fileupload.FileItem;

public class FileUpload {

	private String filename;
	private long filesize;
	private String filecontenttype;
	private String fieldname;
	private FileItem fileitem;

	public FileItem getFileitem() {
		return fileitem;
	}

	public void setFileitem(FileItem fileitem) {
		this.fileitem = fileitem;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getFilecontenttype() {
		return filecontenttype;
	}

	public void setFilecontenttype(String filecontenttype) {
		this.filecontenttype = filecontenttype;
	}

	
}
