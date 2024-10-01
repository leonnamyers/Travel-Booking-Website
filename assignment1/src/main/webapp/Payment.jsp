<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>
<%@ page import="com.iotbay.Model.*" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script type="text/javascript" src="js/index.js"></script>
    <title>Dream Escape - Book Your Trip</title>
    <nav>
        <h1>Payment Details</h1>
    
    <div class="content">
        <%
        if (session != null && session.getAttribute("user") != null) { 
        %>

        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="account_details.jsp">Account</a></li>
            <li><a href="logout.jsp">Logout</a></li>
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
</head>
    <body>
        <main class="form-container">
            <h2>Enter Payment Details</h2>

            <% 
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            double totalPrice = cart.getTotalPrice();
            %>

            <p> Total Amount: $<%=totalPrice%></p>
            <!-- Display Total amount-->
        
            <form action="PaymentController" method="post">
                <div class="form-element">
                    <label for="cardHolderName">Cardholder's Name:</label>
                    <input type="text" name="cardHolderName" id="cardHolderName" required />
                </div>
                <div class="form-element">
                    <label for="cardNumber">Card Number:</label>
                    <input type="text" name="cardNumber" id="cardNumber" required />
                </div>
                <div class="form-element">
                    <label for="expiryDate">Expiry Date (MM/YY):</label>
                    <input type="text" name="expiryDate" id="expiryDate" required />
                </div>
                <div class="form-element">
                    <label for="cvv">CVV:</label>
                    <input type="text" name="cvv" id="cvv" required />
                </div>
                <button class="general-buttons">Submit</button>
            </form>
        </main>
    </body>
</html>