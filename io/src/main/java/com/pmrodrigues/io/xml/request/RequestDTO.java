package com.pmrodrigues.io.xml.request;

import java.util.Collection;

/**
 * Created by Marceloo on 21/05/2015.
 */
public interface RequestDTO<E> {

    public abstract Collection<E> list();
}
