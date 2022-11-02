
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

@Entity
@Getter
@Setter
@Table (name ="rating")
public class Rating {

    @EmbeddedId
    private ProductCustomerId id;

    @Column (name="point")
    private int point;

    
    @ManyToOne
    @JsonBackReference (value="rating")
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Product product;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;

    public Rating (){
        super();
    }

    public Rating(ProductCustomerId id, int point) {
        this.id = id;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Cart [productAndCustomerId=" + id + ", point=" + point + "]";
    }

    
}
