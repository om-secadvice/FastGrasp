<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>New Test</title>
<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>

<script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/FastGrasp/js/bootstrap.js"></script>

<div class="container">
  <h2>Course Test</h2>

  <hr/>
  <h4 class="breadcrumb"><a href="#">Course</a> &gt; <a href="#"><span>Introduction to modern application development</span></a></h4>

  <div class="alert alert-info alert-dismissable"><a class="panel-close close" data-dismiss="alert">×</a> <em class="fa fa-coffee"></em> This is an <strong>.alert</strong>. Use this to show important messages to the user. </div>



  <div class="alert alert-danger alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  	<center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
    </center>
  </div>

  <form>
  <legend>New Test</legend>
    <div class="form-group">

    <!--span >Introduction to modern application development</span--><br/>
    <label for="title">Question</label>
    <input type="text" class="form-control" name="title" required/><br/>
      <label for="content">Options:</label>
      <div>
      	<input type="text" class="form-control" name="title" required/><br/>
        <input type="text" class="form-control" name="title" required/><br/>
        <input type="text" class="form-control" name="title" required/><br/>
        <input type="text" class="form-control" name="title" required/><br/>
      </div>
      <br/>
      <input type="submit" class="btn btn-success" value="Create Announcement"/>
    </div>
  </form>
</div>



</body>
</html>
