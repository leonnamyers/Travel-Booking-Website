<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.*" %>
<%@ page import="com.iotbay.Dao.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="navbar.jsp" flush="true" />
        <div class="outer-container">
            <div class="flex-container">
                    <p>Payment details successfully deleted.</p>
                    <br>
                </div>
            </div>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
