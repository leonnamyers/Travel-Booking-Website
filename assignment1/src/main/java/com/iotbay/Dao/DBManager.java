package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.iotbay.Model.Address;
import com.iotbay.Model.CustomerUser;
import com.iotbay.Model.Order;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;

// This class contains and executes all of the SQL queries and PreparedStatements across the entire app
// Any Controller classes that read from and write to the DB should call methods from the DBManager and add their queries here

public class DBManager {
    private final String customerLoginQuery = "SELECT * FROM CustomerUser WHERE email=? AND password=?";
    private final String staffLoginQuery = "SELECT * FROM Staff WHERE email=? AND password=?";
    private final String updateCustomerQuery = "UPDATE CustomerUser SET email=?, password=?, firstName=?, lastName=?, streetAddress=?, postcode=?, city=?, state=?, homePhoneNumber=?, mobilePhoneNumber=? WHERE email=?";
    private final String updateStaffQuery = "UPDATE Staff SET email=?, password=?, firstName=?, lastName=?, staffID=?, staffTypeID=? WHERE email=?";
    private final String addCustomerQuery = "INSERT INTO CustomerUser (email, password, firstName, lastName, streetAddress, postcode, city, state, homePhoneNumber, mobilePhoneNumber, isActivated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String addStaffQuery = "INSERT INTO Staff (email, password, firstName, lastName, staffID, staffTypeID) VALUES (?, ?, ?, ?, ?, ?)";
    private final String removeStaffQuery = "DELETE FROM Staff WHERE email= ?";
    private final String removeCustomerUserQuery = "DELETE FROM CustomerUser WHERE email = ?";
    private final String updateUserLogQuery = "INSERT INTO UserAccessLogs (sessionId, email) VALUES (?, ?)";
    private final String setUserLogoutQuery = "UPDATE UserAccessLogs SET logoutDateTime = CURRENT_TIMESTAMP WHERE sessionId = ?";
    private final String getUserAccessDataQuery = "SELECT loginDateTime, logoutDateTime FROM UserAccessLogs WHERE email = ?";
    private final String checkCustomerUserDuplicateEmail = "SELECT COUNT(*) FROM CustomerUser WHERE email = ?";
    private final String checkStaffUserDuplicateEmail = "SELECT COUNT(*) FROM Staff WHERE email = ?";
    private final String checkDuplicateStaffIDQuery =  "SELECT COUNT(*) FROM Staff WHERE StaffId = ?";
    private final String updateUserAccessLogsEmailQuery = "UPDATE UserAccessLogs SET email = ? WHERE email = ?";
    private final String deleteUserAccessLogQuery = "DELETE FROM UserAccessLogs WHERE email = ?";
    private final String searchUserAccessLogsQuery = "SELECT loginDateTime, logoutDateTime FROM UserAccessLogs WHERE email = ? AND (DATE(loginDateTime) = ? OR DATE(logoutDateTime) = ?)";


    private Connection connection;

    public DBManager(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        this.connection = connection;
    }

    /*
     * User Registration
     */

    public void addCustomer(CustomerUser customer, String sessionId) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(addCustomerQuery);
        statement.setString(1, customer.getEmail());
        statement.setString(2, customer.getPassword());
        statement.setString(3, customer.getFirstName());
        statement.setString(4, customer.getLastName());
        statement.setString(5, customer.getAddress().getStreetAddress());
        statement.setInt(6, customer.getAddress().getPostcode());
        statement.setString(7, customer.getAddress().getCity());
        statement.setString(8, customer.getAddress().getState());
        statement.setInt(9, customer.getHomePhoneNumber());
        statement.setInt(10, customer.getMobilePhoneNumber());
        statement.setBoolean(11, customer.isActivated());
        statement.executeUpdate();

