package test.com.pmrodrigues.sisgns.acceptables.rest;

import com.pmrodrigues.endereco.models.Estado;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 13/10/2015.
 */
public class ITestEstadoController {


    @Test
    public void deveBuscarEstadoPeloNome() {

        Estado estado = given()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .get("/endereco/estado/RIO.json")
                .andReturn()
                .jsonPath()
                .getObject("list[0]", Estado.class);

        assertNotNull(estado);
        Estado esperado = new Estado("RJ", "Rio de Janeiro");

        assertEquals(esperado, estado);

    }

    @Test
    public void deveRetornarStatusCodeInformandoQueListaEstaVaziaMasNaoPodeDarErro() {
        given()
                .expect()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .get("/endereco/estado/abc.json")
                .statusCode();

    }
}
