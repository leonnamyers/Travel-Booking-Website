package com.iotbay.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iotbay.Model.Package;

public class PackageController extends HttpServlet {

    private List<Package> packages;

    @Override
    public void init() throws ServletException {

        packages = new ArrayList<>();

        Package sydneyOperaHouse = new Package(1, "Sydney Opera House Tour", "A guided tour of the iconic Sydney Opera House.", 150.00);
        sydneyOperaHouse.setIntroduction("Discover the architectural marvel of the Sydney Opera House, a UNESCO World Heritage site and a global symbol of modern Australia.");
        sydneyOperaHouse.setActivities("Guided Tour, Harbour Cruise, Evening Performance");
        sydneyOperaHouse.setTransportation("Private luxury transfers from Sydney Airport");
        sydneyOperaHouse.setDining("Daily breakfast at The Star Grand, with an optional gourmet dinner at the Opera Bar");
        sydneyOperaHouse.setSpecialOffer("10% discount on all in-house dining");
        packages.add(sydneyOperaHouse);

        Package greatBarrierReef = new Package(2, "Great Barrier Reef Snorkeling", "Explore the wonders of the Great Barrier Reef with this snorkeling package.", 400.00);
        greatBarrierReef.setIntroduction("Dive into the world's largest coral reef system, teeming with marine life and vivid coral gardens.");
        greatBarrierReef.setActivities("Snorkeling, Scenic Flight, Island Hopping");
        greatBarrierReef.setTransportation("Return flights from Sydney to Hamilton Island, with private transfers to the hotel.");
        greatBarrierReef.setDining("All-inclusive dining at the Reef View Hotel, featuring fresh seafood and local delicacies.");
        greatBarrierReef.setSpecialOffer("Free sunset cruise with complimentary drinks.");
        packages.add(greatBarrierReef);

        Package uluruCamelTour = new Package(3, "Uluru Camel Tour", "Experience the majesty of Uluru on a camel tour.", 250.00);
        uluruCamelTour.setIntroduction("Immerse yourself in the spiritual heart of Australia with a camel ride around the iconic Uluru.");
        uluruCamelTour.setActivities("Camel Ride, Cultural Experience, Stargazing");
        uluruCamelTour.setTransportation("Flights from Hamilton Island to Ayers Rock, with luxury coach transfers.");
        uluruCamelTour.setDining("Bush tucker dinner featuring native Australian ingredients, served under the stars.");
        uluruCamelTour.setSpecialOffer("Complimentary spa treatment for two at the Red Ochre Spa.");
        packages.add(uluruCamelTour);

        Package daintreeRainforest = new Package(4, "Daintree Rainforest Adventure", "A full-day adventure in the Daintree Rainforest.", 350.00);
        daintreeRainforest.setIntroduction("Explore one of the oldest rainforests in the world, home to unique wildlife and lush greenery.");
        daintreeRainforest.setActivities("Jungle Walks, River Cruise, Canopy Ziplining");
        daintreeRainforest.setTransportation("Private transfers from Ayers Rock to Daintree Rainforest, including a scenic drive along the coast.");
        daintreeRainforest.setDining("Organic meals at the Daintree Ecolodge, featuring fresh, locally sourced ingredients.");
        daintreeRainforest.setSpecialOffer("Free night walk in the rainforest with an experienced guide.");
        packages.add(daintreeRainforest);

        Package melbourneSightseeing = new Package(5, "Melbourne City Sightseeing", "Explore Melbourne’s top attractions on this guided tour.", 120.00);
        melbourneSightseeing.setIntroduction("Discover the vibrant culture and history of Melbourne, known for its arts, food, and architecture.");
        melbourneSightseeing.setActivities("City Tour, Great Ocean Road, Art and Culture");
        melbourneSightseeing.setTransportation("Return flights from Cairns to Melbourne, with private transfers.");
        melbourneSightseeing.setDining("Breakfast included, with dining options at some of Melbourne's top restaurants.");
        melbourneSightseeing.setSpecialOffer("Complimentary tickets to a theater show at the Arts Centre Melbourne.");
        packages.add(melbourneSightseeing);

        Package kangarooIsland = new Package(6, "Kangaroo Island Wildlife Tour", "Discover the wildlife of Kangaroo Island.", 300.00);
        kangarooIsland.setIntroduction("Experience Australia's best wildlife haven, where kangaroos, koalas, and sea lions roam freely.");
        kangarooIsland.setActivities("Wildlife Safari, Sandboarding, Wine Tasting");
        kangarooIsland.setTransportation("Flights from Melbourne to Adelaide, with ferry transfers to Kangaroo Island.");
        kangarooIsland.setDining("All meals included at the Southern Ocean Lodge, featuring gourmet local cuisine.");
        kangarooIsland.setSpecialOffer("Private wildlife photography session with a professional photographer.");
        packages.add(kangarooIsland);

        System.out.println("PackageController initialized with packages: " + packages);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("viewDetails")) {
          
            int packageId = Integer.parseInt(request.getParameter("packageId"));
            Package selectedPackage = getPackageById(packageId);
            
          
            String contactName = request.getParameter("contactName");
            String contactPhone = request.getParameter("contactPhone");
            if (contactName != null) {
                selectedPackage.setContactName(contactName);
            }
            if (contactPhone != null) {
                selectedPackage.setContactPhone(contactPhone);
            }

            request.setAttribute("selectedPackage", selectedPackage);
            request.getRequestDispatcher("packageDetails.jsp").forward(request, response);
        } else if (action != null && action.equals("loadPackages")) {
           
            request.setAttribute("packages", packages);
            request.getRequestDispatcher("packageBooking.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

   
    private Package getPackageById(int id) {
        for (Package pkg : packages) {
            if (pkg.getId() == id) {
                return pkg;
            }
        }
        return null;
    }
}
