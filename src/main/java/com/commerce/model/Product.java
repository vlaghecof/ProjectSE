package com.commerce.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends AbstractEntity{

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private int id_category;

    @Column
    private String description;

    @Column
    private String picture;

    public Product() {

    }

    public Product(String name, int price, int id_category, String description, String picture) {
        this.name = name;
        this.price = price;
        this.id_category = id_category;
        this.description = description;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public int getId_category() {
        return id_category;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

}