<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>Please take a photo!</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- upload to server> <!-->
    <script>
        function btnUploadFile(e) {
            var imgFile = document.getElementById("fileInpBtn").files[0];
            if (imgFile == null) {
                alert("No file uploaded, please take a photo then upload");
                return;
            }
            var formData = new FormData();
            formData.append('file', imgFile);
            console.log(formData.file);
            formData.append('other', 'other');

            $.ajax({
                url: "uploadServlet",
                type: 'POST',
                cache: false,
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    alert("File successfully uploaded");
                },
                error: function (err) {
                    alert("File did not upload, please try again!");
                }
            })
        }
    </script>

    <script>
        function showPicture(imgFile) {
            document.getElementById("newImage").src = window.URL.createObjectURL(imgFile.files[0]);
        }
    </script>

    <style>
        .mui-card {
            position: relative;
            border: #c8c7cc 1px dotted;
            padding: 8px;
            -webkit-box-shadow: none;
            box-shadow: none;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            margin-bottom: 30px;
        }

        .fileInpBtn {
            position: absolute;
            left: 2px;
            top: 0;
            width: 100%;
            height: 100%;
            cursor: pointer;
            filter: alpha(opacity:0);
            opacity: 0;
            border-color: orange;
        }
    </style>
</head>

<body>
<div class="container">
    <h3 style="text-align:center;">Please take a photo!</h3>
    <form action="${pageContext.request.contextPath}/listPictureServlet" method="POST" name="">

        <!--Get phone's camera to take photo-->
        <div class="form-group mui-card">
            <div style="text-align: center">Click here to take a photo.</div>
            <input class="form-control fileInpBtn" id="fileInpBtn" type="file" onchange="showPicture(this)"
                   accept="image/*" capture="user">
        </div>

        <div class="form-group" style="margin: auto">
            <img class="img-responsive" id="newImage" src=""/>
        </div>

        <div class="form-group" style="text-align: center;margin-top: 50px">
            <!--Upload to Cloud button-->
            <input class="btn btn btn-primary" type="button" value="Upload to Cloud" onclick="btnUploadFile(event)">
        </div>

        <div class="form-group" style="text-align: center">
            <!--display all pictures button-->
            <input class="btn btn btn-primary" type="submit" value="Display all pictures uploaded in cloud">
        </div>

    </form>
</div>
</body>
</html>
