<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.Model.*" %>
<%@ page import="com.iotbay.Dao.*" %>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Flight Order Details</title>
    </head>
    <body>
        <div class="outer-container">
            <div class="flex-container">
                <div>
                    <%
                    User user = (User) session.getAttribute("user");
                    Cart cart = (Cart) session.getAttribute("cart");

                    if (user != null && cart != null && !cart.isEmpty()) {
                        Order order = (Order) request.getAttribute("order");
                        if (order != null) {
                            %>
                            <h2>Flight Order Summary</h2>
                            <table>
                                <tr>
                                    <th>Customer Name:</th>
                                    <td><%= user.getFirstName() + " " + user.getLastName() %></td>
                                </tr>
                                <tr>
                                    <th>Email:</th>
                                    <td><%= user.getEmail() %></td>
                                </tr>
                                <tr>
                                    <th>Departure Date:</th>
                                    <td><%= order.getDepartureDate() %></td>
                                </tr>
                                <tr>
                                    <th>Return Date:</th>
                                    <td><%= order.getReturnDate() %></td>
                                </tr>
                                <tr>
                                    <th>Seat Type:</th>
                                    <td>$<%= order.getSeatType() %></td>
                                </tr>
                            </table>
                            <div class="orderbutton-container">
                                <div>
                                    <form action="CancelOrderController" method="POST">
                                        <button type="submit">Cancel Order</button>
                                    </form>
                                </div>
                                <div>
                                    <button onclick="window.location.href='cancelOrder.jsp';">Cancel Order</button>
                                </div>
                                <div>
                                    <form action="PostOrderController" method="POST">
                                        <button type="submit">Confirm Order</button>
                                    </form>
                                </div>
                                <div>
                                    <button onclick="window.location.href='PostOrder.jsp';">Confirm Order</button>
                                </div>
                                <div>
                                    <form action="UpdateFlightOrderController" method="POST">
                                        <button type="submit">Update Order</button>
                                    </form>
                                </div>
                                <div>
                                    <button onclick="window.location.href='updateFlightOrder.jsp';">Update Order</button>
                                </div>
                            </div>
                            <%
                        } else {
                            %>
                            <p>Error: No order details found.</p>
                            <%
                        }
                    } else {
                        %>
                        <p>Error: User not logged in or cart is empty.</p>
                        <%
                    }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
