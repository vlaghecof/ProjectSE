package com.commerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity{

    @Column
    private String username;

    @Column(name = "hash_pwd")
    private String password;

    @Column
    private String email;

    public Customer(){

    }

    public Customer(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
