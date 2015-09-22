package com.pmrodrigues.io;

import java.io.InputStream;
import java.util.Collection;

/**
 * Created by Marceloo on 05/05/2015.
 */
public interface Importer<E> {

    Collection<E> toList(final InputStream source);
}
