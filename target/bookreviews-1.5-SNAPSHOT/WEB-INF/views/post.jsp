<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
 <% 

  int showPostId = ((Integer)(request.getAttribute("postId"))).intValue();
 
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>本棚APP</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>
        <h1>BOOKレビュー</h1>
        <% String message = (String)request.getAttribute("message");%>
        <p><%= message %></p>

        <p>タイトル：<%= request.getAttribute("title") %></p>
        <p>本文：</p>
        <p><%= request.getAttribute("content") %></p>
        <p></p>
        <p>評価：</p>
        <p><%= request.getAttribute("stars") %></p>
        <p></p>
        <p>
            <a href="list">戻る</a>
            </form>
            <form name="inEdit" method="post" action="edit">
            <input type="hidden" name="postId" value="<%= showPostId %>">
            </form>
            <p><a href="#" onclick="document.inEdit.submit();return false;">編集する</a></p>
            <form name="postDelete" method="post" action="delete">
            <input type="hidden" name="postId" value="<%= showPostId %>">
            </form>
            <p><a href="#" onclick="document.postDelete.submit();return false;">削除する</a></p>
        </p>
    </body>
</html>
