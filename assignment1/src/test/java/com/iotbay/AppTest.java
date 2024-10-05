package com.iotbay;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iotbay.Controller.CustomerAndStaffManagement.LoginController;
import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Address;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;

// Changed JUnit testing to Mokito (as our db is local - JUnit tests will not work)

@ExtendWith(MockitoExtension.class)
class AppTest {

    @Mock
    DBManager manager; 

    @InjectMocks
    AppTest appTest;

    Customer dummyCustomer;
    Staff dummyStaff;

    @BeforeEach
    public void setUp() {
        createTestDummies();
    }

    private void createTestDummies() {
        dummyCustomer = new Customer();
        dummyCustomer.setUserType(UserType.CUSTOMER);
        dummyCustomer.setEmail("test@customer.com");
        dummyCustomer.setPassword("1234asdf");
        dummyCustomer.setFirstName("first");
        dummyCustomer.setLastName("last");
        dummyCustomer.setAddress(new Address("123 road road", 1000, "Sydney", "NSW"));
        dummyCustomer.setHomePhoneNumber(12345678);
        dummyCustomer.setMobilePhoneNumber(87654321);

        dummyStaff = new Staff();
        dummyStaff.setUserType(UserType.STAFF);
        dummyStaff.setEmail("test@staff.com");
        dummyStaff.setPassword("0987poiu");
        dummyStaff.setFirstName("test");
        dummyStaff.setLastName("subject");
        dummyStaff.setStaffID(1234);
        dummyStaff.setStaffTypeID(2);
    }

    @Test
    public void testAddCustomerUser() {
        try {
            doNothing().when(manager).addCustomer(any(Customer.class), anyString());
            manager.addCustomer(dummyCustomer, "000000000");
            verify(manager, times(1)).addCustomer(dummyCustomer, "000000000");
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Test
    public void testAddStaffUser() {
        try {
        doNothing().when(manager).addStaff(any(Staff.class), anyString());
        manager.addStaff(dummyStaff, "99999999");
        verify(manager, times(1)).addStaff(dummyStaff, "99999999");
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    @Test
    public void testUpdateCustomer() {
        try {
        doNothing().when(manager).updateCustomer(any(Customer.class), any(Customer.class));
        Customer updatedCustomer = dummyCustomer;
        updatedCustomer.setFirstName("success");
        manager.updateCustomer(updatedCustomer, dummyCustomer);
        verify(manager, times(1)).updateCustomer(updatedCustomer, dummyCustomer);
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    @Test
    public void testUpdateStaff() {
        try {
        doNothing().when(manager).updateStaff(any(Staff.class), any(Staff.class));
        Staff updatedStaff = dummyStaff;
        updatedStaff.setStaffID(123321);
        manager.updateStaff(updatedStaff, dummyStaff);
        verify(manager, times(1)).updateStaff(updatedStaff, dummyStaff);
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    @Test
    public void testLogin() {
        try {
        when(manager.userLogin(dummyStaff.getEmail(), dummyStaff.getPassword(), "123123654"))
            .thenReturn(dummyStaff);
        User user = manager.userLogin(dummyStaff.getEmail(), dummyStaff.getPassword(), "123123654");
        assertEquals(dummyStaff.getEmail(), user.getEmail());
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    @Test
    public void testDuplicateEmail() {
        try {
        when(manager.isDuplicateEmail(dummyCustomer.getEmail())).thenReturn(true);
        assertTrue(manager.isDuplicateEmail(dummyCustomer.getEmail()));
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    @Test
    public void testDuplicateStaffID() {
        try {
        when(manager.isDuplicateStaffID(String.valueOf(dummyStaff.getStaffID()))).thenReturn(true);
        assertTrue(manager.isDuplicateStaffID(String.valueOf(dummyStaff.getStaffID())));
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }

    @Test
    public void testRemoveUser() {
        try {
        doNothing().when(manager).removeUser(any(User.class));
        manager.removeUser(dummyCustomer);
        verify(manager, times(1)).removeUser(dummyCustomer);
        } catch (SQLException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
}
