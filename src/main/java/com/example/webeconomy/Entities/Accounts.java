package com.example.webeconomy.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table (name ="account")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;  
    
    @Column(name ="phone_number")
    private String phoneNumber;

    @Column(name ="password")
    private String password;

    @Column(name ="role_id")
    private int roleId;

    @OneToOne(mappedBy = "accounts")
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable=false, updatable=false)
    private Roles roles;

    public Accounts(String phoneNumber, String password, int roleId) {        
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleId = roleId;
    }

    protected Accounts(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
    
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Accounts [id=" + id + ", phoneNumber=" + phoneNumber + ", password=" + password + ", roleid=" + roleId
                + "]";
    }
}
