package com.fitch.controllers;

//import com.fitch.entities.Bill;
import com.fitch.entities.Bill;
import com.fitch.entities.Order;
import com.fitch.services.OrderService;
import com.fitch.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService = new OrderServiceImpl();

    // POST endpoint to enter order into the DB
    //Return the OrderNumber
    // e.g. http://localhost:8080/order/2?itemIds=1,1,4,6,7,7
    @PostMapping(value = "/order/{tableNo}", params = "itemIds")
    int placeOrder(@PathVariable int tableNo, @RequestParam List<Integer> itemIds) {
        return orderService.placeOrder(tableNo, itemIds);
    }

    // GET endpoint to fetch data to construct bill on front-end
    //Returns a DTO containing the Order Number, Table Number, and a list of BillItems (item name, price and quantity)
    // e.g. http://localhost:8080/bill/2
    @GetMapping("/bill/{tableNo}")
    Bill getBill(@PathVariable int tableNo){
        return orderService.getBill(tableNo);
    }

    // PATCH endpoint to "pay the bill". All it does is updates the "status" field of the order to "Paid"
    // Once the status is paid, it will no longer be retrieved when fetching the bill for a table
    @PatchMapping("/bill/{tableNo}/pay")
    boolean payBill(@PathVariable int tableNo){
        return orderService.payBill(tableNo);
    }


}
