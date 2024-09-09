<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"> 
        <script type="text/javascript" src="js/index.js"></script>
        <title>Dream Escape- Order Placed!</title>
        <nav>
    
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
    <main class="text-display">
        <div class="form-container">
            <h2 class="form-element">Travel Details:</h2>

            <div class="form-container">
                <form method="post" action="PlaceOrderController">
                    <div class="form-element">
                        <label for="name">Name:</label>
                        <input type="text" name="name" id="name" value="<%= user != null ? user.getUsername() : "" %>" required />
                    </div>
                    <div class="form-element">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" value="<%= user != null ? user.getEmail() : "" %>" required />
                    </div>
                    <div class="form-element">
                        <label for="destination">Destination:</label>
                        <input type="text" name="destination" id="destination" required />
                    </div>
                    <div class="form-element">
                        <label for="departureDate">Departure Date:</label>
                        <input type="date" name="departureDate" id="departureDate" required />
                    </div>
                    <div class="form-element">
                        <label for="returnDate">Return Date:</label>
                        <input type="date" name="returnDate" id="returnDate" required />
                    </div>
                    <div class="form-element">
                        <label for="passengers">Number of Passengers:</label>
                        <input type="number" name="passengers" id="passengers" min="1" required />
                    </div>
                    <button class="general-buttons form-element">Book Now ></button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
