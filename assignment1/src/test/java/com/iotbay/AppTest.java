package com.iotbay;
/*
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Address;
import com.iotbay.Model.CustomerUser;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
*/
/**
 * There is one unit test per DAO method. These transactions are not actually committed to the 
 * database, but executed, tested and then rolled back - this is so there is no permanent effect on the DB.
 */
/*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppTest {

        DBConnector dbConnector;
        Connection connection;
        DBManager manager;
    
        CustomerUser dummyCustomerUser;
        Staff dummyStaff;
    
        private static final String getCustomer = "SELECT * FROM CustomerUser WHERE email = ?";
        private static final String getStaff = "SELECT * FROM Staff WHERE email = ?";
        private static final String getAccessLog = "SELECT * FROM UserAccessLogs WHERE sessionId = ?";
    
    
        @BeforeAll
        public void initialize() {
            try {
                dbConnector = new DBConnector();
                connection = dbConnector.openConnection();
                manager = new DBManager(connection); 
                connection.setAutoCommit(false);
                connection.prepareStatement("DELETE FROM CustomerUser WHERE email in ('test@customer.com', 'test@staff.com')").executeUpdate();
                connection.prepareStatement("DELETE FROM Staff WHERE email in ('test@customer.com', 'test@staff.com')").executeUpdate();
                connection.prepareStatement("DELETE FROM UserAccessLogs WHERE email in ('test@customer.com', 'test@staff.com')").executeUpdate();
                createDummies();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        private void createDummies() {
            dummyCustomerUser = new CustomerUser();
            dummyCustomerUser.setUserType(UserType.CUSTOMER_USER);
            dummyCustomerUser.setEmail("test@customer.com");
            dummyCustomerUser.setPassword("1234asdf");
            dummyCustomerUser.setFirstName("first");
            dummyCustomerUser.setLastName("last");
            dummyCustomerUser.setAddress(new Address("123 road road", 1000, "Sydney", "NSW"));
            dummyCustomerUser.setHomePhoneNumber(12345678);
            dummyCustomerUser.setMobilePhoneNumber(87654321);
    
            dummyStaff = new Staff();
            dummyStaff.setUserType(UserType.STAFF);
            dummyStaff.setEmail("test@staff.com");
            dummyStaff.setPassword("0987poiu");
            dummyStaff.setFirstName("test");
            dummyStaff.setLastName("subject");
            dummyStaff.setStaffID(1234);
            dummyStaff.setStaffTypeID(2);
        }
    
        @AfterEach
        public void cleanUp() {
            try {
                connection.rollback();
            }
            catch (SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }

        @AfterAll
        public void cleanUpAll() {
            try {
                dbConnector.closeConnection();
            }
            catch (SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        // Tests
        // As per instructions, 1 test per method in the UserManager (UserDAO) class
    
        @Test
        public void testAddCustomerUser() {
            try {
                manager.addCustomer(dummyCustomerUser, "000000000");
                PreparedStatement statement = connection.prepareStatement(getCustomer);
                statement.setString(1, dummyCustomerUser.getEmail());
                ResultSet res = statement.executeQuery();
                assertTrue(res.next());
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test
        public void testAddStaffUser() {
            try {
                manager.addStaff(dummyStaff, "99999999");
                PreparedStatement statement = connection.prepareStatement(getStaff);
                statement.setString(1, dummyStaff.getEmail());
                ResultSet res = statement.executeQuery();
                assertTrue(res.next());
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test 
        public void testUpdateAccessLogs() {
            try {
                manager.updateAccessLogs("123123123", dummyCustomerUser.getEmail());
                PreparedStatement statement = connection.prepareStatement(getAccessLog);
                statement.setString(1, "123123123");
                ResultSet res = statement.executeQuery();
                assertTrue(res.next());
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test
        public void testUpdateCustomer() {
            try {
                CustomerUser updatedDummy = dummyCustomerUser;
                updatedDummy.setFirstName("success");
                manager.addCustomer(dummyCustomerUser, "122233311");
                manager.updateCustomer(updatedDummy, dummyCustomerUser);

                PreparedStatement statement = connection.prepareStatement(getCustomer);
                statement.setString(1, dummyCustomerUser.getEmail());
                ResultSet res = statement.executeQuery();

                if (res.next()) {
                    assertTrue(res.getString("firstName").equals("success"));
                }
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test
        public void testUpdateStaff() {
            try {
                Staff updatedStaff = dummyStaff;
                updatedStaff.setStaffID(123321);
                manager.addStaff(dummyStaff, "123432115");
                manager.updateStaff(updatedStaff, dummyStaff);

                PreparedStatement statement = connection.prepareStatement(getStaff);
                statement.setString(1, updatedStaff.getEmail());
                ResultSet res = statement.executeQuery();

                if (res.next()) {
                    assertTrue(res.getInt("staffID") == 123321);
                }
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test
        public void testLogin() {
            try {
                manager.addStaff(dummyStaff, "3213541231");
                User user = manager.userLogin(dummyStaff.getEmail(), dummyStaff.getPassword(), "123123654");
                assertTrue(user.getEmail().equals(dummyStaff.getEmail()));
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        } 
    
        @Test
        public void testDuplicateEmail() {
            try {
                manager.addCustomer(dummyCustomerUser, "000000000");
                assertTrue(manager.isDuplicateEmail(dummyCustomerUser.getEmail()));
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }

        @Test
        public void testDuplicateStaffID() {
            try {
                manager.addStaff(dummyStaff, "000000000");
                assertTrue(manager.isDuplicateStaffID(dummyStaff.getStaffID() + ""));
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test
        public void testUpdateLogoutAccessLog() {
            try {
                manager.addCustomer(dummyCustomerUser, "123654789");
                manager.updateUserLogoutAccessLog("123654789");

                PreparedStatement statement = connection.prepareStatement(getAccessLog);
                statement.setString(1, "123654789");
                ResultSet res = statement.executeQuery();

                if (res.next()) {
                    assertTrue(!res.getString(1).isEmpty());
                }
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test
        public void testRemoveUser() {
            try {
                manager.addCustomer(dummyCustomerUser, "000000000");
                manager.removeUser(dummyCustomerUser);
                PreparedStatement statement = connection.prepareStatement(getCustomer);
                statement.setString(1, dummyCustomerUser.getEmail());
                ResultSet res = statement.executeQuery();
                assertFalse(res.next());
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    
        @Test 
        public void testGetUserLogs() {
            try {
                manager.addStaff(dummyStaff, "123123123");
                manager.updateUserLogoutAccessLog("123123123");
                manager.userLogin(dummyStaff.getEmail(), dummyStaff.getPassword(), "12345098");
                ArrayList<Timestamp[]> testList = manager.getUserLogs(dummyStaff.getEmail());
                assertTrue(testList.size() == 2);
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }

        @Test
        public void testSearchUserAccessLogs() {
            try {
                manager.addStaff(dummyStaff, "123123123");
                manager.updateUserLogoutAccessLog("123123123");
                manager.userLogin(dummyStaff.getEmail(), dummyStaff.getPassword(), "12345098");

                LocalDate currentDate = LocalDate.now();
                LocalDate futureDate = currentDate.plusYears(100);
                Date dummyDate = java.sql.Date.valueOf(futureDate);

                ArrayList<Timestamp[]> testList = manager.getSearchedUserAccessLogs(dummyStaff.getEmail(), dummyDate);

                assertTrue(testList.size() == 0);
            } catch(SQLException ex) {
                Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    }
    */
    
