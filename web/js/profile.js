/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function disableProfileEditing() {
    $("#editProfile input, #editProfile textarea").attr('readonly', 'readonly');
    $("#editProfile input[type='submit']").prop('disabled', true);
    $('#confirmPasswordProfile').hide();
    $("#editProfile input[name='revertBackButton']").hide();
}

function enableProfileEditing() {
    $("#editProfile input[editable], #editProfile textarea").removeAttr('readonly');
    $("#editProfile input[type='submit']").prop('disabled', false);
    $('#confirmPasswordProfile').show();
    $("#editProfile input[name='revertBackButton']").show();
}

function defaultValue() {
    
    var fields = $("#editProfile input[editable], #editProfile textarea").serializeArray();
    return fields;
}

function restoreValue(fields) {
    jQuery.each( fields, function( i, field ) {
        $("#editProfile input[name='"+ field.name + "']").val(field.value);
        if(field.name === "aboutMe")
            $("#editProfile textarea").val(field.value);
        disableProfileEditing();
    });
}

$(document).ready(function() {
  var fields = defaultValue();
  console.log(fields);
  disableProfileEditing();
  $("#editProfile input[name='editProfileButton']").click(function () {
      enableProfileEditing();
  });
  
  $("#editProfile input[name='revertBackButton']").click(function () {
      restoreValue(fields);
  });
});

function TestFileTypeAndSize(fileName, fileTypes) {
                if (!fileName)
                    return;
                
                console.log(fileName);
                dots = fileName.split(".");
                //get the part AFTER the LAST period.
                fileType = "." + dots[dots.length - 1];
                var inputtag=document.getElementById('filedesc');
                inputtag.value=fileName;
                var alerttag=document.querySelector('div.alert.alert-info');
                if (fileTypes.join(".").indexOf(fileType) != -1){
                        alert('Accepted! Now Upload');
                      
                } else {
                    alert('Only:' + (fileTypes.join(" .")));
                    inputtag.value="Choose an image...";

                }
 }