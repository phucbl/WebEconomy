package com.example.WebEconomy;

import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;;

@Entity
@Table (name ="accout")
public class Accounts {

    
    @Column(name ="phoneNumber")
    private String phoneNumber;

    @Column(name ="passwd")
    private String password;


    @Column(name ="roleId")
    private int roleId;
    
    protected Accounts(){}

    public Accounts(String phoneNumber, String password, int roleId) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleId = roleId;
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
        return "Accounts [phoneNumber=" + phoneNumber + ", password=" + password + ", roleId=" + roleId + "]";
    }
    
}
