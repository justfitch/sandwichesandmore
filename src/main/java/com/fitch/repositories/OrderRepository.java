package com.fitch.repositories;

import com.fitch.entities.Order;
import com.fitch.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    //public Order findOrder(int order_id);

    //public List<Order> findAllByRestaurantTable(RestaurantTable restaurantTable);

    public List<Order> findByRestaurantTableAndStatus(RestaurantTable restaurantTable, String status);

}
