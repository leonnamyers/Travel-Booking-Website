<div class="navbar">
    <div class="logo">
        Dream Escape
    </div>
    <ul>
        <li><a href="javascript:void(0);" onclick="loadPackagePage()">Package Booking</a></li>
        <li><a href="index.jsp?page=cruise">Cruise</a></li>
        <li><a href="//localhost:8080/flights.jsp">Flight</a></li>
    </ul>
    <% if (session.getAttribute("cart") != null) {
        %>
        <h1>cart is in session</h1>
        <%
    }
    %>
</div>

<script>
function loadPackagePage() {
    window.location.href = 'PackageController?action=loadPackages';
}
</script>

    <!-- commented out because it was causing errors
     
    <form action="search" method="GET" ></form>
    <div class="search-box">
        <label><input name="search" type="search" placeholder="Search IoT Products"></label>
        <button type="submit" class="search-button">
            <i class="fas fa-search"></i>
        </button>
    </div>
    </form>

    <div class="register-or-login">
        <% User user = (User) session.getAttribute("user");
            if (user != null) { %>
        <a href="account">Hello, <%= user.getUsername()%></a> | <a href="logout">Logout</a>
        <% } else { %>
        <a href="register">Register</a> | <a href="login">Login</a>
        <% } %>
    </div>
    <ul>
        <li><a href="javascript:void(0);" onclick="loadPackagePage()">Package Booking</a></li>
        <li><a href="index.jsp?page=cruise">Cruise</a></li>
        <li><a href="//localhost:8080/flights.jsp">Flight</a></li>
    </ul>
    <a href="cart">
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

</div>
</header>
-->