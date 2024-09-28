# Travel Booking Website
 Assignment - Advanced Software Dev
 Repository structure:
    The respository branches are named after the functionality that it is testing and working on, such as Package Management is for developing package feature, Cart is for adding shopping cart in to the application.
    The branches that mainly contributed to the assignment are named after concrete functionality. And beside those, there are also other branches that fix errors and bugs, where it doesn't contribute to the work as much.

    Branch priority: Main > Concrete Functionality > All others

 Responsibility:
    FlightCatalogueManagement - Jialan Guo
        I did the CRUD feature for staff to manage flights, as well as adding the flight as Item to clients' shopping cart. 
        This include,

        JSP (view): addFlight.jsp, addFlightOperation.jsp,deleteFlight.jsp,flights.jsp,updateFlight.jsp,UpdateFlightOperation.jsp

        DAO and java class (model): FlightDAO.java, Flight.java

        Controllers: AddFlightController.java, DeleteFlightController.java, FilteringFlightController.java, FlightCatalogueController.java, UpdateFlightController.java, UpdateFlightFormController

        Testing Code: FlightDAOTest.java test the connection between dbeaver database and models

        Error mitigation: utilised servlet/controller side validation, as well as preformatted input that avoid errors in all jsp.
        
    HotelCatalogueManagement - Jialan Guo

