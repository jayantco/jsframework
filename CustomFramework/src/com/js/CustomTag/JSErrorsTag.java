package com.js.CustomTag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import com.js.action.JSError;
import com.js.action.JSErrors;

public class JSErrorsTag implements BodyTag {
	private PageContext context;
	private BodyContent content;

	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		JspWriter out = content.getEnclosingWriter();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		JSErrors errors = (JSErrors) request.getAttribute("error");
		String errorkey = content.getString();
		errorkey = errorkey.trim();
		if (errors!=null) {
			try {
				if (errorkey != null && errorkey.length() > 0) {
				JSError error = errors.getError(errorkey);
			
						if(error!=null){
							out.println(error.getError());
					}
				} else {
					out.println("error key did not match please give error key same as Error key in validation");
				}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
		}
		return 0;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_PAGE;
	}

	@Override
	public void doInitBody() throws JspException {
		// TODO Auto-generated method stub

	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBodyContent(BodyContent arg0) {
		// TODO Auto-generated method stub
		this.content = arg0;

	}

	@Override
	public void setPageContext(PageContext arg0) {
		// TODO Auto-generated method stub
		this.context = arg0;
	}

	@Override
	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub

	}
}
