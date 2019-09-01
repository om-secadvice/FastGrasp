<%-- 
    Document   : register
    Created on : Jul 14, 2017, 12:47:05 AM
    Author     : Debargha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>
            <c:choose>
                <c:when test="${userType == 'faculty'}">
                    FastGrasp | Faculty-Register
                </c:when>
                <c:when test="${userType == 'student'}">
                    FastGrasp | Student-Register
                </c:when>
            </c:choose>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8" />
        <link rel="stylesheet" href="/FastGrasp/css/bootstrap.min.css" />
        <link rel="stylesheet" href="/FastGrasp/css/main.css" />
        <script type="text/javascript" src="/FastGrasp/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="/FastGrasp/js/main.js"></script>
    </script>
</head>
<body>
    <div id="registerSection">
    
            <form action="<c:url value='login'/>" method="post">
            
                <table id="registerTable" class="formTable">
                    <tr>
                        <td>Name<span class="imp">*</span></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="username" required="required" /></td>
                    </tr>
                    <c:choose>
                        <c:when test="${userType == 'student'}">
                            <tr>
                                <td>College Roll No.</td>
                            </tr>
                            <tr>
                                <td><input type="text" name="collegeRollNo" /></td>
                            </tr>
                        </c:when>
                    </c:choose>
                    <tr>
                        <td>Contact<span class="imp">*</span></td>
                    </tr>
                    <tr>
                        <td><input type="tel" name="contact" required="required" /></td>
                    </tr>
                    <tr>
                        <td>Email<span class="imp">*</span></td>
                    </tr>
                    <tr>
                        <td><input type="email" name="email" required="required" /></td>
                    </tr>
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${userType == 'faculty'}">
                                    Institution Name
                                </c:when>
                                <c:when test="${userType == 'student'}">
                                    College Name
                                </c:when>
                            </c:choose>
                            <span class="imp">*</span>
                        </td>     
                    </tr>
                    <tr>
                        <td><input type="text" name="<c:if test="${userType == 'student'}">collegeName</c:if><c:if test="${userType == 'faculty'}">institution</c:if>" required="required" /></td>
                    </tr>
                    <c:choose>
                        <c:when test="${userType == 'faculty'}">
                            <tr>
                                <td>Designation</td>
                            </tr>
                            <tr>
                                <td><input type="text" name="designation" /></td>
                            </tr>
                        </c:when>
                        <c:when test="${userType == 'student'}">
                            <tr>
                                <td>Semester</td>
                            </tr>
                            <tr>
                                <td><input type="number" name="semester" min="1" max="8"/></td>
                            </tr>
                        </c:when>
                    </c:choose>
                    <tr>
                        <td>State<span class="imp">*</span></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="state" required="required" /></td>
                    </tr>
                    <tr>
                        <td>About Me</td>
                    </tr>
                    <tr>
                        <td><textarea rows="5" name="aboutMe" placeholder="Write something here about yourself."></textarea></td>
                    </tr>
                    <tr>
                        <td>Password<span class="imp">*</span></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="password" required="required" /></td>
                    </tr>
                    <tr>
                        <td>Re-type Password<span class="imp">*</span></td>
                    </tr>
                    <tr>
                        <td><input type="password" name="confirmPassword" required="required" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Register" /></td>
                    </tr>
                    <tr>
                        <td>Already a member?
                                <a href="<c:url value='login'/>">Login</a>
                           </td>
                    </tr>
                </table>
            </form>
    </div>
    <div class="footer">
        <p>© 2017, FastGrasp.com, Inc. or its affiliates</p>
    </div>
</body>
</html>
