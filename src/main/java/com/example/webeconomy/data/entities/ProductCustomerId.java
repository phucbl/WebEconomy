package com.example.webeconomy.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ProductCustomerId implements Serializable{
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="product_id")
    private Long productId;

    public ProductCustomerId(){
        super();
    }

    public ProductCustomerId(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    

}
