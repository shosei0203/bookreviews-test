<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
 <%  int showPostId = ((Integer)(request.getAttribute("postId"))).intValue(); %>
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
        <div class="post-body">
            <div class="container">
                <div class="post-field">
                    <div class="form-field">
                        <br>
                        <% String message = (String)request.getAttribute("message");%>
                        <p><%= message %></p>
                        <table>
                            <tr>
                                <th>
                                    <br>タイトル　<br>
                                </th>
                                <td>
                                    <br>
                                    <%= request.getAttribute("title") %>
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <br>本文 <br>
                                </th>
                                <td>
                                    <br>
                                    <%= request.getAttribute("content") %>
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <p>★</p> <br>
                                </th>
                                <td>
                                    <%= request.getAttribute("stars") %>
                                    <br>
                                </td>
                            </tr>
                        </table>
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div class="container">
            <form name="postDelete" method="post" action="delete">
            <input type="hidden" name="postId" value="<%= showPostId %>">
            </form>
            <p><a class="button is-danger size button-delete" href="#" onclick="document.postDelete.submit();return false;">削除する</a></p>

            <form name="inEdit" method="post" action="edit">
            <input type="hidden" name="postId" value="<%= showPostId %>">
            </form>
            <p><a class="button is-link size button-post" href="#" onclick="document.inEdit.submit();return false;">編集する</a></p>

            <a class="button is-white size button-cancel" href="list">戻る</a>
            </div>
        </p>
    </body>
</html>