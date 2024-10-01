<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="com.iotbay.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <script type="text/javascript" src="js/index.js"></script>
    <title>Error</title>
</head>
<body>
    <jsp:include page="navbar.jsp" flush="true" />
    <div class="outer-container">
        <div class="flex-container">
            <div>
                
                <%
                String errorMessage = request.getParameter("errorMessage");

                if (errorMessage != null && !errorMessage.isEmpty()) { %>
                    <div class="outer-container">
                        <div class="flex-container" style="flex-direction: column;">
                            <br>
                            <p> <%= errorMessage %></p>
                            <br>
                        </div>
                    </div>
                <% } %>  

            </div>
        </div>            
    </div>        
</body>
</html>
