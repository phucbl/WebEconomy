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
    private OrderDetailId orderDetailId;

    @Column(name ="price")
    private float price;

    @Column(name ="quantity")
    private int quantity;

    public OrderDetail (){
        super();
    }

    public OrderDetail(OrderDetailId orderDetailId, float price, int quantity) {
        this.orderDetailId = orderDetailId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailId getOrdersDetailid() {
        return orderDetailId;
    }

    public void setOrdersDetailid(OrderDetailId ordersDetailid) {
        this.orderDetailId = ordersDetailid;
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
        return "OrderDetail [ordersDetailid=" + orderDetailId + ", price=" + price + ", quantity=" + quantity + "]";
    }



    // @ManyToOne(mappedBy = "ordersDetail")
    // private Orders orders;


}
