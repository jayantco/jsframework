package com.js.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;

import com.js.action.JSAction;
import com.js.action.JSBean;
import com.js.action.JSErrors;
import com.js.action.JSForward;
import com.js.asset.FileUpload;
import com.js.asset.FileUploadConfig;
import com.js.asset.ParseMultipartData;
import com.js.bean.RequestInfo;
import com.js.controller.appconfig.ModuleConfig;


/**
 * Servlet implementation class ActionController
 */
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModuleConfig config;
	private FileUpload fileupload[];
	private JSErrors errors;
	private HashMap<String, JSForward> globalforward;
	private Processor process = null;
	
	private ModuleConfig switchconfig;
	private ParseMultipartData multipart;
	private FileUploadConfig uploadconfig;
	private HashMap<String, InputStream> is;
	private Enumeration<String> initparams;
	private Object[] req_file_name;
	private String module;

	public ActionController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		int i = 0;
		is = new HashMap<String, InputStream>();
		multipart = new ParseMultipartData();
		String dbconfigfile = getInitParameter("dataconfig");
		InputStream dbis = this.getClass().getResourceAsStream(
				"/../" + dbconfigfile);

		try {
			process = new Processor();
			

			initparams = getInitParameterNames();

			while (initparams.hasMoreElements()) {
				String initparamname = initparams.nextElement();
				if (initparamname.indexOf("fconfig") > -1) {
					is.put(initparamname,
							this.getClass().getResourceAsStream(
									"/../" + getInitParameter(initparamname)));

				}
			}
			req_file_name = is.keySet().toArray();
			config = new ModuleConfig(is);
			globalforward = config.getGlobalForward();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSBean bean = null;
		RequestInfo req = null;
		String path = request.getServletPath();
		module = request.getParameter("module");
		String req_module = null;

		for (int i = 0; i < req_file_name.length; i++) {
			String initpara = (String) req_file_name[i];

			if ((module != null && module.length() > 0)
					&& initpara.indexOf(module) > -1) {
				req_module = "fconfig/" + module;
			}
		}

		path = path.substring(path.lastIndexOf("/") + 1, path.length() - 3);

		if (req_module != null && req_module.length() > 0) {
			req = config.getRequestInfo(req_module, path);
		} else {
			req = config.getRequestInfo("fconfig", path);
		}

		RequestDispatcher requestdispatch = null;

		if (globalforward != null && globalforward.size() > 0) {
			process.setGlobalForwards(globalforward);
		}
		String beanclass = req.getBeanclass();
		process.setRequest(req);
		if (path.equals("switch")) {
			request.setAttribute("switchmodulereq", req);

		}
		// if request is multipart
		if (ServletFileUpload.isMultipartContent(request)) {

			uploadconfig = req.getUpload();
			// add multipart content into request

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(uploadconfig.getMaxmemsize());
			factory.setRepository(new File(uploadconfig.getTmpstoragepath()));

			ServletFileUpload upload = new ServletFileUpload(factory);

			fileupload = multipart.setMultipartFiles(request, factory, upload);

		}

		if (beanclass != null) {
			bean = getBeanObject(beanclass, request);

			if (req.isValidate().equals("true")) {
				errors = bean.validate();
				if (errors.getElement() > 0) {

					request.setAttribute("error", errors);
					String errorpage = req.getInputPage();

					if (errorpage != null && errorpage.length() > 0) {
						requestdispatch = request
								.getRequestDispatcher(errorpage);

						requestdispatch.forward(request, response);
					} else {
						throw new ServletException(
								"please give validation error page");
					}
				} else {
					getForwardPage(request, response, requestdispatch, req,
							process, bean);
				}
			} else {
				getForwardPage(request, response, requestdispatch, req,
						process, bean);
			}
		} else {
			getForwardPage(request, response, requestdispatch, req, process,
					bean);
		}
	}

	private void getForwardPage(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher requestdispatch,
			RequestInfo req, Processor process, JSBean bean)
			throws ServletException, IOException {
		JSForward jsforward = null;
		String forwardpage = null;
		String actionclass = req.getActionclass();
		JSAction action = getActionObject(actionclass);
		if (uploadconfig != null) {
			action.setUploadConfig(uploadconfig, "true");
		}
		if (action != null) {
			jsforward = action.process(bean, process, request, response);
		}
		if (req.getForward() == null || req.getForward().length() == 0) {
			forwardpage = jsforward.getForwardpage();

		} else {
			String path = request.getServletPath();

			if (module != null && module.length() > 0) {
				if (path.indexOf(module) == -1) {
					forwardpage = "/" + module + "\\" + req.getForward();

				} else {
					forwardpage = req.getForward();

				}
			} else
				forwardpage = "/" + req.getForward();

		}

		requestdispatch = request.getRequestDispatcher(forwardpage);
		requestdispatch.forward(request, response);
		

	}

	private JSAction getActionObject(String actionclass) {
		Object actionObj = null;
		try {
			actionObj = Class.forName(actionclass).newInstance();
		} catch (Exception e) {

		}
		return (JSAction) actionObj;
	}

	private JSBean getBeanObject(String beanclass, HttpServletRequest request) {

		Object obj = null;
		Class<?> cls = null;
		try {
			cls = Class.forName(beanclass);
			obj = cls.newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Field[] fields = cls.getDeclaredFields();
		String methodName = null;
		Method methodObj = null;
		String fieldName;
		Object fieldValue;

		for (Field f1 : fields) {

			fieldName = f1.getName();
			fieldValue = request.getParameter(fieldName);
			try {
				if (fieldValue == null) {

					fieldValue = request.getAttribute(fieldName);

				}

				methodName = "set" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);

				methodObj = cls.getDeclaredMethod(methodName, f1.getType());

				if (fieldValue != null) {
					if (f1.getType().equals(String.class)) {
						methodObj.invoke(obj, fieldValue);
					}
					if (f1.getType().equals(Byte.class)) {
						methodObj.invoke(obj, new Byte((Byte) fieldValue));
					}
					if (f1.getType().equals(Short.class)) {
						methodObj.invoke(obj, new Short((Short) fieldValue));
					}
					if (f1.getType().equals(Integer.class)) {
						methodObj
								.invoke(obj, new Integer((Integer) fieldValue));
					}
					if (f1.getType().equals(Float.class)) {
						methodObj.invoke(obj, new Float((Float) fieldValue));
					}
					if (f1.getType().equals(Long.class)) {
						methodObj.invoke(obj, new Long((Long) fieldValue));
					}
					if (f1.getType().equals(Double.class)) {
						methodObj.invoke(obj, new Double((Double) fieldValue));
					}
					if (f1.getType().equals(byte.class)) {
						methodObj.invoke(obj,
								Byte.parseByte((String) fieldValue));
					}
					if (f1.getType().equals(short.class)) {
						methodObj.invoke(obj,
								Short.parseShort((String) fieldValue));
					}
					if (f1.getType().equals(int.class)) {
						methodObj.invoke(obj,
								Integer.parseInt((String) fieldValue));
					}
					if (f1.getType().equals(float.class)) {
						methodObj.invoke(obj,
								Float.parseFloat((String) fieldValue));
					}
					if (f1.getType().equals(long.class)) {
						methodObj.invoke(obj,
								Long.parseLong((String) fieldValue));
					}
					if (f1.getType().equals(double.class)) {
						methodObj.invoke(obj,
								Double.parseDouble((String) fieldValue));
					}
					if (f1.getType().equals(String[].class)) {
						String x[] = request.getParameterValues(fieldName);

						ArrayList<String> y = null;
						if (x == null || x.length == 0) {
							try {
								y = (ArrayList<String>) request
										.getAttribute(fieldName);
								String z[] = getStringArray(y);
								methodObj.invoke(obj, new Object[] { z });
							} catch (Exception e) {

								String z[] = { (String) request
										.getAttribute(fieldName) };

								methodObj.invoke(obj, new Object[] { z });

							}
						} else {
							methodObj.invoke(obj, new Object[] { x });
						}

					}
				} else if (fileupload != null && fileupload.length > 0) {
					if (f1.getType().equals(FileUpload[].class)
							&& fileupload.length > 1) {

						methodObj.invoke(obj, new Object[] { fileupload });
					} else if (f1.getType().equals(FileUpload.class)) {
						methodObj.invoke(obj, fileupload[0]);
					}
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		fieldValue = null;

		return (JSBean) obj;
	}

	private String[] getStringArray(ArrayList<String> y) {
		Object array[] = y.toArray();
		String[] stringarr = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			stringarr[i] = (String) array[i];
		}
		return stringarr;
	}

	@Override
	public void destroy() {
		
	}

}
