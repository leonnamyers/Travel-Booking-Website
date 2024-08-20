<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"> 
    <script type="text/javascript" src="js/index.js"></script>
    <title>Name of your page</title>
    <nav>
        <h1>Name of your page</h1>

        <!--
            Menu Items => 

                            If your page is accessible to logged in users and unlogged users
                            add them to both

                            If not:
                            I recommend adding them to both of these columns while you are
                            so it's easier to debug // create // look at pages

                            Once you are finished debugging, delete it from the one it doesn't need to be in.

                            If you need to make options for 'only staff' or 'only customeruser' pages
                            and you need help, you can refer to the logic that I provided in discord.

                            You will also have to go through and add that to other people's pages, so
                            it might also be a good idea to ask/tell people to try avoid merge conflicts
        -->

        <!--Menu Items => If User is logged in-->

        <%
        if (session != null && session.getAttribute("user") != null) { 
        %>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>

        <!--Menu Items => If User is NOT logged in-->

        <%
        } else {
        %>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="login.jsp">Login</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
        <% 
        }
        %>
    </nav>
</head>
<body>
    <div class="outer-container">
        <div class="flex-container">
            <div>
                <%
                if (session != null && session.getAttribute("user") != null) { 

                /*
                    Enter logic for when an account is logged in
                    I've given you the code to help you on Discord already.
                */

                %>
                
                <%
                } else {

                /*
                    Enter logic for when an account is not logged in
                            
                    Although, you may redirect the page so that an unregistered/registered user never gets here,
                    in the offchance that they do (human error), you should handle it to avoid an error
        
                    I've added a default. Change it to suit your page.
                */
                
                }
                %>
                <p>User is not logged in.</p>
            </div>
        </div>            
    </div>        
</body>
</html>
