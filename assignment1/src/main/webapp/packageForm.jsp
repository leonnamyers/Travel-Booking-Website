<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Package"%>
<!DOCTYPE html>
<html>
<head>
    <title><%= request.getParameter("action").equals("addPackage") ? "Add New Package" : "Edit Package" %></title>
    <link rel="stylesheet" href="css/package.css">
</head>
<body>


    <h1 class="packagebooking-heading"><%= request.getParameter("action").equals("addPackage") ? "Add New Package" : "Edit Package" %></h1>


    <form action="PackageController" method="post">
   
        <input type="hidden" name="action" value="<%= request.getParameter("action") %>" />
        
        <% 
            Package pkg = (Package) request.getAttribute("selectedPackage");
            boolean isEditing = pkg != null;
        %>

        <% if (isEditing) { %>
     
            <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" />
        <% } %>

  
        <label for="name">Package Name:</label>
        <input type="text" name="name" value="<%= isEditing ? pkg.getName() : "" %>" required />
        
 
        <label for="price">Price:</label>
        <input type="text" name="price" value="<%= isEditing ? pkg.getPrice() : "" %>" required />

    
        <label for="availability">Availability:</label>
        <input type="text" name="availability" value="<%= isEditing ? pkg.getAvailability() : "" %>" required />

      
        <label for="img">Image Path (optional):</label>
        <input type="text" name="img" value="<%= isEditing ? pkg.getImg() : "" %>" />

       
        <label for="description">Description (optional):</label>
        <textarea name="description"><%= isEditing ? pkg.getDescription() : "" %></textarea>

  
        <label for="introduction">Introduction (optional):</label>
        <textarea name="introduction"><%= isEditing ? pkg.getIntroduction() : "" %></textarea>

        <label for="activities">Activities (optional):</label>
        <textarea name="activities"><%= isEditing ? pkg.getActivities() : "" %></textarea>

        <label for="transportation">Transportation (optional):</label>
        <textarea name="transportation"><%= isEditing ? pkg.getTransportation() : "" %></textarea>

        <label for="dining">Dining (optional):</label>
        <textarea name="dining"><%= isEditing ? pkg.getDining() : "" %></textarea>

        <label for="specialOffer">Special Offer (optional):</label>
        <textarea name="specialOffer"><%= isEditing ? pkg.getSpecialOffer() : "" %></textarea>

    
        <input type="hidden" name="contactName" value="<%= isEditing ? pkg.getContactName() : "" %>" />
        <input type="hidden" name="contactPhone" value="<%= isEditing ? pkg.getContactPhone() : "" %>" />

      
        <button type="submit" class="packagebooking-submit-button">
            <%= isEditing ? "Update Package" : "Add Package" %>
        </button>
    </form>

</body>
</html>
