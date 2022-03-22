<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
    String loginId = (String)session.getAttribute("loginId");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>BookReview</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <h1>ユーザー登録完了画面</h1>

        <%  if (request.getAttribute("message") == null) {}
        else{ %>
        <%= request.getAttribute("message") %> <% } %>       

        <p>登録しました</p>
        <p>セッション<%= loginId %></p>
        
        <form name="listlogin" method="post" action="list">
        <input type="hidden" name="<%= loginId %>" value=>
        </form>

        <p><a href="#" onclick="document.listlogin.submit();return false;">ログイン</a></p>
        <p><a href="https://quiet-island-17618.herokuapp.com/index.jsp">ログイン画面へ戻る</a></p>
    </body>
</html>
