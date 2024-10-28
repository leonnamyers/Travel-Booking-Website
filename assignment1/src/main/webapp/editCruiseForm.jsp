<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Cruise"%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Cruise</title>
    <link rel="stylesheet" href="css/cruise.css">
</head>
<body>

<% 
    Cruise cruise = (Cruise) request.getAttribute("selectedCruise"); 
    if (cruise == null) {
        out.println("<p style='color:red;'>Error: Cruise data is missing.</p>");
    }
%>

<form action="EditCruiseController" method="post">
    <input type="hidden" name="action" value="updateCruise" />
    <input type="hidden" name="cruiseId" value="<%= cruise.getItemID() %>" />

    <label for="name">Cruise Name:</label>
    <input type="text" name="name" value="<%= cruise.getName() %>" required />

    <label for="price">Price:</label>
    <input type="text" name="price" value="<%= cruise.getPrice() %>" required />

    <label for="availability">Availability:</label>
    <input type="text" name="availability" value="<%= cruise.getAvailability() %>" required />

    <label for="img">Image Path (optional):</label>
    <input type="text" name="img" value="<%= cruise.getImg() %>" />

    <label for="port">Departure Port:</label>
    <input type="text" name="port" value="<%= cruise.getPort() %>" required />

    <label for="description">Description (optional):</label>
    <textarea name="description"><%= cruise.getDescription() %></textarea>

    <label for="duration">Duration (in nights):</label>
    <input type="text" name="duration" value="<%= cruise.getDuration() %>" required />

    <label for="departureDate">Departure Date:</label>
    <input type="date" name="departureDate" value="<%= cruise.getDepartureDate() %>" required />

    <label for="specialOffer">Special Offer (optional):</label>
    <textarea name="specialOffer"><%= cruise.getSpecialOffer() %></textarea>

    <label for="location">Location (optional):</label>
    <input type="text" name="location" value="<%= cruise.getLocation() %>" />

    <button type="submit" class="cruisebooking-submit-button">Confirm</button>
</form>

</body>
</html>
