package com.js.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class JSConfiguration  implements JSSession{
	private InputStream is;
	private ArrayList<InputStream> jsconfig = new ArrayList<InputStream>();
	private DataJSConfig dbconfig;

	public JSConfiguration() {
		// TODO Auto-generated constructor stub
		is = this.getClass()
				.getResourceAsStream("../../../" + "DataConfig.xml");
		
		}

	public JSConfiguration(String filename)
	{
		is = this.getClass().getResourceAsStream("../../../"+filename);
			
	}
	
	public void configure()
	{
		try {
			
			dbconfig = new DataJSConfig(is);
			setDbconfig(dbconfig);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataJSConfig getDbconfig() {
		return dbconfig;
	}

	private void setDbconfig(DataJSConfig dbconfig) {
		this.dbconfig = dbconfig;
	}

	public JSSession getSession()
	{
		JSSession session = new JSSession() {
			
			@Override
			public void save() {
				// TODO Auto-generated method stub
				
			}
		};
		return session;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
}
