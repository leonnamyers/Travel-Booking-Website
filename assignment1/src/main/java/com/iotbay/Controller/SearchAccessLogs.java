package com.iotbay.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// logic for the feature to search User Access Logs by calendar date
public class SearchAccessLogs extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        HttpSession session = request.getSession();
        String inputDateString = request.getParameter("inputDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        UserValidation.clear(session);

        try {
            if (UserValidation.isDateValid(inputDateString)) {
                java.util.Date parsedDate = sdf.parse(inputDateString); // Parsing as java.util.Date
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Converting to java.sql.Date
                session.setAttribute("userLogsSearchDate", sqlDate);
                response.sendRedirect("search_logs_results.jsp");

            } else {
                session.setAttribute("dateFormatError", "Date is not formatted correctly. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (ParseException ex) {
            Logger.getLogger(SearchAccessLogs.class.getName()).log(Level.SEVERE, "Date parsing error", ex);
            session.setAttribute("dateFormatError", "An error occurred while parsing the date. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchAccessLogs.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("dateFormatError", "An unexpected error occurred. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
