package test.com.pmrodrigues.persistence.validation.constraintvalidations;

import com.pmrodrigues.persistence.validation.constraintvalidations.NotTransientValidation;
import org.junit.Test;

import javax.persistence.Id;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 29/01/2016.
 */
public class TestNotTransientValidation {

    @Test
    public void testIsValid() throws Exception {

        class Test {

            @Id
            private Long teste;

            Test() {

            }

            Test(Long id) {
                this();
                this.teste = id;
            }

            public void setId(long id) {
                this.teste = id;
            }

            public long getId() {
                return teste;
            }
        }

        Test test = new Test();
        NotTransientValidation validation = new NotTransientValidation();
        assertFalse(validation.isValid(test, null));

        test.setId(0L);
        assertFalse(validation.isValid(test, null));

        test.setId(1L);
        assertTrue(validation.isValid(test, null));

    }
}