// package com;

// import java.sql.Connection;

// import org.junit.jupiter.api.Test;

// import com.iotbay.Dao.DBConnector;
// import com.iotbay.Dao.DBManager;

//     DBConnector dbConnector;
//     Connection connection;
//     DBManager manager;

    // @Test
    // public void testDatabaseConnectionAndCRUD() {
    //     try {
     
    //         DBConnector dbConnector = new DBConnector();
    //         Connection connection = dbConnector.openConnection();

//     //         PackageDAO packageDAO = new PackageDAO(connection);

//             System.out.println("Inserting a new package...");
//             packageDAO.createPackage(
//                     "Sydney Opera House Tour",
//                     150.00,
//                     10,
//                     "http://example.com/images/opera.jpg",
//                     "A guided tour of the iconic Sydney Opera House.",
//                     "Discover the architectural marvel of the Sydney Opera House, a UNESCO World Heritage site and a global symbol of modern Australia.",
//                     "Guided Tour, Harbour Cruise, Evening Performance",
//                     "Private luxury transfers from Sydney Airport",
//                     "Daily breakfast at The Star Grand, with an optional gourmet dinner at the Opera Bar",
//                     "10% discount on all in-house dining",
//                     "John Doe",
//                     "1234567890");

    
    //         System.out.println("Inserting a new package...");
    //         packageDAO.createPackage(
    //             "Sydney Opera House Tour", 
    //             150.00, 
    //             10, 
    //             "http://example.com/images/opera.jpg", 
    //             "A guided tour of the iconic Sydney Opera House.", 
    //             "Discover the architectural marvel of the Sydney Opera House, a UNESCO World Heritage site and a global symbol of modern Australia.", 
    //             "Guided Tour, Harbour Cruise, Evening Performance", 
    //             "Private luxury transfers from Sydney Airport", 
    //             "Daily breakfast at The Star Grand, with an optional gourmet dinner at the Opera Bar", 
    //             "10% discount on all in-house dining", 
    //             "John Doe", 
    //             "1234567890"
    //         );

//             System.out.println("Updating a package...");
//             packageDAO.updatePackage(
//                     packages.get(0).getItemID(),
//                     "Sydney Opera House Tour - Updated",
//                     200.00,
//                     8,
//                     "http://example.com/images/opera_updated.jpg",
//                     "An updated guided tour of the iconic Sydney Opera House.",
//                     "Updated description.",
//                     "Updated activities",
//                     "Updated transportation",
//                     "Updated dining",
//                     "Updated special offer",
//                     "Jane Doe",
//                     "0987654321");

//             System.out.println("Deleting a package...");
//             packageDAO.deletePackage(packages.get(0).getItemID());

        
    //         System.out.println("Deleting a package...");
    //         packageDAO.deletePackage(packages.get(0).getItemID());

    //         connection.close();
    //     } catch (ClassNotFoundException | SQLException e) {
    //         fail("Exception occurred during database operations: " + e.getMessage());
    //     }
    // }
}
