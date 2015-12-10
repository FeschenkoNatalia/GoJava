<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp">
	<jsp:param name="title" value="Payment" />
</jsp:include>

<h3><a href="categories">Kickstarter</a>
/
<a href="projects?id=${category.id}">${category.name}</a></h3>
<h1><a href="project?id=${project.id}">${project.name}</a></h1>

<h3>Amount of your donation is $${amount}</h3>

<form action="paymentCheck" method="post">
Enter your name: <input type="text" name="name" value="" size=15 maxlength=20><br>
<br>Enter your card's number: <input type="text" name="card" value="card's number"><br>
<input type="hidden" name="projectId" value="${project.id}"/>
<input type="hidden" name="amount" value="${amount}"/>
<br><input type="submit" value="Submit">
</form>

<jsp:include page="footer.jsp" />