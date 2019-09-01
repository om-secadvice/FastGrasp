<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="entity.Test"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date"/>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Tests</title>
        <link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <script src="/FastGrasp/js/jquery-1.11.2.min.js" type="text/javascript"></script>
        <script src="/FastGrasp/js/bootstrap.js" type="text/javascript"></script>

    </head>

    <body>
        <div class="container">
            <div class="alert alert-info alert-dismissable"><a class="panel-close close" data-dismiss="alert">×</a> <em class="fa fa-coffee"></em> This is an <strong>.alert</strong>. Use this to show important messages to the user. </div>

            <form class="form-horizontal" role="form" action="myTests">
                <div class="row">
                    <div class="col-md-10">
                        <div class="ui-select">
                            <select class="form-control" name="courseId">
                                <c:if test="${!empty selectedCourse}">
                                    <option value="${selectedCourse.id}">${selectedCourse.title}</option>
                                </c:if>
                                <c:forEach var="course" items="${myCourses}">
                                    <c:if test="${course.id!=selectedCourse.id}">
                                        <option value="${course.id}">${course.title}</option>
                                    </c:if>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" value="Refresh" class="btn btn-info"/>
                    </div>
                </div>
            </form>

            <br/>

            <div class="panel-group" id="accordion1">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">Pending Tests</a></h4>
                    </div>
                    <div id="collapseOne1" class="panel-collapse collapse in">
                        <% 
                            Collection<Test> tests=(Collection<Test>)session.getAttribute("myTests");
                            Iterator<Test> itr=tests.iterator();
                            Test myTest;
                            while(itr.hasNext()){
                                myTest=itr.next();
                                pageContext.setAttribute("id", myTest.getId());
                                if(!myTest.getDueDateBool()){
                            %>
                            

                            <div class="row">
                                <div class="col-md-4">
                                    <div class="panel-body">
                                        <%=myTest.getTitle()%>                               </div>
                                </div>
                                <div class="col-md-4" style="text-align: right">
                                    <div class="panel-body">
                                        <b>Due On:</b><%=myTest.getDueDate()%>
                                    </div>
                                </div>
                                <div class="col-md-4" style="text-align: right">
                                    <div class="panel-body">
                                        
                                        <a href="<c:url value='takeTest?testId=${id}'/>" class="btn btn-success">Take Test</a>
                                    </div>
                                </div>
                            </div>
                                        <% }}                                        
                                        %>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1">Completed Tests</a></h4>
                    </div>
                   
                        
                        <div id="collapseTwo1" class="panel-collapse collapse">
                             <%  Iterator<Test> itr1=tests.iterator();
                                 while(itr1.hasNext()){
                        
                        myTest=itr1.next();
                        if(myTest.getDueDateBool()){
                    %>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="panel-body">
                                        <b> <%=myTest.getTitle()%>   </b>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="panel-body">
                                        <b><%=myTest.getObtained()%></b>
                                    </div>
                                </div>
                            </div>
                        </div>
                                    
                       <%}}
                       %>
                </div>
            </div>
        </div>

    </body>
</html>
