<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Untitled Document</title>
    <!-- Bootstrap -->
	<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">


  </head>
  <body>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/FastGrasp/js/bootstrap.js"></script>

  <div class="container">
  <h2>Student Feedbacks</h2>

  <hr/>
  <h4 class="breadcrumb"><a href="#">Course</a> &gt; <a href="#"><span>Introduction to modern application development</span></a></h4>
  <div class="alert alert-danger alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  	<center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
    </center>
  </div>

  <center>
  <label class="radio-inline"><input type="radio" name="filter" value="5" checked/> Excellent</label>
<label class="radio-inline"><input type="radio" name="filter" value="4"/> Very Good</label>
<label class="radio-inline"><input type="radio" name="filter" value="3"/> Good</label>
<label class="radio-inline"><input type="radio" name="filter" value="2"/> Average</label>
<label class="radio-inline"><input type="radio" name="filter" value="1"/> Not Satisfactory</label>

</center>
<hr/>
    <div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
      <div class="panel panel-default">
        <div class="panel-heading" role="tab">
          <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">Feedback Title 1</a></h4>
        </div>
        <div id="collapseOne1" class="panel-collapse collapse in">
          <div class="panel-body">Feedback Content</div>
        </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1">Feedback Title 2</a></h4>
        </div>
        <div id="collapseTwo1" class="panel-collapse collapse">
          <div class="panel-body">Feedback Content</div>
        </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseThree1">Feedback Title 3</a></h4>
        </div>
        <div id="collapseThree1" class="panel-collapse collapse">
          <div class="panel-body">Feedback Content</div>
        </div>
      </div>
    </div>
  </div>
  </body>
</html>
