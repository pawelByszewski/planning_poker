<!doctype html>
<html>
<head>
    <title>Grails Twitter Bootstrap Scaffolding</title>
    <!-- Latest compiled and minified CSS -->
    <asset:javascript src="jquery" />
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
<div class="row-fluid">
    <a href="GroomingSession">
        <g:uploadForm controller="GroomingSession" action="index">
            <input type="file" name="taskFile" />
            <input type="submit" value="Rozpocznij sesję" />
        </g:uploadForm>
    </a>
</div>
</body>
</html>