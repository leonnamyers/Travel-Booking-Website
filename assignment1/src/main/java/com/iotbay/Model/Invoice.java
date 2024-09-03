package com.iotbay.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable{
    private int invoiceID;
    private String orderID;
    private LocalDate invoice;

    public Invoice() {
    }

    public int getInvoiceID() {
        return invoiceID;
    }
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public LocalDate getInvoice() {
        return invoice;
    }
    public void setInvoice(LocalDate invoice) {
        this.invoice = invoice;
    }
}
