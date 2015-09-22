package test.com.pmrodrigues.xml.adapters;

import com.pmrodrigues.xml.adapters.BigDecimalTypeAdapter;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 16/04/2015.
 */
public class TestBigDecimalTypeAdapter {

    @Test
    public void testUnmarshal() throws Exception {
        BigDecimalTypeAdapter adapter = new BigDecimalTypeAdapter();
        BigDecimal value = adapter.unmarshal("10,00");

        assertEquals(BigDecimal.TEN, value);

    }

    @Test
    public void testValorComSeparacaoDeMilhar() throws Exception {
        BigDecimalTypeAdapter adapter = new BigDecimalTypeAdapter();
        BigDecimal value = adapter.unmarshal("1.327,98");

        assertEquals(new BigDecimal(1327.98).round(MathContext.DECIMAL32), value);
    }

    @Test
    public void testMarshal() throws Exception {
        BigDecimalTypeAdapter adapter = new BigDecimalTypeAdapter();
        String value = adapter.marshal(BigDecimal.TEN);

        assertEquals("10,00", value);
    }
}