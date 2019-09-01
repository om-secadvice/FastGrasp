
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FastGrasp | Profile</title>
<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/FastGrasp/css/main.css" />
<script src="/FastGrasp/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/FastGrasp/js/bootstrap.js" type="text/javascript"></script>
<!--<script src="/FastGrasp/js/main.js" type="text/javascript"></script>-->
<script src="/FastGrasp/js/profile.js" type="text/javascript"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <div class="container">
            <div class="container" style="padding-top: 60px;">
                <h1 class="page-header">Profile Details</h1>
                
                <div class="row">
                    <!-- left column -->
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <div class="text-center">
                            <div>
                                <img src="${currentUser.profilePic}" class="avatar img-circle img-responsive img-thumbnail" alt="avatar">
                            </div>
                            <br/><br/><b>Choose a picture</b>
                            <form action="../upload/profile" enctype="multipart/form-data" method="POST">

                                <div class="input-group">
                                    <label class="input-group-btn">
                                        <span class="btn btn-primary">
                                            Browse… <input style="display:none" id="profilepic" type="file" name="profilepic" class="text-center center-block well well-sm" accept="image/jpg,image/jpeg,image/gif,image/png" onchange="TestFileTypeAndSize(this.form.profilepic.value, ['.gif', 'jpg', 'png', 'jpeg']);"> &nbsp;
                                        </span><br/><br/>
                                    </label>
                                    <input id="filedesc" type="text" class="form-control" disabled any value="Choose an image..."> 
                                </div>
                                
                                <br/>

                                <input type="submit" value="Upload" class="btn btn-default btn-block"/>
                            </form>
                        </div>
                    </div>
            <!-- edit form column -->
            <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
              <div class="alert alert-info alert-dismissable">
                  <a class="panel-close close" data-dismiss="alert">Ã</a> 
                  <em class="fa fa-coffee"></em> This is an <strong>.alert</strong>. 
                  Use this to show important messages to the user. 
              </div>
              <h3>Personal info</h3>
              <form class="form-horizontal" role="form" id="editProfile" method="post" action="profile">
          
                <div class="form-group">
                  <label class="col-lg-3 control-label">Name:</label>
                  <div class="col-lg-8">
                      <input class="form-control" type="text" value="${currentUser.name}" name="name" required="required">
                  </div>
                </div>
                  
                <c:if test="${userType == 'student'}">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">College Roll No.:</label>
                        <div class="col-lg-8">
                          <input class="form-control" type="text" value="${currentUser.collegeRoll}" name="collegeRoll" required="required" editable>
                        </div>
                    </div>
                </c:if>

                <c:choose>
                    <c:when test="${userType == 'faculty'}">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Institute Name:</label>
                            <div class="col-lg-8">
                              <input class="form-control" type="text" name="instituteName" required="required">
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${userType == 'student'}">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">College Name:</label>
                            <div class="col-lg-8">
                              <input class="form-control" type="text" value="${college.collegeName}" name="collegeName" required="required">
                            </div>
                        </div>
                    </c:when>
                </c:choose>  
                        
                <c:choose>
                    <c:when test="${userType == 'student'}">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Semester:</label>
                            <div class="col-lg-8">
                              <input class="form-control" type="number" value="${currentUser.semester}" name="semester" min="1" required="required" editable>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${userType == 'faculty'}">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Designation:</label>
                            <div class="col-lg-8">
                              <input class="form-control" type="text" name="designation" required="required" editable>
                            </div>
                        </div>
                    </c:when>
                </c:choose> 

                <div class="form-group">
                  <label class="col-lg-3 control-label">Contact:</label>
                  <div class="col-lg-8">
                    <input class="form-control" type="tel" value="${currentUser.contact}" name="contact" required="required" editable>
                  </div>
                </div>
                        
                <div class="form-group">
                  <label class="col-lg-3 control-label">Email:</label>
                  <div class="col-lg-8">
                    <input class="form-control" type="text" value="${currentUser.email}" name="email" required="required">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label">About Me:</label>
                  <div class="col-md-8">
                      <textarea style="width: 100%; padding: 12px;" name="aboutMe" placeholder="Write something here about yourself." id="editProfileTextArea">${currentUser.aboutMe}</textarea>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label">Password:</label>
                  <div class="col-md-8">
                    <input class="form-control" type="password" value="${currentUser.password}" name="password" required="required" editable>
                  </div>
                </div>
                <div class="form-group" id="confirmPasswordProfile">
                  <label class="col-md-3 control-label">Confirm password:</label>
                  <div class="col-md-8">
                    <input class="form-control" type="password" name="confirmPassword" required="required" editable>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"></label>
                  <div class="col-md-8">
                    <input class="btn btn-primary" value="Edit Profile" type="button" name="editProfileButton">
                    <input class="btn btn-primary" value="Save Changes" type="submit">
                    <input class="btn btn-primary" value="Cancel" type="button" name="revertBackButton">
                    <span></span>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <h1 class="page-header">Performance</h1>
          <div class="row">
          	<div class="col-md-8">
            	<div class="panel-body">
                	Course No.1
                </div>
            </div>
            <div class="col-md-4">
            	<div class="panel-body">
                	85%
                </div>
            </div>
          </div>
          <div class="row">
          	<div class="col-md-8">
            	<div class="panel-body">
                	Course No.2
                </div>
            </div>
            <div class="col-md-4">
            	<div class="panel-body">
                	92%
                </div>
            </div>
          </div>
          <div class="row">
          	<div class="col-md-8">
            	<div class="panel-body">
                	Course No.3
                </div>
            </div>
            <div class="col-md-4">
            	<div class="panel-body">
                	33%
                </div>
            </div>
          </div>
        </div>
    </div>
    <div class="footer">
        <p>© 2017, FastGrasp.com, Inc. or its affiliates</p>
    </div>                    
</body>
</html>
