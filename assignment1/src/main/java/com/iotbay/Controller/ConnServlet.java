package com.iotbay.Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iotbay.Dao.DBConnector;
import com.iotbay.Dao.DBManager;
import com.iotbay.Model.Cart;

// A get request is sent to this Servlet using jsp:include for any JSP page that may potentially use the DB.
// The DB connection and DBManager is only created once and stored in the session to be shared across the app
public class ConnServlet extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
    private Connection conn;

    @Override
    public void init() {
        /*
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }      
            */
    }

    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // adding cart to sessions
        // when the person logs out or if the session goes on 30 mins (i think - something like that)
        // the session is cleared

        response.setContentType("text/html;charset=UTF-8");       
        HttpSession session = request.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        
        /*
        response.setContentType("text/html;charset=UTF-8");       
        HttpSession session = request.getSession();

        Cart cart = new Cart();
        session.setAttribute("cart", cart);

        /*
        if (session.getAttribute("manager") != null) {
            return;
        }
        
        conn = db.openConnection();       
        
        try {
            manager = new DBManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("manager", manager);
        */
    }   

    @Override
     public void destroy() {
        /* 
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            */
    }
}
