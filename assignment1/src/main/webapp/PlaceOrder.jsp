<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="com.iotbay.*" %>
<%@page import="java.util.List"%>
<%@page import="com.iotbay.Model.User"%>

<%
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/index.js"></script>
    <title>Dream Escape - Book Your Trip</title>
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    <main class="text-display">
        <div class="content">
            <% 
            if (session.getAttribute("errors") != null) {
                List<String> errors = (List<String>) session.getAttribute("errors");
            %>
            <div id="sessionErrors" class="error">
                <% for (String error : errors) { %>
                    <p style="color: red"><%= error %></p>
                <% } %>
            </div>
            <% session.removeAttribute("errors"); } %>
            
            <h1 class="form-element">Book Your Trip</h1>
            <h2 class="form-element">Travel Details:</h2>
            
            <div class="form-container">
                <form method="post" action="book-trip">
                    <div class="form-element">
                        <label for="name">Name:</label>
                        <input type="text" name="name" id="name" value="<%= user != null ? user.getUsername() : "" %>" required/>
                    </div>
                    <div class="form-element">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" value="<%= user != null ? user.getEmail() : "" %>" required/>
                    </div>
                    <div class="form-element">
                        <label for="destination">Destination:</label>
                        <input type="text" name="destination" id="destination" required/>
                    </div>
                    <div class="form-element">
                        <label for="departureDate">Departure Date:</label>
                        <input type="date" name="departureDate" id="departureDate" required/>
                    </div>
                    <div class="form-element">
                        <label for="returnDate">Return Date:</label>
                        <input type="date" name="returnDate" id="returnDate" required/>
                    </div>
                    <div class="form-element">
                        <label for="passengers">Number of Passengers:</label>
                        <input type="number" name="passengers" id="passengers" min="1" required/>
                    </div>
                    <button class="general-buttons btn-outline-dark button-gap">Book Now ></button>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
