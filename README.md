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

        Controllers: AddFlightController.java, AddFlightToCartController.java, DeleteFlightController.java, FilteringFlightController.java, FlightCatalogueController.java, UpdateFlightController.java, UpdateFlightFormController

        Testing Code: FlightDAOTest.java test the connection between dbeaver database and models

        Error mitigation: utilised servlet/controller side validation, as well as preformatted input that avoid errors in all jsp.
        
    HotelCatalogueManagement - Jialan Guo
        I did the CRUD feature for staff to manage hotels, as well as adding the hotel as Item to clients' shopping cart. 
        This include,

        JSP (view): addHotel.jsp, addHotelOperation.jsp,deleteHotel.jsp,hotels.jsp,updateHotel.jsp,UpdateHotelOperation.jsp,hotelBookingDateErr.jsp

        DAO and java class (model): HotelDAO.java, Hotel.java, CustomerHotel.java

        Controllers: AddHotelController.java, AddHotelToCartController.java, DeleteHotelController.java, FilteringHotelController.java, HotelCatalogueController.java, UpdateHotelController.java, UpdateHotelFormController

        Testing Code: HotelDAOTest.java test the connection between dbeaver database and models

        Error mitigation: utilised servlet/controller side validation, as well as preformatted input that avoid errors in all jsp.
     



    CustomerAccountManagement - Leonna Myers
    StaffAccountManagement - Leonna Myers
        I handled the features pertaining to basic User (Customer and Staff) functionality (login, logout, register, update, unregister).
        This includes,
        
        JSP (view):
            account_details.jsp
            index.jsp
            login.jsp
            logout.jsp
            register.jsp
            unregister.jsp
            update_user_details.jsp
            welcome.jsp
            error.jsp
        
        DAO:            
            DBManager.java
        
        Models: (Shared Responsibility with Anne Marie)
            Address.java
            Customer.java
            DummyUsers.java
            Staff.java
            StaffType.java
            User.java
            UserType.java
        
        Controller:
            CustomerAndStaffManagement Folder {
                LoginController.java
                RegisterController.java
                UnregisterController.java
                UpdateController.java
            }
            UserValidation.java
        
        Testing:
             AppTest.java
                Tested the logic and each DB methods (DBManager) at least once
        
        Error Mitigation:
            Used both server and client side error handling
                Server (see DataValidation.java & DBManager (Data Validation section(towards the bottom)))
                Client (Ensures required input fields are not Null, and fields are formatted correctly (in conjunction with server side error handling))
            JSP pages checks for User access
            Null values and Error throwing/catching is accounted for (see UserValidation.java, JSP pages and Controller classes)




    AdminStaffUserManagement - Anne Maree Hoang
        My task revolved around user management features for Staff members (either Clerk or System Admin).
        This includes the basic CRUD operations as well viewing a list of users and searching users.
        
        Files I worked with:
        JSP (view):
            accountDetailsPanel.jsp
            registerPanel.jsp
            updateUserPanel.jsp
            userListPanel.jsp
        
        DAO:            
            AdminStaffUserMgmtDAO.java
        
        Models: (Shared Responsibility with Leonna)
            Address.java
            Customer.java
            DummyUsers.java
            Staff.java
            StaffType.java
            User.java
            UserType.java
        
        Controller:
            AdminStaffUserMgmtController.java
            UserValidation.java
        
        Testing:
            AdminStaffUserMgmtTest.java
        
        Error Mitigation:
            Used both server and client side error handling
                Server (see DataValidation.java & DBManager (Data Validation section(towards the bottom)))
                Client (Ensures required input fields are not Null, and fields are formatted correctly (in conjunction with server side error handling))
            JSP pages checks for User access
            Null values and Error throwing/catching is accounted for (see UserValidation.java, JSP pages and Controller classes)
