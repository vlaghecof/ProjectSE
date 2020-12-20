package com.commerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "complaints")
public class Complaint extends AbstractEntity{

    @Column
    private String type;

    @Column
    private String description;

    @Column
    private String status;

    @Column
    private int id_customer;

    public Complaint(){

    }

    public Complaint(String type, String description, String status, int id_customer){
        this.type = type;
        this.description = description;
        this.status = status;
        this.id_customer = id_customer;
    }

    public String getStatus() {
        return status;
    }

    public int getId_customer() {
        return id_customer;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
