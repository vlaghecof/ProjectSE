package com.commerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends AbstractEntity{

    @Column
    private String username;

    @Column(name = "hash_pwd")
    private String password;

    @Column
    private String email;

    @Column
    private boolean isAdmin;

    public Employee(){

    }

    public Employee(String username, String password, String email, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }
}
