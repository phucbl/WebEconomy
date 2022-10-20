package com.example.webeconomy.data.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;;

@Entity
@Table (name ="customer")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="account_id")
    private Long accountId;

    @Column(name ="name")
    private String name; 

    @Column(name ="address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Account account;

    
    // @OneToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", referencedColumnName = "customer_id", insertable=false, updatable=false)
    // private Rating rating;



    public Customer(){
        super();
    }

    public Customer(long accountId, String name, String address) {
        this.accountId = accountId;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

       

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customers [id=" + id + ", accountid=" + accountId + ", name=" + name + ", address=" + address + "]";
    }

   
}