        updateAccessLogs(sessionId, customer.getEmail());
    }

    public void addStaff(Staff staff, String sessionId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addStaffQuery);
        statement.setString(1, staff.getEmail());
        statement.setString(2, staff.getPassword());
        statement.setString(3, staff.getFirstName());
        statement.setString(4, staff.getLastName());
        statement.setInt(5, staff.getStaffID());
        statement.setInt(6, staff.getStaffTypeID());
        statement.executeUpdate();

        updateAccessLogs(sessionId, staff.getEmail());
    }

    /*
     * Update Access Logs by adding entries to the UserAccessLogs table at login/registration and logout time.
     * Can be private as its only called through this class, but made it public so it can be unit tested
     */
     public void updateAccessLogs(String sessionID, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateUserLogQuery);
        statement.setString(1, sessionID);
        statement.setString(2, email);
        statement.executeUpdate();
     }

    /*
     * Only used for the update details feature
     */
    public void updateCustomer(CustomerUser newData, CustomerUser oldData) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateCustomerQuery);
        statement.setString(1, newData.getEmail());
        statement.setString(2, newData.getPassword());
        statement.setString(3, newData.getFirstName());
        statement.setString(4, newData.getLastName());
        statement.setString(5, newData.getAddress().getStreetAddress());
        statement.setInt(6, newData.getAddress().getPostcode());
        statement.setString(7, newData.getAddress().getCity());
        statement.setString(8, newData.getAddress().getState());
        statement.setInt(9, newData.getHomePhoneNumber());
        statement.setInt(10, newData.getMobilePhoneNumber());
        statement.setString(11, oldData.getEmail());
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected == 0) {
            System.out.println("Update failed - Customer not found");
        }
        statement.close();

        updateEmailInUserAccessLogs(newData.getEmail(), oldData.getEmail());
    }
    

    private void updateEmailInUserAccessLogs(String newEmail, String oldEmail) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateUserAccessLogsEmailQuery);
        statement.setString(1, newEmail);
        statement.setString(2, oldEmail);
        statement.executeUpdate();
     }

    public void updateStaff(Staff newData, Staff oldData) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateStaffQuery);
        statement.setString(1, newData.getEmail());
        statement.setString(2, newData.getPassword());
        statement.setString(3, newData.getFirstName());
        statement.setString(4, newData.getLastName());
        statement.setInt(5, newData.getStaffID());
        statement.setInt(6, newData.getStaffTypeID());
        statement.setString(7, oldData.getEmail());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated == 0) {
            System.out.println("Update failed - Staff not likely found");
        }
        statement.close();

        updateEmailInUserAccessLogs(newData.getEmail(), oldData.getEmail());
    } 

    /*
     * Login
     */
    public User userLogin(String email, String password, String sessionId) throws SQLException {
        try (PreparedStatement customerStatement = connection.prepareStatement(customerLoginQuery)) {
            customerStatement.setString(1, email);
            customerStatement.setString(2, password);
            ResultSet result = customerStatement.executeQuery();
    
            if (result.next()) {
                CustomerUser customerUser = new CustomerUser();
                    customerUser.setEmail(result.getString("email"));
                    customerUser.setPassword(result.getString("password"));
                    Address address = new Address();
                    address.setCity(result.getString("city"));
                    address.setPostcode(result.getInt("postcode"));
                    address.setState(result.getString("state"));
                    address.setStreetAddress(result.getString("streetAddress"));
                    customerUser.setAddress(address);
                    customerUser.setFirstName(result.getString("firstName"));
                    customerUser.setLastName(result.getString("lastName"));
                    customerUser.setActivated(result.getBoolean("isActivated"));

                    Integer homePhoneNumber = result.getInt("homePhoneNumber");
                    if (homePhoneNumber != null) {
                        customerUser.setHomePhoneNumber(homePhoneNumber);
                    }

                    Integer mobilePhoneNumber = result.getInt("mobilePhoneNumber");
                    if (mobilePhoneNumber != null) {
                        customerUser.setMobilePhoneNumber(mobilePhoneNumber);
                    }

                    Integer paymentID = result.getInt("savedPaymentID");
                    if (paymentID != null) {
                        customerUser.setSavedPaymentID(paymentID);
                    }

                    Integer shipmentID = result.getInt("savedShipmentID");
                    if (shipmentID != null) {
                        customerUser.setSavedShipmentID(shipmentID);
                    }

                    // update login logs
                    updateAccessLogs(sessionId, email);

                    return customerUser;
            }
        }
    
        try (PreparedStatement staffStatement = connection.prepareStatement(staffLoginQuery)) {
            staffStatement.setString(1, email);
            staffStatement.setString(2, password);
            ResultSet result = staffStatement.executeQuery();
    
            if (result.next()) {
                Staff staff = new Staff();
                staff.setStaffID(result.getInt("staffID"));
                staff.setEmail(result.getString("email"));
                staff.setPassword(result.getString("password"));
                staff.setFirstName(result.getString("firstName"));
                staff.setLastName(result.getString("lastName"));
                staff.setStaffTypeID(result.getInt("staffTypeID"));
                
                // update login logs
                updateAccessLogs(sessionId, email);

                return staff;
            }
        } catch (SQLException e) {
            System.out.println("Error querying Staff: " + e);
        }

        // user cannot be found (login details incorrect)
        return null;
    }

    /*
     * Logout
     */

    public void updateUserLogoutAccessLog(String sessionId) {
        try (PreparedStatement statement = connection.prepareStatement(setUserLogoutQuery)) {
            statement.setString(1, sessionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating logout logs: " + e);
        }
    }

    /*
     * Unregister
     */

    public void removeUser(User user) throws SQLException {
        switch (user.getUserType()) {
            case CUSTOMER_USER:
                PreparedStatement customerStatement = connection.prepareStatement(removeCustomerUserQuery);
                    customerStatement.setString(1, user.getEmail());
                    customerStatement.executeUpdate();
                    break;
            case STAFF:
                PreparedStatement staffStatement = connection.prepareStatement(removeStaffQuery);
                    staffStatement.setString(1, user.getEmail());
                    staffStatement.executeUpdate();
                break;
            default:
                System.out.println("Error removing User: Can't find UserType");
            }
            // remove logs
            PreparedStatement removeAccessLogs = connection.prepareStatement(deleteUserAccessLogQuery);
            removeAccessLogs.setString(1, user.getEmail());
            removeAccessLogs.executeUpdate();
    }

    /*
     * Database Validation
     */

    public boolean isDuplicateEmail(String email) throws SQLException {
        PreparedStatement preparedCustomerUserStatement = connection.prepareStatement(checkCustomerUserDuplicateEmail); 
        preparedCustomerUserStatement.setString(1, email);
        ResultSet customerResultSet = preparedCustomerUserStatement.executeQuery();

        PreparedStatement preparedStaffStatement = connection.prepareStatement(checkStaffUserDuplicateEmail); 
        preparedStaffStatement.setString(1, email);
        ResultSet staffResultSet = preparedStaffStatement.executeQuery();

        int customerCount = 0;
        int staffCount = 0;

        if (customerResultSet.next()) {
          customerCount = customerResultSet.getInt(1);
        }

        if (staffResultSet.next()) {
            staffCount = staffResultSet.getInt(1);
        }

        if (customerCount != 0 || staffCount != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDuplicateStaffID(String staffID) throws SQLException {
        PreparedStatement preparedStaffStatement = connection.prepareStatement(checkDuplicateStaffIDQuery); 
        preparedStaffStatement.setString(1, staffID);
        ResultSet staffResultSet = preparedStaffStatement.executeQuery();

        if (staffResultSet.next()) {
            int staffCount = staffResultSet.getInt(1);
            if (staffCount != 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
     * User Access Logs
     */

    // row 0 == Login time
    // row 1 == Logout time
    public ArrayList<Timestamp[]> getUserLogs(String email) throws SQLException{
        ArrayList<Timestamp[]> results = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(getUserAccessDataQuery);
        statement.setString(1, email);
        ResultSet rs =  statement.executeQuery();
        while (rs.next()) {
            Timestamp[] row = new Timestamp[2];
            row[0] = rs.getTimestamp("loginDateTime");
            row[1] = rs.getTimestamp("logoutDateTime");
            results.add(row);
        }
        return results;
    }

    public ArrayList<Timestamp[]> getSearchedUserAccessLogs(String email, Date date) throws SQLException {
        ArrayList<Timestamp[]> logs = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(searchUserAccessLogsQuery);
        statement.setString(1, email);
        statement.setDate(2, date);
        statement.setDate(3, date);
        
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Timestamp loginTime = resultSet.getTimestamp("loginDateTime");
            Timestamp logoutTime = resultSet.getTimestamp("logoutDateTime");
            logs.add(new Timestamp[]{loginTime, logoutTime});
        }
        return logs;
    }

    public void placeOrder(Order order) {
        
    }

}
    