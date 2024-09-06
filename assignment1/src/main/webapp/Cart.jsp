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
        <title>View Cart</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <main class="text-display">
            <% if (cart == null || cart.isEmpty()) { %>
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
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            <th>Subtotal</th>
                        </tr>
                        <% int itemNumber = 0;
                            for (Product product : cart.getProducts()) { %>
                            <tr>
                                <td><%=product.getName()%></td>
                                <td><input type="number" name="item<%=itemNumber%>" min="0" max="<%=product.getStock()%>" value="<%=product.getQuantity()%>"/></td>
                                <td>$<%=product.getPrice()%></td>
                                <td>$<%=String.format("%.2f", product.calculatePrice())%></td>
                                <td><input type="submit" name="remove<%=itemNumber%>" value="Remove" class="table-button"/></td>
                            </tr>
                        <% itemNumber++; } %>
                    </table>
                    <h2 id="total-price">Total: $<%=cart.getTotalPrice()%></h2>
        
                    <div id="cart-buttons">
                        <input type="submit" name="action" value="Clear cart" class="general-buttons btn-outline-light"/>
                        <input type="submit" name="action" value="Save changes" class="general-buttons btn-outline-dark"/>
                        <input type="submit" name="action" value="Place order" class="add-to-cart-button"/>
                    </div>
                </form>
            </div>
            <% } %>
        </main>
    </body>
</html>