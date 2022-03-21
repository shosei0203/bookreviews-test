<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>新規ユーザー登録</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <%  if (request.getAttribute("message") == null) {}
        else{ %>
        <%= request.getAttribute("message") %> <% } %>

        <h1>ユーザー登録</h1>
        <form action='userCreate' method='post'>
            <label for='loginId'>ログインID：</label>
            <input type='text' name='loginId' value=''>
            <p></p>
            <label for='pass'>パスワード：</label>
            <input type='password' name='pass' value=''>
            <p></p>
            <label for='check'>パスワード：</label>
            <input type='password' name='check' value=''>
            <p></p>
            <button type='submit'>新規登録</button>
            <a href='https://quiet-island-17618.herokuapp.com/index.jsp'>戻る</a>
        </form>
    </body>
</html>