package com.example.webeconomy.data.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; 
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String descripstion;

    @Column(name = "origin")
    private String origin;

    @Column(name = "price")
    private float price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "count")
    private int count;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "rate")
    private float rate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    public Product(String name, String descripstion, String origin, float price, String imageUrl, int count,
            int categoryId, float rate, Date createDate, Date updateDate) {
        this.name = name;
        this.descripstion = descripstion;
        this.origin = origin;
        this.price = price;
        this.imageUrl = imageUrl;
        this.count = count;
        this.categoryId = categoryId;
        this.rate = rate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripstion() {
        return descripstion;
    }

    public void setDescripstion(String descripstion) {
        this.descripstion = descripstion;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Products [id=" + id + ", name=" + name + ", descripstion=" + descripstion + ", origin=" + origin
                + ", price=" + price + ", imageUrl=" + imageUrl + ", count=" + count + ", categoryId=" + categoryId
                + ", rate=" + rate + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }

    
}
