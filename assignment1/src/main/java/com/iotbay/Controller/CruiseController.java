package com.iotbay.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Model.Cruise;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

public class CruiseController extends HttpServlet {

    private List<Cruise> cruiseList;

    @Override
    public void init() throws ServletException {

        cruiseList = new ArrayList<>();
        cruiseList.add(new Cruise(1, "Sydney Harbour Cruise", 150.00, "Sydney", "Enjoy a scenic cruise in Sydney Harbour", 50));
        cruiseList.add(new Cruise(2, "Great Barrier Reef Cruise", 200.00, "Cairns", "Explore the beauty of the Great Barrier Reef", 30));
        cruiseList.add(new Cruise(3, "Tasmanian Wilderness Cruise", 180.00, "Hobart", "Discover the wild landscapes of Tasmania", 40));
        cruiseList.add(new Cruise(4, "Melbourne Coastal Cruise", 120.00, "Melbourne", "Cruise along the stunning Melbourne coastline", 60));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Received GET action: " + action);

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User: " + (user != null ? user.getUserType() : "Anonymous"));

        if ("loadCruises".equals(action)) {
            loadCruises(request, response);
        } else if ("searchByPort".equals(action)) {
            searchCruisesByPort(request, response);
        } else if ("addCruise".equals(action) && user != null && user.getUserType() == UserType.STAFF) {
            request.getRequestDispatcher("/cruiseForm.jsp").forward(request, response);
        } else if ("viewDetails".equals(action)) {
            int cruiseId = Integer.parseInt(request.getParameter("cruiseId"));
            Cruise cruise = findCruiseById(cruiseId);
            if (cruise != null) {
                request.setAttribute("selectedCruise", cruise);
                request.getRequestDispatcher("/cruiseDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("cruiseBooking.jsp");  // 修改为 cruiseBooking.jsp
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Received POST action: " + action);

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User: " + (user != null ? user.getUserType() : "Anonymous"));

        if (user != null && user.getUserType() == UserType.STAFF) {
            if ("addCruise".equals(action)) {
                addCruise(request, response);
            }
        } else {
            response.sendRedirect("noPermission.jsp");
        }
    }

    private void loadCruises(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("cruises", cruiseList);
        request.getRequestDispatcher("cruiseBooking.jsp").forward(request, response);  // 修改为 cruiseBooking.jsp
    }

    private void searchCruisesByPort(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String port = request.getParameter("port");
        List<Cruise> filteredCruises = new ArrayList<>();
        for (Cruise cruise : cruiseList) {
            if (cruise.getPort().equalsIgnoreCase(port)) {
                filteredCruises.add(cruise);
            }
        }
        request.setAttribute("cruises", filteredCruises);
        request.getRequestDispatcher("cruiseBooking.jsp").forward(request, response);  // 修改为 cruiseBooking.jsp
    }

    private void addCruise(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
   
        Cruise cruise = new Cruise();
        cruise.setItemID(cruiseList.size() + 1); 

        cruise.setName(request.getParameter("name"));
        cruise.setPrice(Double.parseDouble(request.getParameter("price")));
        cruise.setPort(request.getParameter("port"));
        cruise.setDescription(request.getParameter("description"));
        cruise.setAvailability(Integer.parseInt(request.getParameter("availability")));

        cruiseList.add(cruise);
        System.out.println("Added new cruise: " + cruise.getName());

        response.sendRedirect("CruiseController?action=loadCruises");
    }

    private Cruise findCruiseById(int cruiseId) {
        for (Cruise cruise : cruiseList) {
            if (cruise.getItemID() == cruiseId) {
                return cruise;
            }
        }
        return null;
    }

}
