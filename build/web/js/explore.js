function showCourse(tableName) {
  table = document.getElementById(tableName);
  tr = table.getElementsByTagName("tr");
  for (i = 1, j = 1; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function showAllCourse(tableName) {
  table = document.getElementById(tableName);
  tr = table.getElementsByTagName("tr");
  for (i = 1, j = 1; i < tr.length; i++) {
    tr[i].style.display = "";
  }
}

$(document).ready(function () {
  $("input[name='course']").change(function(){
    // Do something interesting here
    filter = this.value.toUpperCase();
    if((" all").toUpperCase().indexOf(filter) > -1) {
        showAllCourse("runningCourses");
        showAllCourse("upcomingCourses");
    }
    else {
      showCourse("runningCourses");
      showCourse("upcomingCourses");
    }
  });
});
