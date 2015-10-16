package test.com.pmrodrigues.sisgns.acceptables.rest;

import com.pmrodrigues.endereco.models.Logradouro;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 13/10/2015.
 */
public class ITestLogradouroController {

    @Test
    public void devePesquisarEnderecoPeloCEP() {
        Logradouro logradouro = get("/endereco/logradouro/22743310.json")
                .andReturn()
                .jsonPath()
                .getObject("logradouro", Logradouro.class);

        assertNotNull(logradouro);
        Logradouro esperado = new Logradouro("Estrada Campo da Areia - lado par", "22743310");
        assertEquals(esperado, logradouro);
    }

    @Test
    public void naoPodeRetornarEnderecoCasoOCEPNaoExista() {
        int statusCode = get("/endereco/logradouro/22222222.json")
                .andReturn()
                .statusCode();

        assertEquals(HttpStatus.SC_NO_CONTENT, statusCode);
    }
}
