<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
 <% 

  int showPostId = ((Integer)(request.getAttribute("postId"))).intValue();
 
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>BookReview</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <h1>レビュー編集</h1>
        <% String message = (String)request.getAttribute("message");%>
        <p><%= message %></p>
        <% ArrayList<String> errorMessage = (ArrayList<String>)request.getAttribute("errorMessage");%>
        <% for (String errorMsg : errorMessage) { %>
        <p><%= errorMsg %></p>
        <% } %>
        <form action='update' method='post'>
            <input type='hidden' name='postId' value='<%= request.getAttribute("postId") %>'>
            <label for='title'>タイトル</label><br>
            <input type='text' name='title' value='<%= request.getAttribute("title") %>'>
            <p></p>
            <label for='content'>本文</label><br>
            <textarea name='content' cols='40' rows='10'><%= request.getAttribute("content") %></textarea>
            <p></p>
            <label for='stars'>★</label><br>
            <input type='number' name='stars' value='<%= request.getAttribute("stars") %>'>
            <p></p>
            <button type='submit'>保存する</button>
        </form>
        <form name="showback" method="post" action="show">
        <input type="hidden" name="postId" value="<%= showPostId %>">
        </form>
        <p><a href="#" onclick="document.showback.submit();return false;">キャンセル</a></p>
    </body>
</html>