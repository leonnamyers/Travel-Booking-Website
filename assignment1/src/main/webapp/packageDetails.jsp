<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Package"%>
<!DOCTYPE html>
<html>
<head>
    <%
        Package selectedPackage = (Package) request.getAttribute("selectedPackage");
    %>
    <title><%= selectedPackage != null ? selectedPackage.getName() : "Package Details" %></title>
    <link rel="stylesheet" href="css/packageDetails.css">
</head>
<body>

    <div class="package-image-container">
        <img src="images/<%= selectedPackage.getName().replaceAll(" ", "_").toLowerCase() %>.jpg" alt="<%= selectedPackage.getName() %>" class="package-image">
    </div>

    <div class="package-title-container">
        <h1 class="package-name"><%= selectedPackage.getName() %></h1>
    </div>

    <div class="package-details-container">
        <div class="package-details-left">
            <%
                if (selectedPackage != null) {
            %>
                <h2 class="package-title">Package Details</h2>
                <p><strong>Description:</strong> <%= selectedPackage.getDescription() %></p>
                <p><strong>Price:</strong> $<%= selectedPackage.getPrice() %></p>
                <p><strong>Introduction:</strong> <%= selectedPackage.getIntroduction() %></p>
                <p><strong>Activities:</strong> <%= selectedPackage.getActivities() %></p>
                <p><strong>Transportation:</strong> <%= selectedPackage.getTransportation() %></p>
                <p><strong>Dining:</strong> <%= selectedPackage.getDining() %></p>
            <% } else { %>
                <p>No package found.</p>
            <% } %>
        </div>
        <div class="package-details-right">
            <% if (selectedPackage != null) { %>
            <form action="BookingController" method="post" class="package-details-form">
                <input type="hidden" name="packageId" value="<%= selectedPackage.getId() %>" />

                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" required>

                <label for="numPeople">Number of People:</label>
                <input type="number" id="numPeople" name="numPeople" min="1" required>

                <label for="contactName">Contact Name:</label>
                <input type="text" id="contactName" name="contactName" required>

                <label for="contactPhone">Contact Phone:</label>
                <input type="text" id="contactPhone" name="contactPhone" required>

                <button type="submit" class="package-details-button">Book Now</button>
            </form>
            <% } %>
        </div>
    </div>

    <footer class="footer-container">
        <div class="footer-section">
            <h2>Flight Centre</h2>
            <ul>
                <li><a href="#">Flights</a></li>
                <li><a href="#">Stays</a></li>
                <li><a href="#">Holidays</a></li>
                <li><a href="#">Car hire</a></li>
                <li><a href="#">Tours</a></li>
                <li><a href="#">Cruises</a></li>
                <li><a href="#">Rail</a></li>
                <li><a href="#">Experiences & Day Tours</a></li>
            </ul>
        </div>
        <div class="footer-section">
            <h2>Company</h2>
            <ul>
                <li><a href="#">About us</a></li>
                <li><a href="#">Corporate Site</a></li>
                <li><a href="#">Careers</a></li>
                <li><a href="#">Advertising</a></li>
            </ul>
        </div>
        <div class="footer-section">
            <h2>Extras</h2>
            <ul>
                <li><a href="#">Insurance</a></li>
                <li><a href="#">Gift cards</a></li>
                <li><a href="#">Holiday finance</a></li>
                <li><a href="#">Captain's Pack</a></li>
            </ul>
        </div>
        <div class="footer-section">
            <h2>Help & support</h2>
            <ul>
                <li><a href="#">Help centre</a></li>
                <li><a href="#">Contact us</a></li>
                <li><a href="#">Store finder</a></li>
                <li><a href="#">Privacy</a></li>
                <li><a href="#">Cookies</a></li>
            </ul>
        </div>
    </footer>

</body>
</html>
