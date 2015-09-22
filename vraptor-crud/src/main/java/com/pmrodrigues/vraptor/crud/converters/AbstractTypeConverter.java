package com.pmrodrigues.vraptor.crud.converters;

import br.com.caelum.vraptor.Converter;
import com.pmrodrigues.persistence.daos.Repository;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

import static java.lang.String.format;
import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

public abstract class AbstractTypeConverter<E> implements Converter<E> {

    private final Repository<E> repository;

    private static final Logger logging = Logger
            .getLogger(AbstractTypeConverter.class);

    public AbstractTypeConverter(final Repository<E> repository) {
        this.repository = repository;
    }

    @Override
    public E convert(final String value, final Class<? extends E> type,
                     final ResourceBundle bundle) {
        logging.debug(format("Convertenado para o tipo %s", type));

        E entity = null;

        if (!isBlankOrNull(value)) {
            entity = repository.findById(Long.parseLong(value));
            logging.debug(format("Objeto convertido %s", entity));
        }

        return entity;
    }

}
