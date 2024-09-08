<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"> 
    <script type="text/javascript" src="js/index.js"></script>
    <title>Package Booking</title>
</head>
<body>
    <div class="outer-container">
        <div class="flex-container">
            <!-- Navigation Bar -->
            <nav>
                <h1>Your Travel Booking Website</h1>
                <ul>
                    <li><a href="SAMPLE_PAGE.jsp?pageView=package">Package Booking</a></li>
                    <li><a href="SAMPLE_PAGE.jsp?pageView=cruise">Cruise</a></li>
                    <li><a href="SAMPLE_PAGE.jsp?pageView=flights">Flights</a></li>
                    <li><a href="SAMPLE_PAGE.jsp?pageView=hotel">Hotel</a></li>
                </ul>
            </nav>

            <!-- Content Placeholder 包含导航栏和内容 -->
            <div class="content-placeholder">
                <div class="dynamic-content">
                    <%
                        String pageView = request.getParameter("pageView");
                        if (pageView == null || pageView.equals("package")) {
                            request.getRequestDispatcher("packageBooking.jsp").include(request, response);
                        } else if (pageView.equals("cruise")) {
                            request.getRequestDispatcher("cruise.jsp").include(request, response);
                        } else if (pageView.equals("flights")) {
                            request.getRequestDispatcher("flights.jsp").include(request, response);
                        } else if (pageView.equals("hotel")) {
                            request.getRequestDispatcher("hotel.jsp").include(request, response);
                        }
                    %>
                </div>
            </div>
        </div>            
    </div>        
</body>
</html>
