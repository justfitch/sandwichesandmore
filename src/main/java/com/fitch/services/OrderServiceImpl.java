package com.fitch.services;

import com.fitch.entities.*;
import com.fitch.repositories.MenuItemRepository;
import com.fitch.repositories.OrderRepository;
import com.fitch.repositories.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public int placeOrder(int tableNo, List<Integer> itemIds) {

        //fetch a RestaurantTable object to tie the bill to based on tableNo path variable
        RestaurantTable restaurantTable = restaurantTableRepository.getReferenceById(tableNo);

        //Create an order object
        Order order = new Order(restaurantTable);

        //Add each MenuItem to Order
        //Calculate Cost
        double cost = 0;
        for (int itemId : itemIds) {
            MenuItem orderedItem = menuItemRepository.getReferenceById(itemId);
            cost += orderedItem.getPrice();

            //Add Items to Order
            order.getOrderItems().add(orderedItem);
        }

        //Set cost and save the order, to generate an orderID. Return the orderId
        order.setOrderCost(cost);
        return orderRepository.save(order).getOrderId();
    }

    @Override
    public Bill getBill(int tableNo) {

        // fetch a list of all orders with the matching tableNo and "Active" status
        // This allows multiple orders from a table, which will be compiled onto one bill
        List<Order> activeOrders = orderRepository.findByRestaurantTableAndStatus(restaurantTableRepository.getReferenceById(tableNo), "Active");
        Bill bill = new Bill();

        // Assuming there are unpaid orders for the table...
        if(activeOrders.size() >0) {
            //Build the bill
            //Use the first orderId (if multiple are found) as the primary orderId
            bill.setOrderId(activeOrders.get(0).getOrderId());

            //Set table number
            bill.setTableNo(activeOrders.get(0).getRestaurantTable().getTableNo());

            // map to hold the name and quantity of each MenuItem ordered
            Map<String, Integer> totalItems = new HashMap<>();

            // We're going to do this for each Active Order for the table to construct the Bill
            for (Order order : activeOrders) {
                // ANd we're goign to add each MenuItem from each Order
                for (MenuItem menuItem : order.getOrderItems()) {
                    // If the MenuItem is not in the map yet, add it and set quantity to 1
                    if (!totalItems.containsKey(menuItem.getName())) {
                        totalItems.put(menuItem.getName(), 1);
                    // If it is already on the bill, increment the quantity
                    } else {
                        totalItems.put(menuItem.getName(), totalItems.get(menuItem.getName()) + 1);
                    }
                }
            }

            // Construct a BillItem "sub-DTO" object for each item, including the name, price and quantity
            for (String item : totalItems.keySet()) {
                BillItem billItem = new BillItem(item, menuItemRepository.getByName(item).getPrice(), totalItems.get(item));

                //Add each bill item to the Bill DTO
                bill.getPurchases().add(billItem);
            }

        //In case there are no active orders, though the button should be deactivated on the app then...
        } else {
            bill.setOrderId(0);
        }

        return bill;
    }

    // All this does is sets the Order status to "Paid" so it will not be included in future bills
    @Override
    public boolean payBill(int tableNo) {
        List<Order> activeOrders = orderRepository.findByRestaurantTableAndStatus(restaurantTableRepository.getReferenceById(tableNo), "Active");

        if(activeOrders.size() >0) {
            for (Order order : activeOrders) {
                order.setStatus("Paid");
                orderRepository.save(order);
            }
            return true;
        } else {
            return false; //in case there are no active orders to pay
        }
    }
}
