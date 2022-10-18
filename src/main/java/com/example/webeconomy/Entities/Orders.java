package com.example.webeconomy.entities;

import java.sql.Date;

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

@Entity
@Table (name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "date_created")
    private Date dateCreated;

    @Column (name = "status")
    private int status;

    @Column (name = "customer_id")
    private long customerId;

    public Orders(Date dateCreated, int status, long customerId) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
