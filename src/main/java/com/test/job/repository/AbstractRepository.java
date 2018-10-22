package com.test.job.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * This class could applied for all class of repository
 *
 */

public abstract class AbstractRepository<PK extends Serializable, T> {

        private final Class<T> persistentClass;

        @SuppressWarnings("unchecked")
        public AbstractRepository(){
            this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }

        @Autowired
        private SessionFactory sessionFactory;

        protected Session getSession(){
            return sessionFactory.getCurrentSession();
        }

        @SuppressWarnings("unchecked")
        public T getById(PK id) {
            return (T) getSession().get(persistentClass, id);
        }

        public void persist(T entity) {
            getSession().persist(entity);
        }

        public void delete(T entity) {
            getSession().delete(entity);
        }

        protected Criteria createEntityCriteria(){
            return getSession().createCriteria(persistentClass);
        }
}
