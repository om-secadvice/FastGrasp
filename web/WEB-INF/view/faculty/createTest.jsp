<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>New Test</title>
        <link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    </head>
    <body>

        <script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/FastGrasp/js/bootstrap.js"></script>

        <div class="container">
            <h2>Course Test</h2>

            <hr/>
            <h4 class="breadcrumb"><a href="<c:url value='explore'/>">Course</a> &gt; <a href="<c:url value='explore/courseDetails'/>"><span>${selectedCourse.title}</span></a></h4>

            <div class="alert alert-info alert-dismissable"><a class="panel-close close" data-dismiss="alert">×</a> <em class="fa fa-coffee"></em> This is an <strong>.alert</strong>. Use this to show important messages to the user. </div>



            <div class="alert alert-danger alert-dismissable fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
                </center>
            </div>
            <c:choose>
                <c:when test="${empty newTest}">  
                    <form  method="POST" action="createTest">
                        <legend>New Test</legend>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group ">


                                    <label for="title">Test Title: </label> &nbsp;
                                    <input type="text" class="form-control" name="title" required placeholder="Title for the test..."/>
                                </div></div><div class="col-sm-6">
                                <div class="form-group">
                                    <label for="fullMarks">Full Marks: </label>
                                    &nbsp;<input type="number" class="form-control" name="fullMarks" required placeholder="Full Marks..." min="5"/>
                                </div></div>
                            </div><div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="dueDate">Last Date: </label>

                                    <input type="text" class="form-control" name="dueDate" placeholder="Last Date For Attempting...(MM/DD/YYYY)" required>
                                </div>
                            </div>
                            <div class=" col-sm-6">
                                <div class="form-group">
                                    <label for="noOfQuestions">No. Of Questions: </label>

                                    <input type="number" class="form-control" name="noOfQuestions" placeholder="Number Of Questions" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-offset-5" style="text-align: right">
                                <input type="submit" class="btn btn-success" value="Create Test">
                                <br/>
                            </div>




                    </form>
                    <hr/>
                </c:when>
                <c:otherwise>
                    <form action="<c:url value='createTest'/>">
                        <legend>Current Test</legend>
                        <div class="row">
                            <div class="col-sm-5">
                                <div class="form-group ">


                                    <label for="title">Test Title: </label> &nbsp;${newTest.title}
                                    </div></div><div class="col-sm-5">
                                <div class="form-group">
                                    <label for="fullMarks">Full Marks: </label>
                                    &nbsp;${newTest.fullMarks}
                                </div></div>
                            <div class="col-sm-2" style="text-align: right">
                                <br/>
                                <input type="hidden" name="doneAddingData" value="true">
                                <input type="submit" class="btn btn-info" value="Done Adding Questions?">
                            </div></div><div class="row">
                            <div class="col-sm-5">
                                <div class="form-group">
                                    <label for="dueDate">Last Date: </label>&nbsp;${newTest.dueDate}

                                   
                                </div>
                            </div>
                            <div class=" col-sm-5">
                                <div class="form-group">
                                    <label for="noOfQuestions">No. Of Questions: </label>&nbsp;${newTest.noOfQuestions}

                                    
                                </div>
                            </div>
                        </div>




                    </form>
                    <hr/>
                    <h4><b>Enter Questions For Test:</b></h4>
                    
                </c:otherwise>
            </c:choose>
            <c:if test="${!empty newTest}">  

                <form class="form-horizontal" method="POST" action="addQuestion">
                    <label for="question">Question</label>
                    <input type="text" class="form-control" name="question" required placeholder="Enter Question Here..."/><br/>
                    <div class="form-group">
                        <label class="control-label col-sm-1" for="choiceOne" >Option 1: </label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" name="choiceOne" placeholder="First Choice..." required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1" for="choiceTwo">Option 2:</label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" name="choiceTwo" placeholder="Second Choice..." required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1" for="choiceThree">Option 3:</label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" name="choiceThree" placeholder="Third Choice..." required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-1" for="choiceFour">Option 4:</label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" name="choiceFour" placeholder="Fourth Choice..." required>
                        </div>
                    </div>


                    <div class="form-group"> 
                        <label class="control-label col-sm-1" for="correctChoice">Answer:</label>
                        <div class=" col-sm-5">
                            <input type="number" class="form-control" name="correctChoice" placeholder="Correct Option" required/>
                        </div>
                        <label class="control-label col-sm-1" for="marks">Marks:</label>
                        <div class=" col-sm-5">
                            <input type="number" class="form-control" name="marks" placeholder="Mark Alloted For This Question..." required/>
                        </div>
                    </div>
                    <div class="form-group"> 
                        <div class="col-sm-12">

                            <input type="submit" class="btn btn-success" value="Add Next Question">
                        </div>
                    </div>
                </form>

            </c:if>
        </div>



    </body>
</html>
