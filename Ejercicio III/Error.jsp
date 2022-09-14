<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id='error' scope='session' class='java.lang.String' />  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h1>Error</h1>
 <h3>"${error}"</h3>
 <p  class= "return">
   <a href="Index.jsp">Volver al inicio</a>   
 </p>
</body>

</html>