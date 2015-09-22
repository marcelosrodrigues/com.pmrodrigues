package com.pmrodrigues.persistence.daos.impl;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.utilities.strings.ReflectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import static java.lang.String.format;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class AbstractRepository<E> implements Repository<E> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SessionFactory sessionFactory; //NOPMD

    private final Class<E> persistentClass; //NOPMD

    private static final Logger LOGGER = Logger
            .getLogger(AbstractRepository.class);

    @SuppressWarnings("unchecked")
    public AbstractRepository() {
        final ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.persistentClass = (Class<E>) type.getActualTypeArguments()[0];
    }

    @Override
    public void add(final E e) {

        LOGGER.debug(format("Tentando inserir %s novo valor no banco de dados",
                e));
        this.getSession().save(e);
        LOGGER.debug(format(" %s salvo com sucesso", e));

    }

    @Override
    public void set(final E entity) {
        LOGGER.debug(format("Atualizando o valor %s no banco de dados", entity));
        this.getSession().update(entity);
        LOGGER.debug(format("%s salvo com sucesso", entity));

    }

    @Override
    public void remove(final E entity) {
        LOGGER.debug(format("Removendo o valor %s do banco de dados", entity));
        this.getSession().delete(entity);
        LOGGER.debug(format("%s removido do banco de dados", entity));
    }

    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public E findById(final Serializable id) { //NOPMD
        LOGGER.debug(format(
                "Recuperando o valor de %s do banco de dados pela chave %s",
                persistentClass.getName(), id));
        final E entity = (E) this.getSession().get(persistentClass, id);
        LOGGER.debug(format("Valor encontrado %s", entity));
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultList<E> all() {
        LOGGER.debug(format(
                "Listando todos os valores de %s do banco de dados",
                persistentClass.getCanonicalName()));

        return new ResultList<E>(this.getSession()
                .createCriteria(persistentClass)
                .addOrder(Order.asc("id"))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY))
                .todos();
    }

    @Override
    public ResultList<E> search(final E e) {

        final Criteria criteria = this.getSession().createCriteria(persistentClass)
                .add(Example.create(e));

        return new ResultList<>(criteria);

    }

    protected void addSample(final String criteriaName, final Object entity, final Criteria criteria) {
        if (entity != null) {
            ReflectionUtils.nullifyStrings(entity);
            criteria.createCriteria(criteriaName)
                    .add(Example.create(entity)
                            .excludeZeroes()
                            .enableLike(MatchMode.START));
        }
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public Class getPersistenceClass() {
        return this.persistentClass;
    }
}
