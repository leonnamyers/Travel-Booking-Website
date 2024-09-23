<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random"%>
<%@ page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>
<%@page import="java.sql.Timestamp" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>Home Page</title>
    </head>
    <%  
    if(session.getAttribute("flightList") == null){
        ArrayList<Flight> flightList = new ArrayList<Flight>();
            Flight f1 = new Flight(101,"JetEngine",250,50,"JetEngine.jpg",Timestamp.valueOf("2024-10-01 06:30:00"),Timestamp.valueOf("2024-10-01 06:30:00"),"Sydney","Melbourne", 2,"Non Stop","Economy");
            Flight f2 = new Flight(102,"Harmony",630,50,"Harmony.jpg",Timestamp.valueOf("2024-10-01 06:30:00"),Timestamp.valueOf("2024-10-01 06:30:00"),"Melbourne","Sydney",2,"1 Stop","Business");
            Flight f3 = new Flight(103,"Traveling",500,50,"Travel.jpg",Timestamp.valueOf("2024-10-01 06:30:00"),Timestamp.valueOf("2024-10-01 06:30:00"),"Queensland","Melbourne",2,"2 Stops","Premium Economy");    
            flightList.add(f1);
            flightList.add(f2);
            flightList.add(f3);
            session.setAttribute("flightList", flightList);
        }
    %>
    <body>
        <nav>
            <h1>Dream Escape</h1>
            <!--If User is logged in-->
            <%
            if (session != null && session.getAttribute("user") != null) { 
            %>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="account_details.jsp">Account</a></li>
                <li><a href="logout.jsp">Logout</a></li>
                <a href="Cart.jsp">
                    <button class ="shopping-cart-button" >
                        <i class="fas fa-shopping-cart"></i>
                        <% Cart cart = (Cart) request.getSession().getAttribute("cart");%>
                        <% if (cart == null) { %>
                        $0.00
                        <% } else { %>
                        $<%=cart.getTotalPrice()%>
                        <% } %>
                    </button>
                </a>
            </ul>
            <!--If User is NOT logged in-->
            <%
            } else {
            %>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="register.jsp">Register</a></li>
                <a href="cart.jsp">
                    <button class ="shopping-cart-button" >
                        <i class="fas fa-shopping-cart"></i>
                        <% Cart cart = (Cart) request.getSession().getAttribute("cart");%>
                        <% if (cart == null) { %>
                        $0.00
                        <% } else { %>
                        $<%=cart.getTotalPrice()%>
                        <% } %>
                    </button>
                </a>
            </ul>
            <% 
            }
            %>
        </nav>

        <div class="outer-container">
            <div class="flex-container" style="flex-direction: column;">
                <%
                if (request.getParameter("unregister") != null && request.getParameter("unregister").equals("true")) {
                %>
                <p>You have successfully unregistered your account</p>
                <br>
                <%
                }
                %>

                <!-- Including the navbar -->
                <jsp:include page="navbar.jsp" flush="true" />

                <!-- Content will be loaded based on navigation -->
                <div class="content">
                    <!-- No default content, only show based on navigation -->
                </div>
            </div>           
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
