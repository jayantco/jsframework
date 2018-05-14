package com.js.controller.appconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.js.action.JSForward;

import com.js.asset.FileUploadConfig;
import com.js.bean.RequestInfo;

public class ModuleConfig extends DefaultHandler {
	private RequestInfo req = null;
	private JSForward forward = null;
	private String content;
	private HashMap<String, HashMap<String, RequestInfo>> configinfos = new HashMap<String, HashMap<String, RequestInfo>>();
	private String beanclass;
	private String actionclass;
	private String configname;
	private HashMap<String, JSForward> gforwards = new HashMap<String, JSForward>();
	private String forwardname = null;
	private FileUploadConfig uploadconfig = null;
	private String reqpath;
	private HashMap<String, RequestInfo> infos;

	public ModuleConfig(HashMap<String, InputStream> is)
			throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated constructor stub
		SAXParserFactory sax = SAXParserFactory.newInstance();
		SAXParser sp = sax.newSAXParser();
		Set<String> configkey = is.keySet();
		for (String string : configkey) {
			infos = new HashMap<String, RequestInfo>();
			InputStream configis = is.get(string);
			sp.parse(configis, this);
			configinfos.put(string, infos);
		}
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		// TODO Auto-generated method stub
		if ("request-info".equals(arg2)) {
			req = new RequestInfo();
			req.setForward(arg3.getValue("forward"));
		}
		if ("forward".equals(arg2)) {
			forward = new JSForward();
			forwardname = arg3.getValue("name");
		}
		if ("file-upload".equals(arg2)) {
			uploadconfig = new FileUploadConfig();
		}
		if ("global-forward".equals(arg2)) {

			forward = new JSForward();
			forwardname = arg3.getValue("name");
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
		if ("request-info".equals(arg2)) {
			infos.put(reqpath, req);

		}
		if ("path".equals(arg2)) {
			req.setPath(content);
			reqpath = content;
		}
		if ("bean-class".equals(arg2)) {
			req.setBeanclass(content);
		}
		if ("action-class".equals(arg2)) {
			req.setActionclass(content);
		}
		if ("forward".equals(arg2)) {
			forward.setForwardpage(content);
			req.putForward(forwardname, forward);

		}
		if ("validate".equals(arg2)) {
			req.setValidate(content);
		}
		if ("errorpage".equals(arg2)) {
			req.setInputPage(content);
		}
		if ("storefilepath".equals(arg2)) {
			uploadconfig.setFilestoragepath(content);
		}
		if ("tmpfilepath".equals(arg2)) {
			uploadconfig.setTmpstoragepath(content);
		}
		if ("maxfilesize".equals(arg2)) {
			uploadconfig.setMaxfilesize(Integer.parseInt(content));
		}
		if ("maxmemsize".equals(arg2)) {
			uploadconfig.setMaxmemsize(Integer.parseInt(content));
		}

		if ("file-upload".equals(arg2)) {
			req.setUpload(uploadconfig);
		}
		if ("global-forward".equals(arg2)) {
			forward.setForwardpage(content);
			gforwards.put(forwardname, forward);
		}
	}

	public RequestInfo getRequestInfo(String configfile, String path) {

		HashMap<String, RequestInfo> configinfo = configinfos.get(configfile);
		return configinfo.get(path);
	}

	public HashMap<String, JSForward> getGlobalForward() {
		return gforwards;
	}

}
