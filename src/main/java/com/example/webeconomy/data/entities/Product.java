package com.example.webeconomy.data.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id; 
    
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "origin")
    private String origin;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private boolean status;
    
    @Convert(converter = StringListConverter.class)
    @Column(name = "image_url")
    private List<String> imageUrl;

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

    public Product (){
        super();
    }
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id", insertable=false, updatable=false)
    public Category category;

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Rating> rating;

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Cart> cart;

    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<OrderDetail> orderDetail;

    public Product(long id, String name, String description, String origin, float price, List<String> imageUrl, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", origin=" + origin
                + ", price=" + price + ", status=" + status + ", imageUrl=" + imageUrl + ", count=" + count
                + ", categoryId=" + categoryId + ", rate=" + rate + ", createDate=" + createDate + ", updateDate="
                + updateDate + "]";
    } 
}
