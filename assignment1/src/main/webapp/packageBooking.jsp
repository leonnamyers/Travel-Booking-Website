<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Package"%>
<%@ page import="java.util.List"%>
<%@ page import="com.iotbay.Model.User"%>
<%@ page import="com.iotbay.Model.UserType"%>
<!DOCTYPE html>
<html>
<head>
    <title>Package Booking</title>
    <link rel="stylesheet" href="css/package.css">
</head>
<body>

    <% 
        User user = (User) session.getAttribute("user");
        boolean isStaff = user != null && user.getUserType() == UserType.STAFF;

        String errorMessage = (String) request.getAttribute("error");
    %>

    <h1 class="packagebooking-heading">Choose Your Travel Package</h1>

    <% if (isStaff) { %>
        <form action="PackageController" method="get">
            <input type="hidden" name="action" value="addPackage" />
            <button type="submit" class="packagebooking-package-button">Add New Package</button>
        </form>
    <% } %>

    
    <% if (errorMessage != null) { %>
        <div class="error-message" style="color: red; font-weight: bold; margin-bottom: 20px;">
            <strong>Error:</strong> <%= errorMessage %>
        </div>
    <% } %>

    <div class="packagebooking-package-list">
        <% 
            List<Package> packages = (List<Package>) request.getAttribute("packages");

            if (packages != null && !packages.isEmpty()) {
                for (Package pkg : packages) { 
        %>
            <div class="packagebooking-package-item">
                <img src="images/<%= (pkg.getName() != null ? pkg.getName().replaceAll(" ", "_").toLowerCase() : "default_image") %>.jpg" 
                     alt="<%= (pkg.getName() != null ? pkg.getName() : "No Name") %>" class="packagebooking-package-image">
                <h2 class="packagebooking-package-name"><%= (pkg.getName() != null ? pkg.getName() : "Unnamed Package") %></h2>
                <p class="packagebooking-package-description"><%= (pkg.getDescription() != null ? pkg.getDescription() : "No description available.") %></p>
                <p class="packagebooking-package-price">Price: $<%= (pkg.getPrice() != 0.0 ? pkg.getPrice() : "N/A") %></p>

           
                <form action="PackageController" method="get" class="packagebooking-package-form">
                    <input type="hidden" name="action" value="viewDetails" />
                    <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" />
                    <button type="submit" class="packagebooking-package-button">View Details</button>
                </form>

                <% if (isStaff) { %>
               
                    <form action="EditPackageController" method="get" class="packagebooking-package-form">
                        <input type="hidden" name="action" value="editPackage" />
                        <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" />
                        <button type="submit" class="packagebooking-package-button">Edit</button>
                    </form>
                    
                  
                    <form action="DeletePackageController" method="post" class="packagebooking-package-form">
                        <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" />
                        <button type="submit" class="packagebooking-package-button">Delete</button>
                    </form>
                <% } %>
            </div>
        <% 
                } 
            } else { 
        %>
            <p class="packagebooking-no-packages">No packages available.</p>
        <% 
            } 
        %>
    </div>
</body>
</html>
