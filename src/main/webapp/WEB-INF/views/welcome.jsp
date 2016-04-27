<%--
  Created by IntelliJ IDEA.
  User: fsoewito
  Date: 2/25/2016
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Welcome page</title>
    </head>

    <body>
        <p style="font-size:18pt; color: black">Probe ID : ${probeId}</p>
        <p style="font-size:18pt; color: red">Current Temperature : ${temperature}°C</p>
        <script>
            window.setTimeout(function(){
                window.location = "http://172.30.44.94:8080/raspi/";
            }, 5000);
        </script>
    </body>

</html>
