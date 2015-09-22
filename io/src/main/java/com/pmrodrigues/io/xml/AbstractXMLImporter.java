package com.pmrodrigues.io.xml;


import com.pmrodrigues.io.Importer;
import com.pmrodrigues.io.xml.request.RequestDTO;
import com.pmrodrigues.utilities.xml.Deserializer;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Marceloo on 21/05/2015.
 */
public abstract class AbstractXMLImporter<E, T extends RequestDTO<E>> implements Importer<E> {

    private final Class<E> toClass; //NOPMD

    private final Class<T> fromClass; //NOPMD

    public AbstractXMLImporter() {
        final ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.toClass = (Class<E>) type.getActualTypeArguments()[0];
        this.fromClass = (Class<T>) type.getActualTypeArguments()[1];
    }

    @Override
    public Collection<E> toList(final InputStream source) {

        final T fromClass = (T) Deserializer.unmarshall(this.fromClass, source);
        final Collection<E> collection = new ArrayList<>();
        collection.addAll(fromClass.list());

        return collection;
    }
}
