package com.js.asset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ParseMultipartData {
	private String request_parameter;
	private FileUpload upload;
	private ArrayList<FileUpload> uploadlist;
	private ArrayList<String> request_data;
	private String previous_data_value;
 
	public ParseMultipartData() {
		// TODO Auto-generated constructor stub
		request_parameter = new String();
		
		previous_data_value = new String();
	}



	public FileUpload[] setMultipartFiles(HttpServletRequest request, DiskFileItemFactory factory, ServletFileUpload upload2) {
		// TODO Auto-generated method stub
		String filename;
		try {
			uploadlist = new ArrayList<FileUpload>();
			request_data = new ArrayList<String>();
			List fileit = upload2.parseRequest(request);
			System.out.println(fileit);
			Iterator it = fileit.iterator();
			while (it.hasNext()) {
				
				FileItem fitem = (FileItem) it.next();
				if (!fitem.isFormField()) {
					
					filename = fitem.getName();
					
					if (filename.lastIndexOf("\\")>= 0) {
						filename = filename.substring(filename
								.lastIndexOf("\\"));
					} else {
						filename = filename.substring(filename
								.lastIndexOf("\\") + 1);
					}
					upload = new FileUpload();
					upload.setFileitem(fitem);
					
						upload.setFieldname(fitem.getFieldName());
						upload.setFilename(filename);
						upload.setFilesize(fitem.getSize());
						upload.setFilecontenttype(fitem.getContentType());
						uploadlist.add(upload);
					
				}
				else
				{
					if(request_parameter.equals(fitem.getFieldName()))
					{
						if(!request_data.contains(previous_data_value)){
						
							request_data.add(previous_data_value);
						}
						request_data.add(fitem.getString());
					}
					request_parameter = fitem.getFieldName();
					previous_data_value = fitem.getString();
					
					if(request_data.size()>0)
					{
						request.setAttribute(request_parameter, request_data);
					}
					else
					{
						request.setAttribute(request_parameter, previous_data_value);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return getFileArray(uploadlist);

	}

	private FileUpload[] getFileArray(ArrayList<FileUpload> uploadlist2) {
		// TODO Auto-generated method stub
		Object[] fileupload = uploadlist2.toArray();
		FileUpload[] fileuploadarray = new FileUpload[fileupload.length];
		for (int i = 0; i < fileupload.length; i++) {
			fileuploadarray[i] = (FileUpload) fileupload[i];
		}
		return fileuploadarray;
	}
}