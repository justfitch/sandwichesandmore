package com.fitch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "food_order")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="table_no", nullable=false)
    @JsonIgnore
    private RestaurantTable restaurantTable;

    // **********************How Do I do this????
    //List<Integer> orderItems = new ArrayList<>(); //How do I get make this a One-to-Many or Many-to-Many Relationship for the DB???

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<MenuItem> orderItems = new ArrayList<>();

    @Column(name = "order_cost")
    double orderCost;

    @Column(name = "status")
    String status;


    public Order() {
        this.status = "Active";
    }

    public Order(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
        this.status = "Active";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MenuItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }
}
