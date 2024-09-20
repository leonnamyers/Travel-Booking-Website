<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Package"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Package Booking</title>
    <link rel="stylesheet" href="css/package.css">
</head>
<body>
    <h1 class="packagebooking-heading">Choose Your Travel Package</h1>
    <div class="packagebooking-package-list">
        <% 
            List<Package> packages = (List<Package>) request.getAttribute("packages");
            if (packages != null) {
                for (Package pkg : packages) { 
        %>
            <div class="packagebooking-package-item">
                <img src="images/<%= pkg.getName().replaceAll(" ", "_").toLowerCase() %>.jpg" alt="<%= pkg.getName() %>" class="packagebooking-package-image">
                <h2 class="packagebooking-package-name"><%= pkg.getName() %></h2>
                <p class="packagebooking-package-description"><%= pkg.getDescription() %></p>
                <p class="packagebooking-package-price">Price: $<%= pkg.getPrice() %></p>
                <form action="PackageController" method="get" class="packagebooking-package-form">
                    <input type="hidden" name="action" value="viewDetails" />
                    <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" /> <!-- 修改这里 -->
                    <button type="submit" class="packagebooking-package-button">View Details</button>
                </form>
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
