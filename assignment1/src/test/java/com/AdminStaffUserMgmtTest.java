// package com;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.iotbay.Controller.CustomerAndStaffManagement.LoginController;
// import com.iotbay.Dao.DBManager;
// import com.iotbay.Dao.AdminStaffUserMgmtDAO;
// import com.iotbay.Model.Address;
// import com.iotbay.Model.Customer;
// import com.iotbay.Model.Staff;
// import com.iotbay.Model.User;
// import com.iotbay.Model.UserType;

// @ExtendWith(MockitoExtension.class)
// class AdminStaffUserMgmtTest {

//     @Mock
//     AdminStaffUserMgmtDAO manager;
    
//     @Mock
//     DBManager DBManager;

//     @InjectMocks
//     AdminStaffUserMgmtTest adminStaffUserMgmtTest;

//     Customer dummyCustomer;
//     Staff dummyStaff;

//     @BeforeEach
//     public void setUp() {
//         createTestDummies();
//     }

//     private void createTestDummies() {
//         dummyCustomer = new Customer();
//         dummyCustomer.setUserType(UserType.CUSTOMER);
//         dummyCustomer.setEmail("test@customer.com");
//         dummyCustomer.setPassword("1234asdf");
//         dummyCustomer.setFirstName("first");
//         dummyCustomer.setLastName("last");
//         dummyCustomer.setAddress(new Address("123 road road", 1000, "Sydney", "NSW"));
//         dummyCustomer.setHomePhoneNumber(12345678);
//         dummyCustomer.setMobilePhoneNumber(87654321);

//         dummyStaff = new Staff();
//         dummyStaff.setUserType(UserType.STAFF);
//         dummyStaff.setEmail("test@staff.com");
//         dummyStaff.setPassword("0987poiu");
//         dummyStaff.setFirstName("test");
//         dummyStaff.setLastName("subject");
//         dummyStaff.setStaffID(1234);
//         dummyStaff.setStaffTypeID(2);
//     }

//     @Test
//     public void testViewAllUsers() {
//         try {
//             // Mocking getAllUsers method
//             when(manager.getAllUsers()).thenReturn(new ArrayList<>(List.of(dummyCustomer, dummyStaff)));

//             // Calling the method
//             ArrayList<User> users = manager.getAllUsers();

//             // Verifying the method call and checking if the list contains the expected users
//             verify(manager, times(1)).getAllUsers();
//             assertEquals(2, users.size());
//             assertEquals(dummyCustomer.getEmail(), users.get(0).getEmail());
//             assertEquals(dummyStaff.getEmail(), users.get(1).getEmail());
//         } catch (SQLException e) {
//             fail("Exception thrown: " + e.getMessage());
//         }
//     }

//     @Test
//     public void testViewUserProfile() {
//         try {
//             when(manager.getUserByEmail(dummyCustomer.getEmail())).thenReturn(dummyCustomer);

//             User user = manager.getUserByEmail(dummyCustomer.getEmail());

//             // Verifying method call and checking returned user's details
//             verify(manager, times(1)).getUserByEmail(dummyCustomer.getEmail());
//             assertEquals(dummyCustomer.getEmail(), user.getEmail());
//         } catch (SQLException e) {
//             fail("Exception thrown: " + e.getMessage());
//         }
//     }

//     @Test
//     public void testAddNewUser() {
//         try {
//             // Mock addCustomer method in DBManager
//             doNothing().when(DBManager).addCustomer(any(Customer.class), anyString());
//             DBManager.addCustomer(dummyCustomer, "sessionID");
//             verify(DBManager, times(1)).addCustomer(dummyCustomer, "sessionID");
    
//             // Mock addStaff method in DBManager
//             doNothing().when(DBManager).addStaff(any(Staff.class), anyString());
//             DBManager.addStaff(dummyStaff, "sessionID");
//             verify(DBManager, times(1)).addStaff(dummyStaff, "sessionID");
//         } catch (SQLException e) {
//             fail("Exception thrown: " + e.getMessage());
//         }
//     }
    

//     @Test
//     public void testUpdateUser() {
//         try {
//             // For Customer
//             doNothing().when(manager).updateUser(any(Customer.class));
//             Customer updatedCustomer = dummyCustomer;
//             updatedCustomer.setFirstName("UpdatedName");
//             manager.updateUser(updatedCustomer);
//             verify(manager, times(1)).updateUser(updatedCustomer);

//             // For Staff
//             doNothing().when(manager).updateUser(any(Staff.class));
//             Staff updatedStaff = dummyStaff;
//             updatedStaff.setFirstName("UpdatedStaffName");
//             manager.updateUser(updatedStaff);
//             verify(manager, times(1)).updateUser(updatedStaff);
//         } catch (SQLException e) {
//             fail("Exception thrown: " + e.getMessage());
//         }
//     }

//     @Test
//     public void testDeleteUser() {
//         try {
//             // Testing for Customer
//             doNothing().when(manager).deleteUser(dummyCustomer.getEmail(), "CUSTOMER");
//             manager.deleteUser(dummyCustomer.getEmail(), "CUSTOMER");
//             verify(manager, times(1)).deleteUser(dummyCustomer.getEmail(), "CUSTOMER");
    
//             // Testing for Staff
//             doNothing().when(manager).deleteUser(dummyStaff.getEmail(), "STAFF");
//             manager.deleteUser(dummyStaff.getEmail(), "STAFF");
//             verify(manager, times(1)).deleteUser(dummyStaff.getEmail(), "STAFF");
//         } catch (SQLException e) {
//             fail("Exception thrown: " + e.getMessage());
//         }
//     }

//     @Test
//     public void testRoleBasedAccess() {
//         // Mocking logged-in staff (system admin)
//         Staff adminStaff = dummyStaff;
//         adminStaff.setStaffTypeID(2); // System admin type

//         // Verifying the role
//         assertEquals(2, adminStaff.getStaffTypeID());
//         assertTrue(adminStaff.getStaffTypeID() == 2);
//     }

// }
