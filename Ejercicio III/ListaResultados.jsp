<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id='resultados' scope='session' class='java.util.ArrayList' />  
	 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1> Lista Resultados</h1>
  <h2>Alumno con ci: "${cedula}"</h2>
  <table border='1'>
				<tr >
				    <th>   </th>
				    <th> Codigo  </th>
				    <th> Calificacion  </th>
				</tr>
				 
				<c:forEach items="${resultados}" var ="i">
				  <tr>
				    <td> ${i.getCodigo()}</td>
				    <td> ${i.getCalificacion()}</td>
				  </tr>
				</c:forEach > 
				
		  </table>
		  <p  class= "return">
		    <a href="Index.jsp">Volver al inicio</a>   
		  </p>
  

</body>
</html>