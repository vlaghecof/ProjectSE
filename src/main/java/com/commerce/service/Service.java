package com.commerce.service;

import java.util.List;

public interface Service<T> {

    T save(T entity);

    T findById(Long id);

    List<T> findAll();

    boolean delete(T entity);

}