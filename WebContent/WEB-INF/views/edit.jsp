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
             aria-label="main navigation">
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
                            <% ArrayList<String> errorMessage = (ArrayList<String>)request.getAttribute("errorMessage");%>
                            <% for (String errorMsg : errorMessage) { %>
                            <p><%= errorMsg %></p>
                            <% } %>
                            <form action='update' method='post'>
                                <table>
                                    <input type='hidden' name='postId' value='<%= request.getAttribute("postId") %>'>
                                    <input type='hidden' name='loginId' value='<%= request.getAttribute("personId") %>'>
                                    <tr>
                                        <th>
                                            <br><label for='title'>タイトル　<br></label>
                                        </th>
                                        <td>
                                            <br>
                                            <input class="input is-medium" type='text' name='title' value='<%= request.getAttribute("title") %>'>
                                            <br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <br><label for='content'>本文 <br></label>
                                        </th>
                                        <td>
                                            <br>
                                            <textarea class="textarea is-medium" name='content' cols='40' rows='10'><%= request.getAttribute("content") %></textarea>
                                            <br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            <label for='stars'>★</label> <br></label>
                                        </th>
                                        <td>
                                            <input class="input is-medium"   type='number' name='stars' value='<%= request.getAttribute("stars") %>'>
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
                        <button class="button is-link size button-post" type='submit'>編集を保存する</button>
                    </form>
                    <form name="showback" method="post" action="show">
                        <input type="hidden" name="postId" value="<%= showPostId %>">
                    </form>
                    <a class="button is-white size button-cancel" href="#" onclick="document.showback.submit();return false;">戻る</a>
                </div>
            </footer>
    </body>
</html>