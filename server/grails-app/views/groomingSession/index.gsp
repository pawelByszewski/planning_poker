<!doctype html>
<html>
<head>
    <r:require module="spring-websocket" />
    <title>Grails Twitter Bootstrap Scaffolding</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <asset:javascript src="jquery" />
    <asset:javascript src="spring-websocket" />
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(function() {
            var socket = new SockJS("${createLink(uri: '/stomp')}");
            var client = Stomp.over(socket);

            client.connect({}, function() {
                client.subscribe("/add/user", function(message) {
                    $('#users').append('<p>' + message.body + '</p>')
                });
            });

//            $("#helloButton").click(function() {
//                client.send("/app/hello", {}, "");
//            });
        });
    </script>
</head>

<body>
    <div class="row-fluid">
        <div class="span4">
            <h1>${planningSessionKey}</h1>
        </div>

        <div class="span4">
            <qrcode:image height="100" width="100" text="${planningSessionKey}"/>
        </div>
    </div>
    <div class="row-fluid" >
        <h1>Podłączeniu użytkownicy</h1>
        <div class="span4" id="users">

        </div>
    </div>
</body>
</html>
