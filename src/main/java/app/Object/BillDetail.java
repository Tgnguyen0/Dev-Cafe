package app.Object;

import java.io.Serializable;

public class BillDetail implements Serializable{
    MenuItem item;
    int quantity;
    double total_price;
    private final double INC = 5.0;

    public BillDetail(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.total_price = quantity * (item.getPrice() + INC);
    }

    public MenuItem getItem() {
        return this.item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public double getTotal_price() {
        return this.total_price;
    }

    public void setTotal_price(int quantity, double item_price) {
        this.total_price = quantity * (item_price + INC);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
