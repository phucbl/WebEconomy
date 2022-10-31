package com.example.webeconomy.data.entities;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name ="cart")
public class Cart {

    @EmbeddedId
    private ProductCustomerId id;

    @Column (name="quantity")
    private int quantity;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Product product;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;

    public Cart(){
        super();
    }

    public Cart(ProductCustomerId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart [productAndCustomerId=" + id + ", quantity=" + quantity + "]";
    }

    
}
