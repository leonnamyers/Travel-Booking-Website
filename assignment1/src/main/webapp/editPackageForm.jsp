<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Package"%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Package</title>
    <link rel="stylesheet" href="css/package.css">
</head>
<body>

<% 

    Package pkg = (Package) request.getAttribute("selectedPackage"); 
    if (pkg == null) {
        out.println("<p style='color:red;'>Error: Package data is missing.</p>");
    }
%>

<form action="EditPackageController" method="post">
    <input type="hidden" name="action" value="updatePackage" />
    <input type="hidden" name="packageId" value="<%= pkg.getItemID() %>" />

    <label for="name">Package Name:</label>
    <input type="text" name="name" value="<%= pkg.getName() %>" required />

    <label for="price">Price:</label>
    <input type="text" name="price" value="<%= pkg.getPrice() %>" required />

    <label for="availability">Availability:</label>
    <input type="text" name="availability" value="<%= pkg.getAvailability() %>" required />

    <label for="img">Image Path (optional):</label>
    <input type="text" name="img" value="<%= pkg.getImg() %>" />

    <label for="description">Description (optional):</label>
    <textarea name="description"><%= pkg.getDescription() %></textarea>

    <label for="introduction">Introduction (optional):</label>
    <textarea name="introduction"><%= pkg.getIntroduction() %></textarea>

    <label for="activities">Activities (optional):</label>
    <textarea name="activities"><%= pkg.getActivities() %></textarea>

    <label for="transportation">Transportation (optional):</label>
    <textarea name="transportation"><%= pkg.getTransportation() %></textarea>

    <label for="dining">Dining (optional):</label>
    <textarea name="dining"><%= pkg.getDining() %></textarea>

    <label for="specialOffer">Special Offer (optional):</label>
    <textarea name="specialOffer"><%= pkg.getSpecialOffer() %></textarea>

    <input type="hidden" name="contactName" value="<%= pkg.getContactName() %>" />
    <input type="hidden" name="contactPhone" value="<%= pkg.getContactPhone() %>" />

    <button type="submit" class="packagebooking-submit-button">Confirm</button>
</form>

</body>
</html>
