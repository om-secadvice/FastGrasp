<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Upload Content|FastGrasp</title>
    <!-- Bootstrap -->
	<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">


  </head>
  <body>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="/FastGrasp/js/uploadContent.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/FastGrasp/js/bootstrap.js"></script>

    <div class="container">
  <h2>Upload Course Content</h2>
  <p><strong>Note: </strong>Only Youtube video links are allowed for video content.<br/>
  </p>
  <hr/>
  <h4 class="breadcrumb"><a href="#">Course</a> &gt; <a href="#"><span>Introduction to modern application development</span></a></h4>
  
  <form id="uploadContentForm" action="../upload/file" enctype="multipart/form-data" method="POST">
    <legend>New Resource</legend>
    <div class="form-group">


    <label for="title">Title:</label>
    <input type="text" class="form-control" accept="" name="title" required/><br/>

      <label for="content">Content:</label><hr/>
      <label class="radio-inline"><input type="radio" class="radio" name="fileType" value="video" uploadType="video"/> Youtube Video</label><br/>
      <input type="text" class="form-control" placeholder="Enter Youtube Video url/FastGrasp." name="video" special/><br/><hr/><center style="text-align:center;font-size:24px;">OR</center><br/><hr/>
<label class="radio-inline"><input type="radio" name="fileType" value="PDF" uploadType="file"/> PDF</label>
    <label class="radio-inline"><input type="radio" name="fileType" value="msword" uploadType="file"/> MS-WORD</label>
    <label class="radio-inline"><input type="radio"  name="fileType" value="text" uploadType="file"/> Text</label>
    <br/>
      <input type="file"  class="form-control" rows="5" name="file" onchange="TestFileTypeAndSize(this.form.profilepic.value, ['.pdf', '.docx', '.doc', '.txt']);">
      <br/>
      <input type="submit" class="btn btn-success" value="Upload Content"/>
    </div>
  </form>
</div>


  </body>
</html>
