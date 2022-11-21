package com.fitch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menu_item")
public class MenuItem {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    int itemId;

    @Column(name="name")
    String name;

    @Column(name="description")
    String description;

    @Column(name="imgurl")
    String imgUrl;

    @Column(name="price")
    double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderItems")
    private List<Order> inOrders = new ArrayList<>();

    public MenuItem() {
    }

    public MenuItem(int itemId, String name, String description, String imgUrl, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getInOrders() {
        return inOrders;
    }

    public void setInOrders(List<Order> inOrders) {
        this.inOrders = inOrders;
    }

}
