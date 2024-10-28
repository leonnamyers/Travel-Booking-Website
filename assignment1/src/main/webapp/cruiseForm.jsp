<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Cruise"%>
<!DOCTYPE html>
<html>
<head>
    <title><%= request.getParameter("action").equals("addCruise") ? "Add New Cruise" : "Edit Cruise" %></title>
    <link rel="stylesheet" href="css/cruise.css">
</head>
<body>

    <h1 class="cruisebooking-heading"><%= request.getParameter("action").equals("addCruise") ? "Add New Cruise" : "Edit Cruise" %></h1>

    <!-- Display error message if exists -->
    <% 
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage != null) { 
    %>
    <div class="error-message" style="color: red; font-weight: bold; margin-bottom: 20px;">
        <strong>Error:</strong> <%= errorMessage %>
    </div>
    <% 
        } 
    %>

    <form action="CruiseController" method="post">
        <input type="hidden" name="action" value="<%= request.getParameter("action") %>" />
        
        <% 
            Cruise cruise = (Cruise) request.getAttribute("selectedCruise");
            boolean isEditing = cruise != null;
        %>

        <% if (isEditing) { %>
            <input type="hidden" name="cruiseId" value="<%= cruise.getItemID() %>" />
        <% } %>

        <label for="name">Cruise Name:</label>
        <input type="text" name="name" value="<%= isEditing ? cruise.getName() : "" %>" required />

        <label for="price">Price:</label>
        <input type="text" name="price" value="<%= isEditing ? cruise.getPrice() : "" %>" required />

        <label for="availability">Availability:</label>
        <input type="text" name="availability" value="<%= isEditing ? cruise.getAvailability() : "" %>" required />

        <label for="img">Image Path (optional):</label>
        <input type="text" name="img" value="<%= isEditing ? cruise.getImg() : "" %>" />

        <label for="port">Departure Port:</label>
        <input type="text" name="port" value="<%= isEditing ? cruise.getPort() : "" %>" required />

        <label for="description">Description (optional):</label>
        <textarea name="description"><%= isEditing ? cruise.getDescription() : "" %></textarea>

        <label for="duration">Duration (nights):</label>
        <input type="number" name="duration" value="<%= isEditing ? cruise.getDuration() : "" %>" required />

        <label for="departureDate">Departure Date:</label>
        <input type="date" name="departureDate" value="<%= isEditing ? cruise.getDepartureDate() : "" %>" required />

        <label for="specialOffer">Special Offer (optional):</label>
        <textarea name="specialOffer"><%= isEditing ? cruise.getSpecialOffer() : "" %></textarea>

        <label for="location">Cruise Location:</label>
        <input type="text" name="location" value="<%= isEditing ? cruise.getLocation() : "" %>" required />

        <!-- 提交按钮 -->
        <button type="submit" class="cruisebooking-submit-button">Confirm</button>
    </form>

</body>
</html>
