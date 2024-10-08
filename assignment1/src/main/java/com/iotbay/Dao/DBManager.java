package com.iotbay.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iotbay.Model.Address;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;

// This class contains and executes all of the SQL queries and PreparedStatements across the entire app
// Any Controller classes that read from and write to the DB should call methods from the DBManager and add their queries here
// User Database Validation also below

public class DBManager {
    // Customer and Staff Queries
    private final String customerLoginQuery = "SELECT * FROM Customer WHERE email=? AND password=?";
    private final String staffLoginQuery = "SELECT * FROM Staff WHERE email=? AND password=?";
    private final String updateCustomerQuery = "UPDATE Customer SET email=?, password=?, first_name=?, last_name=?, street_address=?, postcode=?, city=?, state=?, home_phone_number=?, mobile_phone_number=? WHERE email=?";
    private final String updateStaffQuery = "UPDATE Staff SET email=?, password=?, first_name=?, last_name=?, staff_id=?, staff_type_id=? WHERE email=?";
    private final String addCustomerQuery = "INSERT INTO Customer (email, password, first_name, last_name, street_address, postcode, city, state, home_phone_number, mobile_phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String addStaffQuery = "INSERT INTO Staff (email, password, first_name, last_name, staff_id, staff_type_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final String removeStaffQuery = "DELETE FROM Staff WHERE email= ?";
    private final String removeCustomerUserQuery = "DELETE FROM Customer WHERE email = ?";

    private final String fetchCustomerIdQuery = "SELECT customer_id FROM Customer WHERE email=?";


    // Data Validation Queries
    private final String checkCustomerUserDuplicateEmail = "SELECT COUNT(*) FROM Customer WHERE email = ?";
    private final String checkStaffUserDuplicateEmail = "SELECT COUNT(*) FROM Staff WHERE email = ?";
    private final String checkDuplicateStaffIDQuery =  "SELECT COUNT(*) FROM Staff WHERE staff_id = ?";
    
    private Connection connection;

    public DBManager(Connection connection) throws SQLException {
        connection.setAutoCommit(true);
        this.connection = connection;
    }

    /*
     * User Registration
     */

    public void addCustomer(Customer customer, String sessionId) throws SQLException{

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
        statement.executeUpdate();
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

    }

    /*
     * Only used for the update account (Customer & Staff) details feature
     */

    public void updateCustomer(Customer newData, Customer oldData) throws SQLException {

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
                Customer customer = new Customer();
                    customer.setEmail(result.getString("email"));
                    customer.setPassword(result.getString("password"));
                    Address address = new Address();
                    address.setCity(result.getString("city"));
                    address.setPostcode(result.getInt("postcode"));
                    address.setState(result.getString("state"));
                    address.setStreetAddress(result.getString("street_address"));
                    customer.setAddress(address);
                    customer.setFirstName(result.getString("first_name"));
                    customer.setLastName(result.getString("last_name"));

                    Integer homePhoneNumber = result.getInt("home_phone_number");
                    if (homePhoneNumber != null) {
                        customer.setHomePhoneNumber(homePhoneNumber);
                    }

                    Integer mobilePhoneNumber = result.getInt("mobile_phone_number");
                    if (mobilePhoneNumber != null) {
                        customer.setMobilePhoneNumber(mobilePhoneNumber);
                    }

                    /*
                    Integer paymentID = result.getInt("savedPaymentID");
                    if (paymentID != null) {
                        customerUser.setSavedPaymentID(paymentID);
                    }
                        */

                    // update login logs


                    return customer;
            }
        }
    
        try (PreparedStatement staffStatement = connection.prepareStatement(staffLoginQuery)) {
            staffStatement.setString(1, email);
            staffStatement.setString(2, password);
            ResultSet result = staffStatement.executeQuery();
    
            if (result.next()) {
                Staff staff = new Staff();
                staff.setStaffID(result.getInt("staff_iD"));
                staff.setEmail(result.getString("email"));
                staff.setPassword(result.getString("password"));
                staff.setFirstName(result.getString("first_name"));
                staff.setLastName(result.getString("last_name"));
                staff.setStaffTypeID(result.getInt("staff_type_id"));

                return staff;
            }
        } catch (SQLException e) {
            System.out.println("Error querying Staff: " + e);
        }

        // user cannot be found (login details incorrect)
        return null;
    }

    public int fetchCustomerId(String email) throws SQLException {
        int customerId = -1; // Default value if no ID is found

        // Prepare statement to execute the query
        PreparedStatement statement = connection.prepareStatement(fetchCustomerIdQuery);
        statement.setString(1, email);

        // Execute the query and get the result set
        ResultSet result = statement.executeQuery();

        // Extract customer ID if a result is found
        if (result.next()) {
            customerId = result.getInt("customer_id");
        }

        // Close the resources
        result.close();
        statement.close();

        return customerId; // Return the fetched ID or -1 if not found
    }

    /*
     * Unregister
     */

    public void removeUser(User user) throws SQLException {
        switch (user.getUserType()) {
            case CUSTOMER:
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
        return false;
    }


    
    /*
    public void placeOrder(Order order) {
                try {
            int orderId = getNextOrderId();

            PreparedStatement statement = connection.prepareStatement("insert into \"ORDER\" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, orderId);
            statement.setString(2, order.getName());
            statement.setString(3, order.getPhoneNo());
            statement.setString(4, Order.Status.Processing.name());
            statement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            statement.setString(6, order.getMethod().name());

            if (order.getUserId() == 0) {
                statement.setNull(7, Types.INTEGER);
            } else {
                statement.setInt(7, order.getUserId());
            }
            if (order.getPaymentId() == 0) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setInt(8, order.getPaymentId());
            }
            if (order.getCollectionId() == 0) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, order.getCollectionId());
            }
            if (order.getDeliveryId() == 0) {
                statement.setNull(10, Types.INTEGER);
            } else {
                statement.setInt(10, order.getDeliveryId());
            }

            statement.execute();

            for (OrderLine orderLine : orderLines) {
                statement = connection.prepareStatement("insert into ORDERLINE values (?, ?, ?)");
                statement.setInt(1, orderLine.getQuantity());
                statement.setInt(2, orderId);
                statement.setInt(3, orderLine.getProductId());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        */

}
    