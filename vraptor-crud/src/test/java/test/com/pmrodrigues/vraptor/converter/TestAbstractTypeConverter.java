package test.com.pmrodrigues.vraptor.converter;

import com.pmrodrigues.persistence.daos.Repository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import test.com.pmrodrigues.vraptor.crud.controller.Loja;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Marceloo on 15/04/2015.
 */
public class TestAbstractTypeConverter {

    private final Mockery context = new Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    private Repository<Loja> repository = context.mock(Repository.class);

    @Test
    public void converter() {

        context.checking(new Expectations() {{
            oneOf(repository).findById(with(aNonNull(Long.class)));
            will(returnValue(new Loja()));
        }});

        MockAbstractTypeConverter converter = new MockAbstractTypeConverter(repository);
        final Loja loja = converter.convert("1", Loja.class, null);

        assertNotNull(loja);
    }

    @Test
    public void naoDeveConverter() {
        MockAbstractTypeConverter converter = new MockAbstractTypeConverter(repository);
        Loja loja = converter.convert("", Loja.class, null);

        assertNull(loja);

        loja = converter.convert(null, Loja.class, null);

        assertNull(loja);
    }
}