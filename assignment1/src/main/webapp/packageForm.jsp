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

        <!-- 如果是编辑模式，显示 packageId -->
        <% if (isEditing) { %>
            <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" />
        <% } %>

        <label for="name">Package Name:</label>
        <input type="text" name="name" value="<%= isEditing ? pkg.getName() : "" %>" required />
        
        <label for="price">Price:</label>
        <input type="text" name="price" value="<%= isEditing ? pkg.getPrice() : "" %>" required />

        <label for="availability">Availability:</label>
        <input type="text" name="availability" value="<%= isEditing ? pkg.getAvailability() : "" %>" required />

        <label for="img">Image Path:</label>
        <input type="text" name="img" value="<%= isEditing ? pkg.getImg() : "" %>" required />

        <label for="description">Description:</label>
        <textarea name="description" required><%= isEditing ? pkg.getDescription() : "" %></textarea>

        <label for="introduction">Introduction:</label>
        <textarea name="introduction"><%= isEditing ? pkg.getIntroduction() : "" %></textarea>

        <label for="activities">Activities:</label>
        <textarea name="activities"><%= isEditing ? pkg.getActivities() : "" %></textarea>

        <label for="transportation">Transportation:</label>
        <textarea name="transportation"><%= isEditing ? pkg.getTransportation() : "" %></textarea>

        <label for="dining">Dining:</label>
        <textarea name="dining"><%= isEditing ? pkg.getDining() : "" %></textarea>

        <label for="specialOffer">Special Offer:</label>
        <textarea name="specialOffer"><%= isEditing ? pkg.getSpecialOffer() : "" %></textarea>

        <label for="contactName">Contact Name:</label>
        <input type="text" name="contactName" value="<%= isEditing ? pkg.getContactName() : "" %>" />

        <label for="contactPhone">Contact Phone:</label>
        <input type="text" name="contactPhone" value="<%= isEditing ? pkg.getContactPhone() : "" %>" />

        <button type="submit" class="packagebooking-submit-button">
            <%= isEditing ? "Update Package" : "Add Package" %>
        </button>
    </form>

</body>
</html>
