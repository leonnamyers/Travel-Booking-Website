package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.iotbay.Model.Address;
import com.iotbay.Model.Customer;
import com.iotbay.Model.Staff;
import com.iotbay.Model.User;
import com.iotbay.Model.UserType;
import com.iotbay.Controller.UserValidation;



public class AdminStaffUserMgmtDAO {

    //queries
    private final String addCustomerQuery = "INSERT INTO Customer (email, first_name, last_name, password, street_address, postcode, city, state, home_phone_number, mobile_phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String addStaffQuery = "INSERT INTO Staff (email, first_name, last_name, password, staff_id, staff_type_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final String getAllUsersQuery = "SELECT email, first_name AS firstName, last_name AS lastName, 'CUSTOMER' AS userType, password, street_address, postcode, city, state, home_phone_number, mobile_phone_number, NULL AS staff_id, NULL AS staff_type_id FROM Customer UNION SELECT email, first_name AS firstName, last_name AS lastName, 'STAFF' AS userType, password, NULL AS street_address, NULL AS postcode, NULL AS city, NULL AS state, NULL AS home_phone_number, NULL AS mobile_phone_number, staff_id, staff_type_id FROM Staff";
    private final String getCustomersQuery = "SELECT email, first_name AS firstName, last_name AS lastName, password, street_address, postcode, city, state, home_phone_number, mobile_phone_number FROM Customer";
    private final String updateCustomerQuery = "UPDATE Customer SET first_name = ?, last_name = ?, password = ?, street_address = ?, postcode = ?, city = ?, state = ?, home_phone_number = ?, mobile_phone_number = ? WHERE email = ?";
    private final String updateStaffQuery = "UPDATE Staff SET first_name = ?, last_name = ?, password = ?, staff_type_id = ? WHERE email = ?";
    private final String deleteCustomerQuery = "DELETE FROM Customer WHERE email = ?";
    private final String deleteStaffQuery = "DELETE FROM Staff WHERE email = ?";
    private final String searchUserQuery = "SELECT email, first_name AS firstName, last_name AS lastName, 'CUSTOMER' AS userType, password, NULL AS staff_id, NULL AS staff_type_id FROM Customer WHERE LOWER(CONCAT(first_name, ' ', last_name)) LIKE ? UNION SELECT email, first_name AS firstName, last_name AS lastName, 'STAFF' AS userType, password, staff_id, staff_type_id FROM Staff WHERE LOWER(CONCAT(first_name, ' ', last_name)) LIKE ?";
    private final String searchCustomerQuery = "SELECT email, first_name AS firstName, last_name AS lastName, password FROM Customer WHERE LOWER(CONCAT(first_name, ' ', last_name)) LIKE ?";
    private final String getUserByEmailQuery = "SELECT email, password, first_name, last_name, 'CUSTOMER' as userType, street_address, postcode, city, state, home_phone_number, mobile_phone_number, NULL AS staff_id, NULL AS staff_type_id FROM Customer WHERE email = ? UNION SELECT email, password, first_name, last_name, 'STAFF' as userType, NULL AS street_address, NULL AS postcode, NULL AS city, NULL AS state, NULL AS home_phone_number, NULL AS mobile_phone_number, staff_id, staff_type_id FROM Staff WHERE email = ?";
    private final String deleteUserAccessLogQuery = "DELETE FROM UserAccessLogs WHERE email = ?";

    private Connection conn;

    public AdminStaffUserMgmtDAO(Connection conn) throws SQLException{
        conn.setAutoCommit(true);
        this.conn = conn;
    }


    // // Add new customer to the database
    // public void addCustomer(Customer customer) throws SQLException {
    //     try (PreparedStatement insertStatement = conn.prepareStatement(addCustomerQuery)) {
    //         insertStatement.setString(1, customer.getEmail());
    //         insertStatement.setString(2, customer.getFirstName());
    //         insertStatement.setString(3, customer.getLastName());
    //         insertStatement.setString(4, customer.getPassword());
    //         insertStatement.setString(5, customer.getAddress().getStreetAddress());
    //         insertStatement.setInt(6, customer.getAddress().getPostcode());
    //         insertStatement.setString(7, customer.getAddress().getCity());
    //         insertStatement.setString(8, customer.getAddress().getState());
    //         insertStatement.setInt(9, customer.getHomePhoneNumber());
    //         insertStatement.setInt(10, customer.getMobilePhoneNumber());

    //         insertStatement.executeUpdate();
    //     }
    // }

    // // Add new staff to the database
    // public void addStaff(Staff staff) throws SQLException {
    //     try (PreparedStatement insertStatement = conn.prepareStatement(addStaffQuery)) {
    //         insertStatement.setString(1, staff.getEmail());
    //         insertStatement.setString(2, staff.getFirstName());
    //         insertStatement.setString(3, staff.getLastName());
    //         insertStatement.setString(4, staff.getPassword());
    //         insertStatement.setInt(5, staff.getStaffID());
    //         insertStatement.setInt(6, staff.getStaffTypeID());

    //         insertStatement.executeUpdate();
    //     }
    // }



    public ArrayList<User> getAllUsers() throws SQLException{
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(getAllUsersQuery);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                // System.out.println("Fetched user: " + rs.getString("email"));
                
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userType = rs.getString("userType");
                String password = rs.getString("password");

                User user;
                if ("STAFF".equalsIgnoreCase(userType)) {
                    int staffID = rs.getInt("staff_id");
                    int staffTypeID = rs.getInt("staff_type_id");
                    user = new Staff(email, password, firstName, lastName, staffID, staffTypeID);
                } else {
                    String streetAddress = rs.getString("street_address");
                    int postcode = rs.getInt("postcode");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
    
                    int homePhone = rs.getString("home_phone_number") != null ? Integer.parseInt(rs.getString("home_phone_number")) : -1;
                    int mobilePhone = rs.getString("mobile_phone_number") != null ? Integer.parseInt(rs.getString("mobile_phone_number")) : -1;
    
                    Address address = new Address(streetAddress, postcode, city, state);
                    user = new Customer(email, password, firstName, lastName, UserType.CUSTOMER, address, homePhone, mobilePhone);
                }
                users.add(user);
            }
            // System.out.println("Total users fetched: " + users.size());
        }
        return users;
    }

    public ArrayList<User> getCustomers() throws SQLException {
        ArrayList<User> customers = new ArrayList<>();
        
        try (PreparedStatement statement = conn.prepareStatement(getCustomersQuery);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String password = rs.getString("password");
    
                String streetAddress = rs.getString("street_address");
                int postcode = rs.getInt("postcode");
                String city = rs.getString("city");
                String state = rs.getString("state");

                Address address = new Address(streetAddress, postcode, city, state);

                int homePhone = rs.getString("home_phone_number") != null ? Integer.parseInt(rs.getString("home_phone_number")) : -1;
                int mobilePhone = rs.getString("mobile_phone_number") != null ? Integer.parseInt(rs.getString("mobile_phone_number")) : -1;
    
                User customer = new Customer(email, password, firstName, lastName, UserType.CUSTOMER, address, homePhone, mobilePhone);
                customers.add(customer);
            }
        }
        return customers;
    }

    public void updateUser(User user) throws SQLException {
        String updateQuery;
        PreparedStatement updateStatement;
    
        if (user instanceof Staff) {
            updateQuery = updateStaffQuery;
            updateStatement = conn.prepareStatement(updateQuery);
            Staff staff = (Staff) user;
            updateStatement.setString(1, staff.getFirstName());
            updateStatement.setString(2, staff.getLastName());
            updateStatement.setString(3, staff.getPassword());
            updateStatement.setInt(4, staff.getStaffTypeID());
            updateStatement.setString(5, user.getEmail());          
        } else if (user instanceof Customer) {
            updateQuery = updateCustomerQuery;
            updateStatement = conn.prepareStatement(updateQuery);
            Customer customer = (Customer) user;
            updateStatement.setString(1, customer.getFirstName());
            updateStatement.setString(2, customer.getLastName());
            updateStatement.setString(3, customer.getPassword());
            updateStatement.setString(4, customer.getAddress().getStreetAddress());
            updateStatement.setInt(5, customer.getAddress().getPostcode());
            updateStatement.setString(6, customer.getAddress().getCity());
            updateStatement.setString(7, customer.getAddress().getState());
            updateStatement.setInt(8, customer.getHomePhoneNumber());
            updateStatement.setInt(9, customer.getMobilePhoneNumber());
            updateStatement.setString(10, customer.getEmail());
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }

        updateStatement.executeUpdate();
    }


    public void deleteUser(String email, String userType) throws SQLException {
        String deleteQuery;
        PreparedStatement deleteStatement;

        if ("STAFF".equalsIgnoreCase(userType)) {
            deleteQuery = deleteStaffQuery;
            deleteStatement = conn.prepareStatement(deleteQuery);
        } else if ("CUSTOMER".equalsIgnoreCase(userType)) {
            deleteQuery = deleteCustomerQuery;
            deleteStatement = conn.prepareStatement(deleteQuery);
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }

        deleteStatement.setString(1, email);
        deleteStatement.executeUpdate();
    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;
    
        try (PreparedStatement statement = conn.prepareStatement(getUserByEmailQuery)) {
            statement.setString(1, email);
            statement.setString(2, email); // For the union to work correctly, you need to set the email twice
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String userType = rs.getString("userType");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String password = rs.getString("password");
    
                    if ("STAFF".equalsIgnoreCase(userType)) {
                        int staffID = rs.getInt("staff_id");
                        int staffTypeID = rs.getInt("staff_type_id");
                        user = new Staff(email, password, firstName, lastName, staffID, staffTypeID);
                    } else if ("CUSTOMER".equalsIgnoreCase(userType)) {
                        String streetAddress = rs.getString("street_address");
                        int postcode = rs.getInt("postcode");
                        String city = rs.getString("city");
                        String state = rs.getString("state");
                        int homePhone = rs.getString("home_phone_number") != null ? Integer.parseInt(rs.getString("home_phone_number")) : -1;
                        int mobilePhone = rs.getString("mobile_phone_number") != null ? Integer.parseInt(rs.getString("mobile_phone_number")) : -1;
                        Address address = new Address(streetAddress, postcode, city, state);
                        user = new Customer(email, password, firstName, lastName, UserType.CUSTOMER, address, homePhone, mobilePhone);
                    }
                }
            }
        }
        return user;
    }


    public ArrayList<User> searchAllUsers(String fullName) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String searchQuery = "%" + fullName.toLowerCase() + "%";
        try (PreparedStatement statement = conn.prepareStatement(searchUserQuery)) {
            statement.setString(1, searchQuery);
            statement.setString(2, searchQuery);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String email = rs.getString("email");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String userType = rs.getString("userType");
                    String password = rs.getString("password");

                    User user;
                    if ("STAFF".equalsIgnoreCase(userType)) {
                        int staffID = rs.getInt("staff_id");
                        int staffTypeID = rs.getInt("staff_type_id");
                        user = new Staff(email, password, firstName, lastName, staffID, staffTypeID);
                    } else {
                        user = new Customer(email, password, firstName, lastName, null);
                    }
                    users.add(user);
                }
            }
        }
        return users;
    }
    

    public ArrayList<User> searchCustomers(String fullName) throws SQLException {
        ArrayList<User> customers = new ArrayList<>();
        String searchQuery = "%" + fullName.toLowerCase() + "%";
        try (PreparedStatement statement = conn.prepareStatement(searchCustomerQuery)) {
            statement.setString(1, searchQuery);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String email = rs.getString("email");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String password = rs.getString("password");

                    User customer = new Customer(email, password, firstName, lastName, null);
                    customers.add(customer);
                }
            }
        }
        return customers;
    }

}