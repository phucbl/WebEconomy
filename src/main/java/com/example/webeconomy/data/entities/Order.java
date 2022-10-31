package com.example.webeconomy.data.entities;

import java.util.Date;
import java.util.List;

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

// import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column (name = "id")
    private long id;

    @Column (name = "date_created")
    private Date dateCreated;

    @Column (name = "status")
    private OrderStatus status;

    @Column (name = "customer_id")
    private long customerId;

    public Order (){
        super();
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail;

    @ManyToOne
    @JoinColumn(name="customer_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;

    public Order(Date dateCreated, OrderStatus status, long customerId) {
        this.dateCreated = dateCreated;
        this.status = status;
        this.customerId = customerId;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
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
