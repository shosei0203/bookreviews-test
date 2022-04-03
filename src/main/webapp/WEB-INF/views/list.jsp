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
        <title>BookReviewAPP</title>
        <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css"
        />
        <link
          rel="stylesheet"
          href="https://use.fontawesome.com/releases/v6.1.1/css/all.css"
        />
        <link
          rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
        />
        <link rel="stylesheet" href="/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="/css/style.css" />

    <body>
        <div id="footerwrap">
        <nav
         class="navbar is-fixed-top"
         role="navigation"
         aria-label="main navigation"
        >
        <div class="container">
            <div class="navbar-brand">
                <a id="logo" class="navbar-item" href="#">
                    <span class="logo-triangle"></span>
                    <span>BookReview APP</span>
                </a>
            </div>
            <div class="navbar-menu">
                <div class="navbar-end">
                    <a class="navbar-item" href="logout">
                        <span class="icon has-text-orange"><i class="fas fa-sign-in-alt"></i></span>
                        <span>Log out</span>
                    </a> 
                </div>
            </div>
        </div>
    </nav>
    <div class="container">
        <table id="booklist-wrappwer">
            <div class="test">
            <tr>
                <th>No.</th>
                <th>画像</th>
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
                    <td>

                    </td>
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
    </div>
        </div>

        <p></p>
        <footer>
            <form name="newpost" method="post" action="new">
            <input type="hidden" name="<%= loginId %>" value=>
            </form>
            <p><a class="button is-link size" href="#" onclick="document.newpost.submit();return false;">新規レビュー追加 ＞＞</a></p>
        </footer>
    </body>
</html>
