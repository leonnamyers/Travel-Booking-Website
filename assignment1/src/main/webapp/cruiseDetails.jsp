<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.iotbay.Model.Cruise"%>
<!DOCTYPE html>
<html>
<head>
    <%
        Cruise selectedCruise = (Cruise) request.getAttribute("selectedCruise");
    %>
    <title><%= selectedCruise != null ? selectedCruise.getName() : "Cruise Details" %></title>
    <link rel="stylesheet" href="css/cruiseDetails.css">
    <script>
        window.onload = function() {
            var pricePerPerson = Number("<%= selectedCruise != null ? selectedCruise.getPrice() : 0 %>");
            var today = new Date().toISOString().split('T')[0];
            document.getElementById("startDate").setAttribute("min", today);

            function calculateTotalPrice() {
                var numPeople = document.getElementById("numPeople").value;
                var totalPrice = pricePerPerson * numPeople;
                document.getElementById("totalPriceDisplay").innerText = totalPrice.toFixed(2);
                document.getElementById("totalPrice").value = totalPrice.toFixed(2);
            }

            document.getElementById("numPeople").addEventListener("input", calculateTotalPrice);

            document.getElementById("cruiseForm").addEventListener("submit", function(event) {
                var phonePattern = /^\+?[0-9]{1,3}[-\s]?[0-9]{7,10}$/; // 简单的电话号码正则表达式
                var phoneNumber = document.getElementById("contactPhone").value;
                if (!phonePattern.test(phoneNumber)) {
                    event.preventDefault(); // 阻止表单提交
                    alert("Invalid phone number format. Please enter a valid phone number.");
                }
            });
        }
    </script>
</head>
<body>

    <div class="cruise-image-container">
        <img src="<%= selectedCruise.getImg() %>" alt="<%= selectedCruise.getName() %>" class="cruise-image">
    </div>

    <div class="cruise-title-container">
        <h1 class="cruise-name"><%= selectedCruise.getName() %></h1>
    </div>

    <div class="cruise-details-container">
        <div class="cruise-details-left">
            <%
                if (selectedCruise != null) {
            %>
                <h2 class="cruise-title">Cruise Details</h2>
                <p><strong>Description:</strong> <%= selectedCruise.getDescription() %></p>
                <p><strong>Price per Person:</strong> $<%= String.format("%.2f", selectedCruise.getPrice()) %></p>
                <p><strong>Port:</strong> <%= selectedCruise.getPort() %></p>
                <p><strong>Availability:</strong> <%= selectedCruise.getAvailability() %> seats left</p>
            <% } else { %>
                <p>No cruise found.</p>
            <% } %>
        </div>
        <div class="cruise-details-right">
            <% if (selectedCruise != null) { %>
            <form id="cruiseForm" action="CruiseDetailsController" method="post" class="cruise-details-form">
                <input type="hidden" name="cruiseId" value="<%= selectedCruise.getItemID() %>" />

                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" required>

                <label for="numPeople">Number of People:</label>
                <input type="number" id="numPeople" name="numPeople" min="1" required>

                
                <label for="contactName">Contact Name:</label>
                <input type="text" id="contactName" name="contactName" required>

                <label for="countryCode">Country Code:</label>
                <select id="countryCode" name="countryCode" required>
                    <option value="+61">Australia (+61)</option>
                    <option value="+1">USA (+1)</option>
                    <option value="+44">UK (+44)</option>
                    <option value="+81">Japan (+81)</option>
                    <option value="+49">Germany (+49)</option>
                    <option value="+33">France (+33)</option>
                    <option value="+86">China (+86)</option>
                    <option value="+91">India (+91)</option>
                </select>

                <label for="contactPhone">Contact Phone:</label>
                <input type="text" id="contactPhone" name="contactPhone" required placeholder="Enter phone number without country code">

                <p><strong>Total Price:</strong> $<span id="totalPriceDisplay">0.00</span></p>
                <input type="hidden" id="totalPrice" name="totalPrice" value="0.00">

                <button type="submit" class="cruise-details-button">Book Now</button>
            </form>
            <% } %>
        </div>
    </div>

    <footer class="footer-container">
    </footer>

</body>
</html>
