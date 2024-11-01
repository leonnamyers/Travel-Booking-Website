package com.iotbay.Model;

public class Package extends Item {
    private String description;
    private String introduction;
    private String activities;
    private String transportation;
    private String dining;
    private String specialOffer;
    private String contactName;
    private String contactPhone;


    public Package() {
        super();  
        this.description = null;
        this.introduction = null;
        this.activities = null;
        this.transportation = null;
        this.dining = null;
        this.specialOffer = null;
        this.contactName = null;
        this.contactPhone = null;
    }

 
    public Package(int itemID, String name, double price, int availability, String img, String description) {
        super(itemID, name, price, availability, img);  
        this.description = description;
        this.introduction = null;
        this.activities = null;
        this.transportation = null;
        this.dining = null;
        this.specialOffer = null;
        this.contactName = null;
        this.contactPhone = null;
    }


    public Package(int itemID, String name, double price, int availability, String img, String description, String introduction, String activities, String transportation, String dining, String specialOffer, String contactName, String contactPhone) {
        super(itemID, name, price, availability, img);
        this.description = description;
        this.introduction = introduction;
        this.activities = activities;
        this.transportation = transportation;
        this.dining = dining;
        this.specialOffer = specialOffer;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    // Getters and Setters...

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public String getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(String specialOffer) {
        this.specialOffer = specialOffer;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
