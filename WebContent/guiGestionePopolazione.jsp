<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="webtier.UserBean" import="java.sql.*"	import="dbtier.ConnectionManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<%
			UserBean currentUser = (UserBean) (session
					.getAttribute("currentSessionUser"));
		%>
		Welcome
		<%=currentUser.getFirstName() + " "
					+ currentUser.getLastName() + " you are "
					+ currentUser.getPopolazione() + " "%>
	</center>
	<!--  Stampa tutte le popolazioni a cui si puo' iscrivere l'utente -->
	<!-- select P.descrizione from popolazione P,appartenenza A where  A.idUtente = user.getId() and A.idPopolazione=P.id -->

	<br>
	<br>
	
<!-- 	<form method="post" name="form">
		<table border="1"> -->
		<form action="CambiaPopolazioneServlet">
		<fieldset>
			<legend>Popolazione di appartenenza</legend>
			<%				
				int sumcount = 0;
				Statement st;
				try {
					Connection con = ConnectionManager.getConnection();
					
					String query = "select P.descrizione,P.id from popolazione P,appartenenza A where  A.idUtente = "
							+ currentUser.getId() + " and A.idPopolazione=P.id";
					st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
			%>
			<%
				while (rs.next()) {
			%>

			<%=rs.getString(1)%> <input type="radio" name="idNuovaPopolazione" value="<%=rs.getString(2)%>"			
			<% if (currentUser.getId() == rs.getInt(2)) { %>
			checked="checked"
			<% }%>
			/><br>
			<%
				}
			%>
			<%
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
		</fieldset>
	</form>
</body>


</body>
</html>