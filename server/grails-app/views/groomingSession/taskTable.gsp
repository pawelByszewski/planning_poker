<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<g:each in="${tasks}">
    <p>Id: ${it.id}</p>
    <p>Title: ${it.title}</p>
</g:each>
</body>
</html>