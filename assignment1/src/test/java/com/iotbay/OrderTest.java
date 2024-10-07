// package com.iotbay;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// import java.sql.Connection;
// import java.sql.SQLException;
// import java.sql.Timestamp;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.iotbay.Dao.DBConnector;
// import com.iotbay.Dao.OrderDAO;
// import com.iotbay.Model.Order;

// @ExtendWith(MockitoExtension.class)
// class OrderDAOTest{

//     @Mock
//     private DBConnector dbConnector;  

//     @Mock
//     private Connection connection;     

//     @InjectMocks
//     private OrderDAO orderDAO;        // Inject mocks into OrderDAO

//     private Order dummyOrder;

//     @BeforeEach
//     public void setUp() throws Exception {
//         // Mock the behavior of dbConnector to return the mocked connection
//         when(dbConnector.openConnection()).thenReturn(connection);
//         createTestDummies();  // Create test dummies
//     }

//     private void createTestDummies() {
//         // Create a dummy order
//         dummyOrder = new Order();
//         dummyOrder.setTotalPrice(200.0);
//         dummyOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
//         // Set other necessary fields for dummyOrder if needed
//     }

//     // Create
//     @Test
//     public void testCreateOrder_Success() throws SQLException {
//         // Mock the creation of the order
//         doNothing().when(orderDAO).createOrder(any(Order.class), anyString(), anyDouble(),
//                 any(Timestamp.class), anyString(), any(), any(), any());

//         // Call createOrder method
//         orderDAO.createOrder(dummyOrder, "testCustomerID", dummyOrder.getTotalPrice(),
//                 dummyOrder.getOrderDate(), "Destination", null, null, "SeatType");

//         // Verify that createOrder was called with expected parameters
//         verify(orderDAO, times(1)).createOrder(eq(dummyOrder), eq("testCustomerID"), eq(200.0),
//                 eq(dummyOrder.getOrderDate()), eq("Destination"), any(), any(), eq("SeatType"));
//     }

//     @Test
//     public void testCreateOrder_DBError() {
//         try {
//             // Simulating a SQLException being thrown
//             doThrow(new SQLException("Database error")).when(orderDAO).createOrder(any(Order.class), anyString(),
//                     anyDouble(), any(Timestamp.class), anyString(), any(), any(), any());

//             // Attempt to create an order, which should throw an exception
//             orderDAO.createOrder(dummyOrder, "testCustomerID", dummyOrder.getTotalPrice(),
//                     dummyOrder.getOrderDate(), "Destination", null, null, "SeatType");
//             fail("Expected SQLException to be thrown");
//         } catch (SQLException e) {
//             // Assert that the exception message is as expected
//             assertEquals("Database error", e.getMessage());
//         }
//     }
// }
