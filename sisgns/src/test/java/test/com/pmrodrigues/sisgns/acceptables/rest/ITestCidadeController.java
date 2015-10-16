package test.com.pmrodrigues.sisgns.acceptables.rest;

import com.pmrodrigues.endereco.models.Cidade;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 13/10/2015.
 */
public class ITestCidadeController {

    @Test
    public void devePesquisarCidadePeloEstadoENome() {
        final Cidade cidade = given()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .get("/endereco/cidade/19/Rio.json")
                .andReturn()
                .jsonPath()
                .getObject("list[0]", Cidade.class);

        assertNotNull(cidade);
        Cidade esperado = new Cidade(7039L, "Rio Bonito");
        assertEquals(esperado, cidade);

    }

    @Test
    public void naoPodeDarErroCasoNaoEncontreUmaCidade() {
        given()
                .expect()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .get("/endereco/cidade/19/XX.json");

    }

    @Test
    public void naoPodePesquisarCasoOEstadoSejaInvalido() {
        given().expect()
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
                .get("/endereco/cidade/00/XX.json")
                .andReturn()
                .statusCode();
    }
}
