<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>本棚APP</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
         <%  if (request.getAttribute("message") == null) {}
            else{ %>
         <%= request.getAttribute("message") %> <% } %>

        <div>
           <a href="https://quiet-island-17618.herokuapp.com/index.jsp">戻る</a>
        </div>
    </body>
</html>