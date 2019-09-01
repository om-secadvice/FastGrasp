/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function disableUpload(uploadType) {
   
     if(uploadType === "file") {
        $("#uploadContentForm input[type='file']").prop('disabled', true);
        $("#uploadContentForm input[type='file']").removeAttr('required');
    }else {
        $("#uploadContentForm input[name='" + uploadType + "']").prop('disabled', true);
        $("#uploadContentForm input[name='" + uploadType + "']").removeAttr('required');
    }
} 

function enableUpload(uploadType) {
    if(uploadType === "file") {
        $("#uploadContentForm input[type='file']").prop('disabled', false);
        $("#uploadContentForm input[type='file']").attr('required', 'required');
        disableUpload("video");
    } else {
        $("#uploadContentForm input[name='" + uploadType + "']").prop('disabled', false);
        $("#uploadContentForm input[name='" + uploadType + "']").attr('required', 'required');
        disableUpload("file");
    }
}

$(document).ready(function () {
    var uploadType = "file";
    disableUpload(uploadType);
    var uploadType = "video";
    disableUpload(uploadType);
    $("#uploadContentForm input[name='fileType']").on('change', function () {
        uploadType = $("#uploadContentForm input[name='fileType']:checked").attr("uploadType");
        enableUpload(uploadType);
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



