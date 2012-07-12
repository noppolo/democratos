
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="webtier.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd""http://www.w3.org/TR/html4/frameset.dtd" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Logged Successfully</title>
</head>
<body>
	<center>
		<%
			UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
		%>
		Welcome
		<%=currentUser.getFirstName() + " " + currentUser.getLastName()%>			
	<form action="GestionePopolazioneServlet">
		<input type="submit" value="Cambia assemblea di appartenza">
	</form>
	<form action="IscrizionePopolazioneServlet">
		<input type="submit" value="Gestione assemblee di appartenza">
	</form>
	</center>
	
</body>
</html>