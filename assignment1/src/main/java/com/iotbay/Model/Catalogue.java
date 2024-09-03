package com.iotbay.Model;

import java.util.ArrayList;

public class Catalogue {

    private ArrayList<Item> itemList;

    public Catalogue(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }


}
