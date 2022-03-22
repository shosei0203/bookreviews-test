<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<%
    String loginId = (String)session.getAttribute("loginId");
    ArrayList<ReviewsDTO> reviews  = (ArrayList<ReviewsDTO>)request.getAttribute("reviews");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>BookReview</title>
        <style>body {padding: 30px;}</style>
    </head>
    <body>

        <h1>BOOKリスト</h1>  
        <%  if (request.getAttribute("message") == null) {}
            else{ %>
        <%= request.getAttribute("message") %> <% } %>

        <table id="hoge">
            <tr>
                <th>No.</th>
                <th>ID</th>
                <th>タイトル</th>
                <th>評価</th>
            </tr>
        <% 
            for (ReviewsDTO review : reviews) {
        %>
                <tr>
                    <td>
                        <script type="text/javascript"> 
                        document.write(hoge.rows.length-1);
                        </script>
                    </td>
                    <td><%= review.getPostId() %></td>
                    <td>
                        <% int showPostId = review.getPostId(); %>
                        <% String strShowPostId = String.valueOf(showPostId); %>
                        <form name="<%='a'+strShowPostId%>" method="post" action="show">
                        <input type="hidden" name="postId" value="<%= showPostId %>">
                        </form>
                        <a href="#" onclick="document.<%='a'+strShowPostId %>.submit();return false;"><%= review.getTitle() %></a>
                    </td>
                    <td><%= review.getStars() %></td>
                </tr>
            <% } %>
        </table>

        <p></p>
        <form name="newpost" method="post" action="new">
        <input type="hidden" name="<%= loginId %>" value=>
        </form>
        <p><a href="#" onclick="document.newpost.submit();return false;">新規レビュー</a></p>
        <p><a href="logout">ログアウト</a></p>   
    </body>
</html>
