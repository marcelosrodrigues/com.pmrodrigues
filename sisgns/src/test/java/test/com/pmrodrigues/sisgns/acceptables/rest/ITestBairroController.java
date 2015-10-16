package test.com.pmrodrigues.sisgns.acceptables.rest;

import com.pmrodrigues.endereco.models.Bairro;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 15/10/2015.
 */
public class ITestBairroController {

    @Test
    public void devePesquisarBairros() {
        final Bairro bairro = given()
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .get("/endereco/bairro/7043/Pechincha.json")
                .andReturn()
                .jsonPath().getObject("list[0]", Bairro.class);
        assertNotNull(bairro);

    }

    @Test
    public void naoDeveRetornarBairros() {
        given()
                .expect()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .get("/endereco/bairro/7043/XXX.json");
    }

    @Test
    public void naoPodePesquisarBairrosSemInformarUmCidade() {
        given()
                .expect()
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
                .get("/endereco/bairro/9999999/XXX.json");
    }
}
