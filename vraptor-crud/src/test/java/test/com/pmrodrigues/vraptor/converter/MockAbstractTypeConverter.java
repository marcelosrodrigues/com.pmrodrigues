package test.com.pmrodrigues.vraptor.converter;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.vraptor.crud.converters.AbstractTypeConverter;
import test.com.pmrodrigues.vraptor.crud.controller.Loja;

/**
 * Created by Marceloo on 15/04/2015.
 */
public class MockAbstractTypeConverter extends AbstractTypeConverter<Loja> {
    public MockAbstractTypeConverter(Repository<Loja> repository) {
        super(repository);
    }
}
