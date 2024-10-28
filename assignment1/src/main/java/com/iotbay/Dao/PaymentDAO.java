package com.iotbay.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iotbay.Model.Payment;

public class PaymentDAO {

    private String createQuery = "INSERT INTO Payments (payment_id, email, card_number, expiration_date, cvv, Cardholder_Name) VALUES (?, ?, ?, ?, ?, ?)";
    private String readQuery = "SELECT payment_id, email, card_number, expiration_date, cvv, Cardholder_Name FROM Payments";
    private String deleteQuery = "DELETE FROM Payments WHERE payment_id = ?";
    private String getPaymentQuery = "SELECT payment_id, email, card_number, expiration_date, cvv, Cardholder_Name FROM Payments WHERE payment_id = ?";
    private String updateQuery = "UPDATE Payments SET email = ?, card_number = ?, expiration_date = ?, cvv = ?, Cardholder_Name = ? WHERE payment_id = ?";

    private Connection connection;

    // Constructor
    public PaymentDAO(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(true);
    }

    // Create Operation
    public void createPayment(Payment payment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.setInt(1, payment.getPaymentID());
        statement.setString(2, payment.getEmail());
        statement.setString(3, payment.getCardNumber());
        statement.setDate(4, payment.getExpirationDate());
        statement.setString(5, payment.getCvv());
        statement.setString(6, payment.getCardHolderName());

        statement.executeUpdate();
        statement.close();
    }

    // Read Operation - Fetch All Payments
    public ArrayList<Payment> fetchAllPayments() throws SQLException {
        ArrayList<Payment> payments = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(readQuery);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int paymentID = rs.getInt("payment_id");
            String email = rs.getString("email");
            String cardNumber = rs.getString("card_number");
            Date expirationDate = rs.getDate("expiration_date");
            String cvv = rs.getString("cvv");
            String cardHolderName = rs.getString("Cardholder_Name");

            Payment payment = new Payment(paymentID, email, cardNumber, expirationDate, cvv, cardHolderName);
            payments.add(payment);
        }

        rs.close();
        statement.close();
        return payments;
    }

    // Update Operation
    public void updatePayment(Payment payment) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, payment.getEmail());
        statement.setString(2, payment.getCardNumber());
        statement.setDate(3, payment.getExpirationDate());
        statement.setString(4, payment.getCvv());
        statement.setString(5, payment.getCardHolderName());
        statement.setInt(6, payment.getPaymentID());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            System.out.println("Update failed - Payment not found");
        } else {
            System.out.println("Payment successfully updated");
        }

        statement.close();
    }

    // Delete Operation
    public void deletePayment(int paymentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(deleteQuery);
        statement.setInt(1, paymentID);
        statement.executeUpdate();
        statement.close();
    }

    // Get single Payment by ID
    public Payment fetchPayment(int paymentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(getPaymentQuery);
        statement.setInt(1, paymentID);

        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String email = rs.getString("email");
            String cardNumber = rs.getString("card_number");
            Date expirationDate = rs.getDate("expiration_date");
            String cvv = rs.getString("cvv");
            String cardHolderName = rs.getString("Cardholder_Name");

            Payment payment = new Payment(paymentID, email, cardNumber, expirationDate, cvv, cardHolderName);
            rs.close();
            statement.close();
            return payment;
        }

        rs.close();
        statement.close();
        return null;
    }
}
