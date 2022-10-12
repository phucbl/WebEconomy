package com.example.WebEconomy;
import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;;

@Entity
@Table (name ="customer")
public class Customers {
    
    @Id
    @Column(name ="phoneNumber")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name ="passwd")
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name ="roleId")
    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name ="address")
    private String address;

    public Customers(String phoneNumber, String password, int roleId, String address) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleId = roleId;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customers [phoneNumber=" + phoneNumber + ", password=" + password + ", roleId=" + roleId + ", address="
                + address + "]";
    }

    
}
