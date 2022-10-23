
package com.example.webeconomy.data.entities;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name ="rating")
public class Rating {

    @EmbeddedId
    private ProductCustomerId productAndCustomerId;

    @Column (name="point")
    private int point;

    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Product product;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;

    public Rating(ProductCustomerId productAndCustomerId, int point) {
        this.productAndCustomerId = productAndCustomerId;
        this.point = point;
    }

    public ProductCustomerId getProductAndCustomerId() {
        return productAndCustomerId;
    }

    public void setProductAndCustomerId(ProductCustomerId productAndCustomerId) {
        this.productAndCustomerId = productAndCustomerId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Cart [productAndCustomerId=" + productAndCustomerId + ", point=" + point + "]";
    }

    
}
