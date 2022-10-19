package com.example.webeconomy.data.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "orderdetail")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailid ordersDetailid;

    @Column(name ="price")
    private float price;

    @Column(name ="quantity")
    private int quantity;

    public OrderDetail(OrderDetailid ordersDetailid, float price, int quantity) {
        this.ordersDetailid = ordersDetailid;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailid getOrdersDetailid() {
        return ordersDetailid;
    }

    public void setOrdersDetailid(OrderDetailid ordersDetailid) {
        this.ordersDetailid = ordersDetailid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail [ordersDetailid=" + ordersDetailid + ", price=" + price + ", quantity=" + quantity + "]";
    }



    // @ManyToOne(mappedBy = "ordersDetail")
    // private Orders orders;


}
