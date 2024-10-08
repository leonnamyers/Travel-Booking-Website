<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="com.iotbay.Model.*" %>
<%@ page import="com.iotbay.*" %>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Flight Order Details</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" flush="true" />
        <div class="outer-container">
            <div class="flex-container">
                <div>
                    <%
                    User user = (User) session.getAttribute("user");
                    Cart cart = (Cart) session.getAttribute("cart");
                    Order order = (Order) session.getAttribute("order");
                    
                    if (user == null) {
                        out.println("<p>Error: User not found in session.</p>");
                    }
                    if (order == null) {
                        out.println("<p>Error: Order not found in session.</p>");
                    }

                    if (user != null && order != null) {
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
                            <th>Start Time:</th>
                            <td><%= order.getStartTime() %></td>
                        </tr>
                        <tr>
                            <th>End Time:</th>
                            <td><%= order.getEndTime() %></td>
                        </tr>
                        <tr>
                            <th>Seat Type:</th>
                            <td>$<%= order.getSeatType() %></td>
                        </tr>
                    </table>
                    <br>
                    <div class="orderbutton-container">
                        <div>
                            <form action="CancelOrderController" method="POST">
                                <button type="submit">Cancel Order</button>
                            </form>
                        </div>
                        
                        <div>
                            <form action="PostOrderController" method="POST">
                                <button type="submit">Confirm Order</button>
                            </form>
                        </div>
                        <div>
                            <form action="UpdateFlightOrderController" method="GET">
                                <input type="hidden" name="orderID" value="<%= order.getOrderID() %>"/>
                                <button type="submit">Update Order</button>
                            </form>
                        </div>
                    </div>
                <%
                } else {
                %>
                    <p>Error: No order details found.</p>
                <%
                }
                %>
            </div>
        </div>
    </div>
    <jsp:include page="/ConnServlet" flush="true" />
</body>
</html>
