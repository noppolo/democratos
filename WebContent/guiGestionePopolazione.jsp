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
					+ currentUser.getPopolazione() + " "+ currentUser.getIdPopolazione()%>
	</center>
	<!--  Stampa tutte le popolazioni a cui si puo' iscrivere l'utente -->
	<!-- select P.descrizione from popolazione P,appartenenza A where  A.idUtente = user.getId() and A.idPopolazione=P.id -->

	<br>
	<br>
	

		<H1>Popolazione di appartenenza</H1>
			
		<form action="CambiaPopolazioneServlet" METHOD="post" >
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
			
			<input type="radio" name="idNuovaPopolazione" value="<%=rs.getString(2)%>"			
			<% if ((currentUser.getIdPopolazione() - rs.getInt(2)) == 0 ) { %>
			checked="checked"
			<% }%>
			>
			<%=rs.getString(1)%> <br>
			<%
				}
			%>
			<%
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
		<input type="submit" value="Cambia">
	</form>
	<form type="submit" action="userLogged.jsp">
		<input type="submit" value="Torna a main">
	</form>
	
</body>


</body>
</html>