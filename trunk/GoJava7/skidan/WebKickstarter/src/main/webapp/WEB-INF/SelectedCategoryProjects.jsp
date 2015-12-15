<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SelectedCategoryProjects.jsp</title>
</head>
<body>

<h1>Projects Available in this Category </h1>

	<div id=3>

		<c:forEach items="${projectList}" var="project">
			<a href=http://localhost:8080/WebKickstarter/SingleProjectServlet?projectId=${project.getId()}><c:out
					value="${project.getTitle()}" /></a><br>
				Project description:<c:out value="${project.getDiscription()}" /><br>
					   Required Sum:<c:out value="${project.getRequiredSum()}" /><br>
					   Gained Sum:<c:out value="${project.getGainedSum()}" /><br>
					   Days Left till end :<c:out value="${project.getDaysLeft()}" /><br>
        </c:forEach>

	</div>




</body>
</html>