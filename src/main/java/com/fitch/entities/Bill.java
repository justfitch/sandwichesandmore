package com.fitch.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DTO to pass the necessary data for construction of a bill to the front-end
// Bill is called up by tableNo
// The bill will include all unpaid orders on that table, allowing for multiple orders per table

public class Bill {
    int orderId;
    int tableNo;

    List<BillItem> purchases = new ArrayList<>();

    public Bill() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public List<BillItem> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<BillItem> purchases) {
        this.purchases = purchases;
    }
}
