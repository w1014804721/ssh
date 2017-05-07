<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传图片事例</title>
    <script src="js/jquery-3.1.1.min.js"></script>
</head>
<body>
<progress id="progress" value="0" max="100"></progress>
<form>
    <img src="" id="pic" />
    <input type="file" name="file"/>
    <input type="button" id="submit" value="上传" />
</form>
</body>
<script>

    function progressHandlingFunction(e){
        if(e.lengthComputable){
            $('#progress').attr({value:e.loaded/e.total*100});
        }
    }

    function completeHandler(data) {
        alert("上传成功")
        $("#pic").attr({src:data});
    }

    function errorHandler() {
        alert("上传失败")
    }
    $("#submit").click(function(){
        console.log("go")
        var formData = new FormData($('form')[0]);
        $.ajax({
            url: 'Upload/uploadPic',  //server script to process data
            type: 'POST',
            xhr: function() {  // custom xhr
                var myXhr = $.ajaxSettings.xhr();
                if(myXhr.upload){ // check if upload property exists
                    myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // for handling the progress of the upload
                }
                return myXhr;
            },
            //Ajax事件
            success: completeHandler,
            error: errorHandler,
            // Form数据
            data: formData,
            //Options to tell JQuery not to process data or worry about content-type
            cache: false,
            contentType: false,
            processData: false
        });

    })

</script>
</html>