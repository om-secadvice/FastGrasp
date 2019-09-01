<%-- 
    Document   : login
    Created on : Jul 13, 2017, 10:26:01 PM
    Author     : Debargha
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
        <title>
            <c:choose>
                <c:when test="${sessionScope.userType == \"faculty\"}">
                    FastGrasp | Faculty-Login
                </c:when>
                <c:when test="${sessionScope.userType == \"student\"}">
                    FastGrasp | Student-Login
                </c:when>
            </c:choose>
        </title>
        <link rel="stylesheet" href="/FastGrasp/css/bootstrap.min.css" />
        <link rel="stylesheet" href="/FastGrasp/css/main.css" />
        <script type="text/javascript" src="/FastGrasp/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="/FastGrasp/js/main.js"></script>
    </head>
    <body>
        <div id="loginSection">
            <form action="dashboard" method="post">
              <table id="loginTable" class="formTable">
                <tr>
                  <td>Email</td>
                </tr>
                <tr>
                  <td><input type="email" name="email" required="required" /></td>
                </tr>
                <tr>
                  <td>Password</td>
                </tr>
                <tr>
                  <td><input type="password" name="password" required="required" /></td>
                </tr>
                <tr>
                  <td><input type="submit" value="Login" /></td>
                </tr>
                <tr>
                  <td><a href="<c:url value='register' />">New User? Create Account.</a></td>
                </tr>
              </table>
            </form>
        </div>
        <div class="footer">
          <p>© 2017, FastGrasp.com, Inc. or its affiliates</p>
        </div>
  </body>
</html>