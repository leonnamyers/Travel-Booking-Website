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
    <script>
        window.onload = function() {
            // 确保使用 Number 将 JSP 值转为数字类型
            var pricePerPerson = Number("<%= selectedPackage != null ? selectedPackage.getPrice() : 0 %>");
            function calculateTotalPrice() {
                var numPeople = document.getElementById("numPeople").value;
                var totalPrice = pricePerPerson * numPeople;
                document.getElementById("totalPriceDisplay").innerText = totalPrice.toFixed(2); // 显示总价
                document.getElementById("totalPrice").value = totalPrice.toFixed(2); // 将总价传递到表单中
            }
            document.getElementById("numPeople").addEventListener("input", calculateTotalPrice);
        }
    </script>
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
                <p><strong>Price per Person:</strong> $<%= String.format("%.2f", selectedPackage.getPrice()) %></p>
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
            <form action="PackageDetailsController" method="post" class="package-details-form">
                <input type="hidden" name="packageId" value="<%= selectedPackage.getItemID() %>" /> <!-- 确保包裹 ID 正确传递 -->

                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" required>

                <label for="numPeople">Number of People:</label>
                <input type="number" id="numPeople" name="numPeople" min="1" required>

                <label for="contactName">Contact Name:</label>
                <input type="text" id="contactName" name="contactName" required>

                <label for="contactPhone">Contact Phone:</label>
                <input type="text" id="contactPhone" name="contactPhone" required>

                <!-- 显示总价格 -->
                <p><strong>Total Price:</strong> $<span id="totalPriceDisplay">0.00</span></p>
                <input type="hidden" id="totalPrice" name="totalPrice" value="0.00">

                <button type="submit" class="package-details-button">Book Now</button>
            </form>
            <% } %>
        </div>
    </div>

    <footer class="footer-container">
        <!-- 页脚代码保持不变 -->
    </footer>

</body>
</html>
