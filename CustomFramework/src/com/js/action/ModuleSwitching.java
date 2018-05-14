package com.js.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.bean.RequestInfo;
import com.js.controller.Processor;

public class ModuleSwitching extends JSAction{
	@Override
	public JSForward process(JSBean bean, Processor process,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		RequestInfo req = (RequestInfo)request.getAttribute("switchmodulereq");
		
		if(req!=null)
		{
			
			String forward = request.getParameter("page");
			req.setForward(forward);
		}
		return super.process(bean, process, request, response);
	}
}
