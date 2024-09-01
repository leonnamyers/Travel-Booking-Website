package com.iotbay.Model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.io.Serializable;

public class Catalogue implements Serializable{
    private double price;
    private Time startTime;
    private Date startDate;
    private String name;
    private String serviceType;
    private int availability;
    private String img;

}
