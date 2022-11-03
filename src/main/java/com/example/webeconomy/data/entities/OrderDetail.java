package com.example.webeconomy.data.entities;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "orderdetail")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;

    @Column(name ="price")
    private float price;

    @Column(name ="quantity")
    private int quantity;

    @JsonBackReference (value="order-detail")
    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Order order;

    public OrderDetail (){
        super();
    }

    public OrderDetail(OrderDetailId id, float price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
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
        return "OrderDetail [ordersDetailid=" + id + ", price=" + price + ", quantity=" + quantity + "]";
    }



    // @ManyToOne(mappedBy = "ordersDetail")
    // private Orders orders;


}
