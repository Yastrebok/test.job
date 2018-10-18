package com.test.job.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class could applied for all class of repository
 * Current task can exist without it
 */

public abstract class AbstractRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSesion(){
        return sessionFactory.getCurrentSession();
    }
}
