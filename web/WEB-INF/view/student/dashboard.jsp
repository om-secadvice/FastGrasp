<%-- 
    Document   : dashboard
    Created on : 12 Jul, 2017, 11:13:02 PM
    Author     : HARIOM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8" />
    <title>Dashboard</title>
    <link rel="stylesheet" href="/FastGrasp/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/FastGrasp/css/main.css" />
    <link rel="stylesheet" href="/FastGrasp/css/dashboard.css" />
    <script type="text/javascript" src="/FastGrasp/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/FastGrasp/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/FastGrasp/js/main.js"></script>
    <script type="text/javascript" src="/FastGrasp/js/dashboard.js"></script>
    
  </head>

  <body>
      <div id="dashboardSection1" class="dashboardWorkScreen">
          <div id="exploreLink" class="dashboardLink resourceLoc">
              <p><span class="glyphicon glyphicon-globe linkLogo"></span> EXPLORE</p>
          </div>
          <a href="<c:url value='explore' />">
              <div id="exploreDesc" class="dashboardDesc resourceLoc">
                  <p>
                      Explore the wide range of courses.
                  </p>
              </div>
          </a>
          <div id="profileLink" class="dashboardLink resourceLoc">
              <p><span class="glyphicon glyphicon-user linkLogo"></span> PROFILE</p>
          </div>
          <a href="<c:url value='profile' />">
              <div id="profileDesc" class="dashboardDesc resourceLoc">
                  <p>
                      View and edit your profile.
                  </p>
              </div>
          </a>


          <div id="myCoursesLink" class="dashboardLink resourceLoc">
              <p><span class="glyphicon glyphicon-book linkLogo"></span> MY COURSES</p>
          </div>
          <a href="<c:url value='myCourses' />">
              <div id="myCoursesDesc" class="dashboardDesc resourceLoc">
                  <p>
                      Go to your own list of courses.
                  </p>
              </div>
          </a>


          <div id="testsLink"class="dashboardLink resourceLoc">
              <p><span class="glyphicon glyphicon-file linkLogo"></span> TESTS</p>
          </div>
          <a href="<c:url value='myTests' />">
              <div id="testsDesc" class="dashboardDesc resourceLoc">
                  <p>
                      Explore the wide range of tests.
                  </p>
              </div>
          </a>


          <div id="issuesLink" class="dashboardLink resourceLoc">
              <p><span class="glyphicon glyphicon-exclamation-sign linkLogo"></span> ISSUES</p>
          </div>
          <a href="<c:url value='createIssue' />">
              <div id="issuesDesc" class="dashboardDesc resourceLoc">
                  <p>
                      See if the issues have been resolved.
                  </p>
              </div>
          </a>

          <div id="discussionForumLink" class="dashboardLink resourceLoc">
              <p><span class="glyphicon glyphicon-comment linkLogo"></span> DISCUSSION FORUM</p>
          </div>
          <a href="<c:url value='discussionForum' />">
              <div id="discussionForumDesc" class="dashboardDesc resourceLoc">
                  <p>
                      Discuss with the online community.
                  </p>
              </div>
          </a>
    </div>
    <div class="footer">
      <p>© 2017, FastGrasp.com, Inc. or its affiliates</p>
    </div>
  </body>
</html>
