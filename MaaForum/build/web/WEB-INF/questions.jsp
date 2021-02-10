<%-- 
    Document   : questions
    Created on : 5 févr. 2021, 13:42:20
    Author     : Maamoune
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.supmanagement.MaaForum.entity.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forum</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <style>
            .avatar{
                max-width: 64px;
                max-height: 64px;
                border-radius: 50%;
            }
            a.a-unstil, a.a-unstil:hover{
                text-decoration: none;
                color: black;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <a href="questions" class="a-unstil" ><h1 class="display-4">Maa Forum</h1></a>
            <h2 class="h3">Poser une question</h2>
            <form method="POST">
                <div class="row mt-3">
                    <div class="col-2">
                        <input type="text" name="author" placeholder="Pseudo ..." class="form-control" />
                    </div>
                    <div class="col-8">
                        <div class="form-group" >
                            <input type="text" name="title" placeholder="Question ..." class="form-control" />
                        </div>
                    </div>
                    <div class="col">
                        <input type="submit" name="newQuestion" value="Soumettre" class="btn btn-primary btn-block" />
                    </div>
                </div>
            </form>
            
            <h2 class="h3 mt-4">Les questions posée (${count}):</h2>
           
            <c:forEach items="${questions}" var="question" >
                <a href="answers?question=${question.id}" class="a-unstil" >
                    <div class="row mt-3">
                        <div class="col-1">
                            <img src="https://www.ltc.lu/images/Contact/no_user_picture.jpg" alt="avatar" class="avatar"/>
                        </div>
                        <div class="col bg-light">
                            <h2 class="h5">${question.author}</h2>
                            <p>
                                ${question.title}
                                <span class="d-block float-right mt-2">${question.createdAtFormated} à ${question.timeFormated}</span>
                            </p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </body>
</html>
