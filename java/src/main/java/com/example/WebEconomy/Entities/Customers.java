package com.example.WebEconomy.Entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;;

@Entity
@Table (name ="customer")
public class Customers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="accountid")
    private int accountid;

    @Column(name ="name")
    private String name; 

    @Column(name ="address")
    private String address;

    @OneToOne (mappedBy = "customers")
    private Accounts accounts;

    public Customers(){
        super();
    }

    public Customers(int accountid, String name, String address) {
        this.accountid = accountid;
        this.name = name;
        this.address = address;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
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
        return "Customers [id=" + id + ", accountid=" + accountid + ", name=" + name + ", address=" + address + "]";
    }

   
}