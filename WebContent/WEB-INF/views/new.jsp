<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>BookReview</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <h1>BOOKレビュー</h1>
        <% String message = (String)request.getAttribute("message");%>
        <p><%= message %></p>
        <% ArrayList<String> errorMessage = (ArrayList<String>)request.getAttribute("errorMessage");%>
        <% for (String errorMsg : errorMessage) { %>
        <p><%= errorMsg %></p>
        <% } %>
        <% int postId = (Integer)request.getAttribute("postId");%>
        <p><%= postId  %>投稿目</p>
        <% String loginId = (String)request.getAttribute("personId");%>
        <p>ユーザーID：<%= loginId %></p>

        <form action='create' method='post'>
            <input type='hidden' name='postId' value= <%= postId %>>
            <input type='hidden' name='loginId' value= <%= loginId %>><br>
            <label for='title'>タイトル</label><br>
            <input type='text' name='title' value=''>
            <p></p>
            <label for='content'>本文</label><br>
            <textarea name='content' cols='40' rows='10'></textarea>
            <p></p>
            <label for='stars'>★</label><br>
            <input type='number' name='stars' value='0'>
            <p></p>
            <button type='submit'>保存する</button>
            <!--<a href='list'>キャンセル</a>-->
        </form>
        <form name="listback" method="post" action="list">
        <input type="hidden" name="" value=>
        </form>
        <p><a href="#" onclick="document.listback.submit();return false;">キャンセル</a></p>
    </body>
</html>