package com.fitch.entities;

// Sub-DTO that holds information about ordered items
// They will be compiled into a BillItem DTO to send to the front-end

public class BillItem {
    private String name;
    private double price;
    private int quantity;

    public BillItem() {
    }

    public BillItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
