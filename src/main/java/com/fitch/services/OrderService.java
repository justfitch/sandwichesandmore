package com.fitch.services;

//import com.fitch.entities.Bill;
import com.fitch.entities.Bill;
import com.fitch.entities.Order;
import com.fitch.entities.RestaurantTable;

import java.util.List;

public interface OrderService {

    int placeOrder(int tableNo, List<Integer> itemIds);

    Bill getBill(int tableNo);

    int payBill(int tableNo);
}
