<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADA</title>
</head>

<body> 
<form action ="FileReader1" enctype="multipart/form-data" method="post">
<input type="file" placeholder="UPLOAD FILE 1" name="File1">
<input type="submit" value="Submit">
</form>
<form action ="FileReader2" enctype="multipart/form-data" method="post">
<input type="file" placeholder="UPLOAD FILE 2" name="File2">
<input type="submit" value="Submit">
</form>
<%
	Double Result=null;
	Result = (Double)session.getAttribute("Result"); 
	if(Result!=null){
		out.println(Result);
	}else{}
    %>
  <%	
         Integer hitsCount = (Integer)application.getAttribute("hitCounter");
         if( hitsCount ==null || hitsCount == 0 ) {
            hitsCount = 1;
         } else {
            hitsCount += 1;
         }
         application.setAttribute("hitCounter", hitsCount);
      %>
      <center>
         <p>Total number of visits: <%= hitsCount%></p>
      </center>
   
 <%
      GregorianCalendar cal = new GregorianCalendar();
      out.print(cal.get(Calendar.YEAR));
    %>

   
</body>
</body>
</html>