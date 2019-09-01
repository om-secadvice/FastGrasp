x = 0;

function goByScroll(dataId){
    $('html,body').animate({scrollTop: $("#"+dataId).offset().top},'slow');
}

$(document).ready(function() {
  $("#loginSection").css('height', (window.innerHeight) + 'px');
  $("#courseDetailsScreen").css('height', (window.innerHeight) + 'px');
  $("#section3").css('padding-left', (window.innerWidth * 0.2) + 'px');
  $("#page2").click(function () {
      $("#section3").toggle(1000);
  });
  $(window).resize(function(){
      var windowWidth = $(window).width();
      $("#courseDetailsScreen").css('height', (window.innerHeight) + 'px');
      var fgfg = document.getElementById('courseDetailsSection1').clientHeight;
      console.log("fgfg" + fgfg );
      if(windowWidth < 730) {
          $(".fullScreen").css('height', (window.innerHeight) + 'px');
          console.log(window.innerHeight * 0.7);
          $("#logo").css('width', (window.innerWidth * 0.9) + 'px');
          $("#welcomeMsg").css('width', (window.innerWidth * 0.9) + 'px');
          $("#section3").css('padding-left', (window.innerWidth * 0.2) + 'px');
      }
      if(windowWidth > 730) {
          $(".pageLink").css('width', (window.innerWidth * 0.3) + 'px');
          $("#section3").css('padding-left', (window.innerWidth * 0.2) + 'px');
          $("#logo").css('width', (612) + 'px');
      }

    });
  $("#pageUp").click(function() {
    goByScroll('section2');
  });

});
