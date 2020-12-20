package com.commerce.repository;

import com.commerce.model.Product;
import com.commerce.service.HibernateService;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ProductRepository implements Repository<Product> {

    public Product save(Product entity) {
        Session databaseSession = HibernateService.getSessionFactory().openSession();
        databaseSession.beginTransaction();
        Query query = databaseSession.createQuery("FROM Product X WHERE X.name = :name");
        query.setParameter("name", entity.getName());
        List<Product> result = query.list();
        if(result.size() == 0) {
            databaseSession.saveOrUpdate(entity);
            databaseSession.save(entity);
            databaseSession.flush();
            databaseSession.getTransaction().commit();
        }
        else {

            return null;
        }
        return entity;
    }


    public Product findById(Long id) {
        Session databaseSession = HibernateService.getSessionFactory().openSession();
        Query query = databaseSession.createQuery("FROM Product X WHERE X.id = :id");
        query.setParameter("id", id);
        List<Product> result = query.list();
        return result.get(0);
    }


    public List<Product> findAll() {
        Session databaseSession = HibernateService.getSessionFactory().openSession();
        Query query = databaseSession.createQuery("from Product");
        List<Product> result = query.list();
        return result;
    }


    public boolean delete(Product entity) {
        Session databaseSession = HibernateService.getSessionFactory().openSession();
        databaseSession.beginTransaction();
        if(findById(entity.getId()) != null) {
            Query query = databaseSession.createQuery("DELETE FROM Product X WHERE X.id = :id");
            query.setParameter("id", entity.getId());
            query.executeUpdate();
            databaseSession.flush();
            databaseSession.getTransaction().commit();
            return true;
        }
        else {
            return false;
        }
    }
}
