package com.pmrodrigues.persistence.daos;

import java.io.Serializable;

public interface Repository<E> extends Serializable {

    void add(E e);

    void set(E e);

    void remove(E e);

    E findById(Serializable e);

    ResultList<E> all();

    ResultList<E> search(E e);

}
