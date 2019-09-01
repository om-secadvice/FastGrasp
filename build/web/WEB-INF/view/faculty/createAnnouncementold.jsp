<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Do Announcement|FastGrasp</title>
    <!-- Bootstrap -->
	<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">


  </head>
  <body>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/FastGrasp/js/bootstrap.js"></script>

    <div class="container">
  <h2>Course Announcement</h2>

  <hr/>
  <h4 class="breadcrumb"><a href="#">Course</a> &gt; <a href="#"><span>Introduction to modern application development</span></a></h4>

  <div class="alert alert-danger alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  	<center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
    </center>
  </div>

  <form>
  <legend>New Announcement</legend>
    <div class="form-group">

    <!--span >Introduction to modern application development</span--><br/>
    <label for="title">Title:</label>
    <input type="text" class="form-control" name="title" required/><br/>
      <label for="content">Content:</label>
      <textarea class="form-control" rows="5" name="content" required ></textarea>
      <br/>
      <input type="submit" class="btn btn-success" value="Create Announcement"/>
    </div>
  </form>
</div>
<br/>

<div class="container">
  <h4>Previous announcements</h4>
  <div class="list-group"><a href="#" class="list-group-item active">
      <h4 class="list-group-item-heading">Title 1</h4>
      <p class="list-group-item-text">Content 1</p>
      </a><a href="#" class="list-group-item">
        <h4 class="list-group-item-heading">Title 2</h4>
        <p class="list-group-item-text">Content 2</p>
        </a><a class="list-group-item disabled">
          <h4 class="list-group-item-heading">Title 3</h4>
          <p class="list-group-item-text">Content 3</p>
        </a></div>
</div>


  </body>
</html>
