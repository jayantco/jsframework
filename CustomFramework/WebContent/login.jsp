<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="html" uri="/WEB-INF/custom.tld"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="login.do" method="post" enctype="multipart/form-data">
username:
	<input type="text" name="username" value="${param.username}"/></br>
	<html:error>username</html:error></br>
password:
	<input type="password" name="password"/></br>	
	<html:error>password</html:error></br>
Education:
	 <select name="education" multiple="multiple">
	 	<option value="BE">BE</option>
	 	<option value="MCA">MCA</option>
	 	<option value="BTECH">BTech</option>
	 </select></br>
	 <html:error>education</html:error></br>
Resume:
	<input type="file" name="resume"/></br>
	
	<html:error>file</html:error></br>
<input type="submit"/></form>
</body>
</html>