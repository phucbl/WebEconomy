package com.example.webeconomy.data.entities;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @JsonBackReference (value="category-show")
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> product;

    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
    
}
