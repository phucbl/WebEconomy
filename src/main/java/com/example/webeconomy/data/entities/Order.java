package com.example.webeconomy.data.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.example.webeconomy.constants.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

// import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column (name = "id")
    private long id;

    @Column (name = "date_created")
    private String dateCreated;

    @Column (name = "status")
    private OrderStatus status;

    @Column (name = "customer_id")
    private long customerId;

    public Order (){
        super();
    }
    
    @JsonBackReference
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    @JsonBackReference (value="Order-show")
    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;

    public Order(String dateCreated, OrderStatus status, long customerId) {
        this.dateCreated = dateCreated;
        this.status = status;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", dateCreated=" + dateCreated + ", status=" + status + ", customerId=" + customerId
                + "]";
    }

    // @OneToMany (fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", referencedColumnName = "order_id", insertable=false, updatable=false)
    // private OrdersDetail ordersDetail;

    // @ManyToOne (fetch = FetchType.LAZY)
    // @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    // private Customers customers;
}
