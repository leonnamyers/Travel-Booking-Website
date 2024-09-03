<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Name? - Thank you!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/buttons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>
    <jsp:include page="header.jsp"/>

    <main class="text-display">
        <h1>Your order has been placed!</h1>
        <h2>Thank you for using IoTBay</h2>
    </main>
</body>
</html>
