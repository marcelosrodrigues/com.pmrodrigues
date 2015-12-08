package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Operadora;
import com.pmrodrigues.sisgns.repositories.OperadoraRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 23/11/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestOperadoraRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OperadoraRepository repository;
    private long administradora;

    @Before
    public void setup() {


        jdbcTemplate.update("insert into sisgns.endereco (complemento, numero, logradouro, cep, cidade_id, bairro_id, estado_id) values (?,?,?,?,?,?,?)",
                "APTO 206", 84, "Estrada Campo da Areia", "22743-310", 7043, 12258, 19);

        long endereco = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);

        jdbcTemplate.update("insert into cedente(DTYPE , convenio, nome, numeroDocumento , endereco_id) values ( 'Administradora' , 0 , 'TESTE' , ? , ?)",
                GeradorCNPJCPF.cnpj(), endereco);
        this.administradora = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);

        jdbcTemplate.update("insert into operadora (nome, modalidade_id, codigo, qualicorp, usaPrimeiraParcela, " +
                "excluido, quantidadeParcelas, administradora_id) values ( 'TESTE' , 1 , '0001' , false , false , 0 , 0 , ?)", this.administradora);

    }


    @Test
    public void devePesquisarPeloNome() throws Exception {

        final Operadora operadora = new Operadora();
        operadora.comNome("TESTE");

        final long quantidadeEsperada = jdbcTemplate.queryForObject("select count(1) from operadora where excluido = 0 and nome like 'TESTE%'", Long.class);
        ResultList<Operadora> operadoras = repository.search(operadora);

        assertEquals(quantidadeEsperada, operadoras.getQuantidadeRegistros());

    }

}