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
        </ul>
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
        <main class="form-container">
            <h2>Enter Payment Details</h2>

            <%
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            double totalPrice = cart.getTotalPrice();
            %>

            <p> Total Amount: $<%=totalPrice%></p>
            <!-- Display Total amount-->

            <script type="text/javascript">
                function validatePaymentForm() {
                    var cardNumber = document.getElementById("cardNumber").value;
                    var expiryDate = document.getElementById("expiryDate").value;
                    var cvv = document.getElementById("cvv").value;
                    
                    // Card number should be 16 digits
                    var cardNumberPattern = /^\d{16}$/;
                    if (!cardNumberPattern.test(cardNumber)) {
                        alert("Please enter a valid 16-digit card number.");
                        return false;
                    }
            
                    // Expiry date should be in MM/YY format
                    var expiryDatePattern = /^(0[1-9]|1[0-2])\/\d{2}$/;
                    if (!expiryDatePattern.test(expiryDate)) {
                        alert("Please enter a valid expiry date in MM/YY format.");
                        return false;
                    }
            
                    // CVV should be 3 or 4 digits
                    var cvvPattern = /^\d{3,4}$/;
                    if (!cvvPattern.test(cvv)) {
                        alert("Please enter a valid 3 or 4 digit CVV.");
                        return false;
                    }
            
                    return true;
                }
            </script>
        
            <form action="PaymentController" method="post" onsubmit="return validatePaymentForm()">
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
                    <input type="submit" name="action" value="Submit" class="general-buttons btn-outline-light"/>
            </form>
        </main>
    </body>
</html>