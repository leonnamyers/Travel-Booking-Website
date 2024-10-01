// package com;

// import static org.junit.jupiter.api.Assertions.*;

// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.ArrayList;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.iotbay.Dao.DBConnector;
// import com.iotbay.Dao.PackageDAO;
// import com.iotbay.Model.Package;

// <<<<<<< HEAD
// public class PackageDAOTest {

//     private PackageDAO packageDAO;
//     private ArrayList<Package> DPackages;
// public class PackageDAOTest {
// /*
//     private PackageDAO packageDAO;
//     private ArrayList<Package> DPackages;
// >>>>>>> 1148b7124a673bfcc710e05bc028740d5201e678

// //     @BeforeEach
// //     public void setup() throws SQLException, ClassNotFoundException {
// //         if (isPipelineEnvironment()) {
// //             setupDData();  
// //         } else {
// //             setupDatabaseConnection();  
// //         }
// //     }

// //     private boolean isPipelineEnvironment() {
// //         String pipelineEnv = System.getenv("CI");
// //         return pipelineEnv != null && pipelineEnv.equals("true");
// //     }

// //     private void setupDatabaseConnection() throws ClassNotFoundException, SQLException {
// //         DBConnector connector = new DBConnector();
// //         Connection conn = connector.openConnection();
// //         packageDAO = new PackageDAO(conn);
// //     }

// //     private void setupDData() {
// //         DPackages = new ArrayList<>();

// //         Package sydneyOperaHouse = new Package(1, "Sydney Opera House Tour", 150.00, 10, "opera.jpg", "A guided tour of the iconic Sydney Opera House.");
// //         DPackages.add(sydneyOperaHouse);

// //         Package greatBarrierReef = new Package(2, "Great Barrier Reef Snorkeling", 400.00, 8, "reef.jpg", "Explore the wonders of the Great Barrier Reef with this snorkeling package.");
// //         DPackages.add(greatBarrierReef);

// //         Package uluruCamelTour = new Package(3, "Uluru Camel Tour", 250.00, 15, "uluru.jpg", "Experience the majesty of Uluru on a camel tour.");
// //         DPackages.add(uluruCamelTour);
// //     }

// //     @Test
// //     public void testFetchAllPackages() throws SQLException {
// //         if (isPipelineEnvironment()) {
// //             assertFalse(DPackages.isEmpty(), "Package list should not be empty.");
// //         } else {
// //             ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
// //             assertFalse(allPackages.isEmpty(), "Package list should not be empty.");
// //         }
// //     }


    
// //     @Test
// //     public void testFetchPackageById() throws SQLException {
// //         if (isPipelineEnvironment()) {
// //             // 假设在 pipeline 环境下，我们使用虚拟数据
// //             Package pkg = DPackages.stream().filter(p -> p.getItemID() == 1).findFirst().orElse(null);
// //             assertNotNull(pkg, "Package should not be null.");
// //             assertEquals(pkg.getName(), "Sydney Opera House Tour");
// //         } else {
// //             // 从数据库中获取现有的 package
// //             ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
// //             if (!allPackages.isEmpty()) {
// //                 Package pkg = allPackages.get(0);  // 获取第一个 package
// //                 packageDAO.updatePackage(pkg.getItemID(), "Updated Sydney Harbour Tour", 150.00, 18, 
// //                     "updated_harbour.jpg", "Updated description", 
// //                     "Updated introduction", "Updated activities", 
// //                     "Updated transportation", "Updated dining", 
// //                     "Updated special offer", "Updated contact", "0412345679");
    
// //                 Package updatedPackage = packageDAO.fetchPackageById(pkg.getItemID());
// //                 assertNotNull(updatedPackage, "Package should not be null.");
// //                 assertEquals(updatedPackage.getName(), "Updated Sydney Harbour Tour");
// //             } else {
// //                 fail("No packages found in the database.");
// //             }
// //         }
// //     }
    


// //     @Test
// //     public void testCreatePackage() throws SQLException {
// //         if (isPipelineEnvironment()) {
// //             DPackages.add(new Package(3, "Sydney Harbour Tour", 120.50, 20, "harbour.jpg", "Beautiful Sydney Harbour Tour"));
// //             assertTrue(DPackages.stream().anyMatch(pkg -> pkg.getName().equals("Sydney Harbour Tour")), "Package should be successfully created.");
// //         } else {
// //             packageDAO.createPackage("Sydney Harbour Tour", 120.50, 20, "harbour.jpg", "Beautiful Sydney Harbour Tour", "Explore the beauty of Sydney Harbour", "Boat Tour, Sightseeing", "Private ferry", "Lunch included", "20% discount", "Alice", "0412345678");

// //             ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
// //             assertTrue(allPackages.stream().anyMatch(pkg -> pkg.getName().equals("Sydney Harbour Tour")), "Package should be successfully created.");
// //         }
// //     }

// //     @Test
// //     public void testUpdatePackage() throws SQLException {
// //         if (isPipelineEnvironment()) {
// //             Package pkg = DPackages.get(0);
// //             pkg.setName("Updated Sydney Harbour Tour");
// //             pkg.setPrice(150.00);
// //             assertEquals(pkg.getName(), "Updated Sydney Harbour Tour", "Package name should be updated.");
// //             assertEquals(pkg.getPrice(), 150.00, "Package price should be updated.");
// //         } else {
// //             ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
// //             if (!allPackages.isEmpty()) {
// //                 Package pkg = allPackages.get(0);
// //                 packageDAO.updatePackage(pkg.getItemID(), "Updated Sydney Harbour Tour", 150.00, 18, "updated_harbour.jpg", "Updated description", "Updated introduction", "Updated activities", "Updated transportation", "Updated dining", "Updated special offer", "Updated contact", "0412345679");

// //                 Package updatedPackage = packageDAO.fetchPackageById(pkg.getItemID());
// //                 assertEquals(updatedPackage.getName(), "Updated Sydney Harbour Tour", "Package name should be updated.");
// //                 assertEquals(updatedPackage.getPrice(), 150.00, "Package price should be updated.");
// //             } else {
// //                 fail("No packages found to update.");
// //             }
// //         }
// //     }

// //     @Test
// //     public void testDeletePackage() throws SQLException {
// //         if (isPipelineEnvironment()) {
// //             DPackages.remove(DPackages.size() - 1);  
// //             assertFalse(DPackages.stream().anyMatch(pkg -> pkg.getName().equals("Package to Delete")), "Package should be successfully deleted.");
// //         } else {
// //             packageDAO.createPackage("Package to Delete", 100.00, 5, "delete_img.jpg", "Delete this package", "Test introduction", "Test activities", "Test transportation", "Test dining", "Test special offer", "Test contact", "0412345678");

// //             ArrayList<Package> allPackages = packageDAO.fetchAllPackages();
// //             Package lastPackage = allPackages.get(allPackages.size() - 1);  
// //             packageDAO.deletePackage(lastPackage.getItemID());

// <<<<<<< HEAD
// //             Package deletedPackage = packageDAO.fetchPackageById(lastPackage.getItemID());
// //             assertNull(deletedPackage, "Package should be successfully deleted.");
// //         }
// //     }
// // }
// =======
//             Package deletedPackage = packageDAO.fetchPackageById(lastPackage.getItemID());
//             assertNull(deletedPackage, "Package should be successfully deleted.");
//         }
//     }
//         */
// }
// >>>>>>> 1148b7124a673bfcc710e05bc028740d5201e678
