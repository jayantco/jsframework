package com.js.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.rowset.serial.SerialArray;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.js.db.DataJSConfig;

public class DataJSConfig extends DefaultHandler {
	private ArrayList<DataJSConfigBean> dblist = new ArrayList<DataJSConfigBean>();
	private DataJSConfigBean mappingconfig = null;
	private String content;

	public DataJSConfig(InputStream is) throws ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated constructor stub
		SAXParserFactory sax = SAXParserFactory.newInstance();
		SAXParser sp = sax.newSAXParser();
		sp.parse(is, this);
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		// TODO Auto-generated method stub
		if ("js-mapping".equals(arg2)) {
			mappingconfig = new DataJSConfigBean();
			
		}
	}

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		content = new String(arg0, arg1, arg2);
		content = content.trim();
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		if ("js-mapping".equals(arg2)) {
			dblist.add(mappingconfig);
		}
		if ("dbdriverclass".equals(arg2)) {
			mappingconfig.setDriverclass(content);
		}
		if ("dburl".equals(arg2)) {
			mappingconfig.setUrl(content);
		}
		if ("dbusername".equals(arg2)) {
			mappingconfig.setUsername(content);
		}
		if ("dbpassword".equals(arg2)) {
			mappingconfig.setPassword(content);
		}
		if ("dbpoolsize".equals(arg2)) {
			mappingconfig.setPoolsize(content);
		}
		if ("dbconnection-type".equals(arg2)) {
			mappingconfig.setDbtype(content);

		}
		if ("table-creation".equals(arg2)) {
			mappingconfig.setTable_creation(content);
		}
		if ("show-sql".equals(arg2)) {
			mappingconfig.setShow_sql(content);
		}
		if ("jbm-file".equals(arg2)) {
			mappingconfig.setJbm_file(content);
		}
		
	}

	public ArrayList<DataJSConfigBean> getDbinfo() {
		System.out.println(dblist);
		return dblist;
	}
}
