package com.fitch.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @Column(name = "table_no")
    int tableNo;

    @OneToMany(mappedBy = "restaurantTable")
    private Set<Order> orders = new HashSet<>();

    public RestaurantTable() {
    }

    public RestaurantTable(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

}
