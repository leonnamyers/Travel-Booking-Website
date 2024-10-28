<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.*" %>
<%@ page import="com.iotbay.Dao.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Review Payments</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" flush="true" />
        <div class="outer-container">
            <div class="flex-container">
                <div>
                    <%
                    Payment payment = (Payment) request.getAttribute("payment");
                    
                    if (payment != null) {
                    %>
                    <h2>Payment Details</h2>
                    
                    <table>
                        <tr>
                            <th>Payment ID:</th>
                            <td><%= payment.getPaymentID() %></td>
                        </tr>
                        <tr>
                            <th>Cardholder Name:</th>
                            <td><%= payment.getCardHolderName() %></td>
                        </tr>
                        <tr>
                            <th>Card Number:</th>
                            <td>**** **** **** <%= payment.getCardNumber().substring(12) %></td> 
                        </tr>
                        <tr>
                            <th>Expiry Date:</th>
                            <td><%= payment.getExpirationDate() %></td>
                        </tr>
                        <tr>
                            <th>CVV:</th>
                            <td>***</td>
                        </tr>
                    </table>
                    <br>

                    <div class="orderbutton-container">
                        <div>
                            <form action="RemovePaymentDetailsController" method="POST">
                                <button type="submit">Remove Details</button>
                            </form>
                        </div>
                        <div>
                            <form action="PostOrderController" method="POST">
                                <button type="submit">Confirm Order</button>
                            </form>
                        </div>
                        <div>
                            <form action="UpdatePaymentDetailsController" method="POST">
                                <button type="submit">Update Details</button>
                            </form>
                        </div>
                    </div>

                    <%
                    } else {
                    %>
                    <p>No payment details found.</p>
                    <% 
                    } 
                    %>
                </div>
            </div>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
