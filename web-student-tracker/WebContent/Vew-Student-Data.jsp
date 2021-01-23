<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.*,com.luv2code.web.jdbc.*" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Tracker App</title>

<link type="text/css" rel = "stylesheet" href = "css/style.css" >

</head>


<body>

	<div id = "wrapper">
		<div id = "header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id = "container">
		<div id = "content">
		
<!-- 		<input type = "button" value = "Add Student"
			onclick = "window.location.herf = 'add-student-form.jsp'; return false;"
			class = "add-student-button"
		 /> -->
		 <a href = "add-student-form.jsp" >Add Student</a>
		 
		 </br></br>
		
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
					
			<c:forEach var = "tempStudent" items="${student_list}" >
			
			<c:url var = "tempLink" value = "StudentControllerServlet" >
				<c:param name="command" value = "LOAD" ></c:param>
				<c:param name="studentId" value = "${tempStudent.id}" ></c:param>
			</c:url>
			
			<c:url var = "deleteLink" value = "StudentControllerServlet" >
				<c:param name="command" value = "DELETE" ></c:param>
				<c:param name="studentId" value = "${tempStudent.id}" ></c:param>
			</c:url>
			
			<tr>
				<td>${tempStudent.firstName}</td>
				<td>${tempStudent.lastName}</td>
				<td>${tempStudent.email}</td>
				<td><a href = "${tempLink}" >Update</a> | <a href = "${deleteLink}" 
					onclick = "if(!(confirm('Are you sure you want to delete this student ?'))) return false" >
				Delete</a> </td>
			</tr>
			
			</c:forEach>
			
		</table>
		</div>
	</div>

</body>
</html>