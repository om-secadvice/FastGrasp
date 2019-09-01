<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html5>
<html>
  <head>
    <title>FastGrasp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8" />
    <link rel="stylesheet" href="/FastGrasp/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/FastGrasp/css/main.css" />
    <script type="text/javascript" src="/FastGrasp/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/FastGrasp/js/main.js"></script>
    </script>
  </head>

  <body>
    <div id="welcome" class="fullScreen">
      <img src="images/fastgrasp.png" alt="FastGrasp logo" id="logo"/>
      <p id="welcomeMsg">
        e-Portal for online courses. Find the best, free courses in FastGrasp.
      </p>
      <span class="glyphicon glyphicon-chevron-down" id="pageUp"></span>
    </div>
    <div id="section2">
        <a href="student/explore">
      <div id="page1" class="pageLink resourceLoc">
          <p><span class="glyphicon glyphicon-globe linkLogo"></span></p>
          <p class="linkLogo">EXPLORE</p>
          <hr />
          <p class="linkDesc">Explore the wide range of courses.</p>
      </div></a>
      <div id="page2" class="pageLink resourceLoc">
          <p><span class="glyphicon glyphicon-user linkLogo"></span></p>
          <p class="linkLogo">JOIN US</p>
          <hr />
          <p class="linkDesc">Join and become a part of the community and teachers.</p>
      </div>
      <div id="page3" class="pageLink resourceLoc">
        <p><span class="glyphicon glyphicon-info-sign linkLogo"></span></p>
        <p class="linkLogo">ABOUT US</p>
        <hr />
        <p class="linkDesc">Know the people behind Team FastGrasp.</p>
      </div>

    </div>
      
      <div id="section3">
      <div id="page4" class="pageLink resourceLoc">
          <p><span class="glyphicon glyphicon-briefcase linkLogo"></span></p>
          <p class="linkLogo">JOIN AS STUDENT</p>
          <hr />
          <a href="<c:url value='student/login' />"><span id="studentLogin" class="imp">Login</span></a>
          &nbsp;
          &nbsp;
          &nbsp;
          <a href="student/register"><span id="studentRegister" class="imp">Register</span></a>
     </div>
      <div id="page5" class="pageLink resourceLoc">
          <span class="glyphicon glyphicon-briefcase linkLogo"></span></p>
          <p class="linkLogo">JOIN AS FACULTY</p>
          <hr />
          <a href="faculty/login"><span id="facultyLogin" class="imp">Login</span></a>
          &nbsp;
          &nbsp;
          &nbsp;
          <a href="faculty/register"><span id="facultyRegisterr" class="imp">Register</span></a>
      </div>

    </div>
      
    <div class="footer">
      <p>© 2017, FastGrasp.com, Inc. or its affiliates</p>
    </div>


  </body>
</html>
