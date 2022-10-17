package com.example.webeconomy.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;

import com.example.webeconomy.Entities.*;;

@Entity
@Table (name ="account")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;  
    
    @Column(name ="phonenumber")
    private String phonenumber;

    @Column(name ="passwd")
    private String password;


    @Column(name ="roleid")
    private int roleid;
    
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id", referencedColumnName = "accountid")
    private Customers customers;

    public Accounts(String phonenumber, String password, int roleid) {        
        this.phonenumber = phonenumber;
        this.password = password;
        this.roleid = roleid;
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
        return roleid;
    }
    public void setRoleId(int roleid) {
        this.roleid = roleid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Accounts [id=" + id + ", phoneNumber=" + phonenumber + ", password=" + password + ", roleid=" + roleid
                + "]";
    }
}
