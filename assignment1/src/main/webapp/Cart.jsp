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
        <h1>View Cart</h1>
    
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
        <main class="text-display">


            <% 
            Cart cart = (Cart) session.getAttribute("cart");
            
            User user = (User) session.getAttribute("user");
            if (user != null && cart != null) {
                cart.addItemToCart(DummyItems.createDummyFlight());
            }

            if (cart == null || cart.isEmpty()) {
            %>
                <div id="empty-cart">
                    <h1>Your cart is empty</h1>
                    <p>Why not check out <a href="index.jsp"">our store</a>?</p>
                </div>
            <% }  else {%>
            <h1 class="page-heading">Your cart</h1>
            <div id="cart">
                <form method="post" action="CartController">
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
                        <input type="submit" name="action" value="Place order" class="general-buttons btn-outline-light"/>
                    </div>
                </form>
            </div>
            <% } %>
        </main>
    </body>
</html>