<%@ page import="ua.com.goit.gojava7.kickstarter.domain.Category" %>
<%@ page import="ua.com.goit.gojava7.kickstarter.domain.Quote" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kickstarter</title>
</head>
<body>
<img src="../img/logo.png" />
<br />
<%
    Quote quote = (Quote)(request.getAttribute("quote"));
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<i><%= quote.getText() %> (<%= quote.getAuthor() %>)</i>
<h3>Categories</h3>
<ul>
<%
    for (int i = 0; i < categories.size(); i++) {
%>
<li><a href="category?id=<%= i %>"><%= categories.get(i).getName() %></a></li>
<%
    }
%>
</ul>
</body>
</html>