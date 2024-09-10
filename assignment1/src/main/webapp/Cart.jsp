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
    <script type="text/javascript" src="js/index.js"></script>
    <title>Dream Escape - Book Your Trip</title>
    <nav>
        <h1>View Cart</h1>
    
    <div class="content">
        <%
        if (session != null && session.getAttribute("user") != null) {
            List<String> errors = (List<String>) session.getAttribute("errors");

        %>
        <div id="sessionErrors" class="error">
            <% for (String error : errors) { %>
                <p><%= error %></p>
            <% } %>
        </div>
        <% } %>
    
        <%
            User user = (User) session.getAttribute("user");
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
        <main class="text-display">
            <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart!= null && cart.isEmpty()) {
            %>
                <div id="empty-cart">
                    <h1>Your cart is empty</h1>
                    <p>Why not check out <a href="<%= request.getContextPath() %>">our store</a>?</p>
                </div>
            <% }  else {%>
            <h1 class="page-heading">Your cart</h1>
            <div id="cart">
                <form method="post" action="cart">
                    <table class="display-table">
                        <tr>
                            <th>Product</th>
                            <th>Unit Price</th>
                        </tr>
                        <% int itemNumber = 0;
                            for (Item item : cart.getItems()) { %>
                            <tr>
                                <td><%=item.getName()%></td>
                                <td>$<%=item.getPrice()%></td>
                            </tr>
                        <% itemNumber++; } %>
                    </table>
                    <h2 id="total-price">Total: $<%=cart.getTotalPrice()%></h2>
        
                    <div id="cart-buttons">
                        <input type="submit" name="action" value="Clear cart" class="general-buttons btn-outline-light"/>
                        <input type="submit" name="action" value="Place order" class="add-to-cart-button"/>
                    </div>
                </form>
            </div>
            <% } %>
        </main>
    </body>
</html>