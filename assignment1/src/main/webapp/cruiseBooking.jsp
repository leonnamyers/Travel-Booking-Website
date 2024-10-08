<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Cruise"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iotbay.Model.User"%>
<%@ page import="com.iotbay.Model.UserType"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cruise Booking</title>
    <link rel="stylesheet" href="css/cruise.css">
</head>
<body>

    <% 
        User user = (User) session.getAttribute("user");
        boolean isStaff = user != null && user.getUserType() == UserType.STAFF;

        String errorMessage = (String) request.getAttribute("error");
    %>

    <h1 class="cruisebooking-heading">Choose Your Cruise</h1>

    <% if (isStaff) { %>
        <form action="CruiseController" method="get">
            <input type="hidden" name="action" value="addCruise" />
            <button type="submit" class="cruisebooking-cruise-button">Add New Cruise</button>
        </form>
    <% } %>

    
    <% if (errorMessage != null) { %>
        <div class="error-message" style="color: red; font-weight: bold; margin-bottom: 20px;">
            <strong>Error:</strong> <%= errorMessage %>
        </div>
    <% } %>


    <form action="CruiseController" method="get" class="cruisebooking-search-form">
        <input type="hidden" name="action" value="searchByPort" />
        <label for="port">Search by Port:</label>
        <input type="text" id="port" name="port" placeholder="Enter port" required>
        <button type="submit" class="cruisebooking-search-button">Search</button>
    </form>

    <div class="cruisebooking-cruise-list">
        <% 
            List<Cruise> cruises = (List<Cruise>) request.getAttribute("cruises");

            if (cruises != null && !cruises.isEmpty()) {
                for (Cruise cruise : cruises) { 
        %>
            <div class="cruisebooking-cruise-item">
                <img src="images/<%= (cruise.getName() != null ? cruise.getName().replaceAll(" ", "_").toLowerCase() : "default_image") %>.jpg" 
                     alt="<%= (cruise.getName() != null ? cruise.getName() : "No Name") %>" class="cruisebooking-cruise-image">
                <h2 class="cruisebooking-cruise-name"><%= (cruise.getName() != null ? cruise.getName() : "Unnamed Cruise") %></h2>
                <p class="cruisebooking-cruise-description"><%= (cruise.getDescription() != null ? cruise.getDescription() : "No description available.") %></p>
                <p class="cruisebooking-cruise-price">Price: $<%= (cruise.getPrice() != 0.0 ? cruise.getPrice() : "N/A") %></p>
                <p class="cruisebooking-cruise-port">Port: <%= (cruise.getPort() != null ? cruise.getPort() : "Unknown") %></p>

           
                <form action="CruiseController" method="get" class="cruisebooking-cruise-form">
                    <input type="hidden" name="action" value="viewDetails" />
                    <input type="hidden" name="cruiseId" value="<%= cruise.getItemID() %>" />
                    <button type="submit" class="cruisebooking-cruise-button">View Details</button>
                </form>

                <% if (isStaff) { %>
                  
                    <form action="EditCruiseController" method="get" class="cruisebooking-cruise-form">
                        <input type="hidden" name="action" value="editCruise" />
                        <input type="hidden" name="cruiseId" value="<%= cruise.getItemID() %>" />
                        <button type="submit" class="cruisebooking-cruise-button">Edit</button>
                    </form>
                    
                
                    <form action="DeleteCruiseController" method="post" class="cruisebooking-cruise-form">
                        <input type="hidden" name="cruiseId" value="<%= cruise.getItemID() %>" />
                        <button type="submit" class="cruisebooking-cruise-button">Delete</button>
                    </form>
                <% } %>
            </div>
        <% 
                } 
            } else { 
        %>
            <p class="cruisebooking-no-cruises">No cruises available.</p>
        <% 
            } 
        %>
    </div>
</body>
</html>
