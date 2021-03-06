<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Курсы</h1>
	<%
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");
		if (search==null || search.trim().isEmpty())
			search="";
		
	
	%>
	<form method="POST">
		<input type=text name=search value="<%=search%>">
		<input type=submit value=Поиск>
	</form>	
	
	<%
	String DRIVER_NAME = application.getInitParameter("DRIVER_NAME");
	//"com.mysql.jdbc.Driver";
	String CONNECTION_STRING = application.getInitParameter("CONNECTION_STRING");
	//"jdbc:mysql://localhost:3306/web?user=root&password=demo";
	
	Class.forName(DRIVER_NAME);

	try(Connection conn  = DriverManager.getConnection(CONNECTION_STRING))
	{
		
		String sql = "SELECT title, length,description FROM courses " 
				+ "WHERE title LIKE ? "
				+ "ORDER BY title";
		PreparedStatement cmd = conn.prepareStatement(sql);
		cmd.setString(1, "%"+search+"%");
		
		ResultSet result = cmd.executeQuery();
		out.print("<table border=1>");
		
		while( result.next() ) {
			String title = result.getString("title");
			int length   = result.getInt("length");
			String description = result.getString("description");
			out.print("<tr>");
			out.print("<td>");
			out.print(title);
			out.print("</td>");
			out.print("<td>");
			out.print(length);
			out.print("</td>");
			out.print("<td>");
			out.print(description);
			out.print("</td>");
			out.print("</tr>");
			
		}
		out.print("</table>");
		
	} 
	%>

</body>
</html>