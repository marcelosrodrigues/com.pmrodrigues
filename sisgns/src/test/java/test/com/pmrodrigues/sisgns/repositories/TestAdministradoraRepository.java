package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.endereco.repositories.BairroRepository;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import com.pmrodrigues.vraptor.crud.exceptions.UniqueException;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marceloo on 21/10/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestAdministradoraRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AdministradoraRepository repository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private SessionFactory sessionFactory;

    private List<String> cnpjs = new ArrayList<>();

    @Before
    public void inicia() {

        for (int i = 0; i < 3; i++) {
            final String cnpj = GeradorCNPJCPF.cnpj();
            cnpjs.add(cnpj);

            jdbcTemplate.update("insert into endereco ( bairro_id , cidade_id , estado_id , " +
                    "   cep , logradouro , complemento , numero  ) values " +
                    " ( 12258 , 7043 , 19 , '22743310' , 'TESTE' , 'TESTE' , 10  )");

            long enderecoId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);

            jdbcTemplate.update("insert into cedente (nome, numeroDocumento , DTYPE , endereco_id) values " +
                            " ( ? , ? , 'Administradora' , ? )",
                    format("test_%s", i), cnpj, enderecoId);

        }


    }


    @Test
    public void devePesquisarSemPassarParametroNoMetodo() {

        final long esperado = jdbcTemplate.queryForObject("select count(1) from cedente where dtype = 'Administradora'", Long.class);

        ResultList<Administradora> administradoras = repository.search(new Administradora());

        assertEquals(esperado, administradoras.getQuantidadeRegistros());

    }

    @Test
    public void devePesquisarComParametroNulo() {
        final long esperado = jdbcTemplate.queryForObject("select count(1) from cedente where dtype = 'Administradora'", Long.class);

        ResultList<Administradora> administradoras = repository.search(null);

        assertEquals(esperado, administradoras.getQuantidadeRegistros());
    }

    @Test
    public void devePesquisarApenasPeloCNPJ() {

        final String cnpj = this.cnpjs.get(0);
        final long esperado = jdbcTemplate.queryForObject("select count(1) from cedente where numeroDocumento like ? " +
                " and dtype = 'Administradora'", Long.class, cnpj + '%');

        final Administradora administradora = (Administradora) new Administradora().comNumeroDocumento(cnpj);

        ResultList<Administradora> administradoras = repository.search(administradora);

        assertEquals(esperado, administradoras.getQuantidadeRegistros());

    }

    @Test
    public void devePesquisarApenasPeloNome() {
        final long esperado = jdbcTemplate.queryForObject("select count(1) from cedente where nome like 'test%' " +
                " and dtype = 'Administradora'", Long.class);

        final Administradora administradora = (Administradora) new Administradora().comNome("test");

        ResultList<Administradora> administradoras = repository.search(administradora);

        assertEquals(esperado, administradoras.getQuantidadeRegistros());
    }

    @Test
    public void devePesquisarPorNomeECNPJ() {
        final String cnpj = this.cnpjs.get(0);
        final long esperado = jdbcTemplate.queryForObject("select count(1) from cedente where nome like 'test%' or " +
                " numeroDocumento like ? " +
                " and dtype = 'Administradora'", Long.class, cnpj + '%');

        final Administradora administradora = (Administradora) new Administradora()
                .comNumeroDocumento(cnpj)
                .comNome("test");

        ResultList<Administradora> administradoras = repository.search(administradora);

        assertEquals(esperado, administradoras.getQuantidadeRegistros());

    }

    @Test
    public void podeIncluirAdministradora() {

        final String cnpj = GeradorCNPJCPF.cnpj();

        final Administradora administradora = (Administradora) new Administradora()
                .comNumeroDocumento(cnpj)
                .comNome("test");

        administradora.adicionar(new Telefone("021", "33926222"));
        administradora.comEndereco(criaEndereco());


        repository.add(administradora);

        this.sessionFactory.getCurrentSession().flush();

        final long count = jdbcTemplate.queryForObject("select count(1) from cedente where numeroDocumento = ?", Long.class, cnpj);

        assertEquals(1, count);
    }

    public Endereco criaEndereco() {
        Endereco endereco = new Endereco();
        final Bairro bairro = bairroRepository.findById(12258L);
        endereco.setBairro(bairro);
        endereco.setCidade(bairro.getCidade());
        endereco.setEstado(bairro.getCidade().getEstado());
        endereco.setCep("22743-310");
        endereco.setLogradouro("TESTE");
        endereco.setComplemento("TESTE");
        endereco.setNumero("TESTE");
        return endereco;
    }

    @Test(expected = UniqueException.class)
    public void naoPodeIncluirAdministradoraComCNPJRepetido() {
        final String cnpj = this.cnpjs.get(0);

        final Administradora administradora = (Administradora) new Administradora()
                .comNumeroDocumento(cnpj)
                .comNome("test");

        repository.add(administradora);
    }

    @Test(expected = UniqueException.class)
    public void naoPodeAtualizarAdministradoraQuandoJaExisteOutraComOMesmoCNPJ() {

        final String cnpj = this.cnpjs.get(0);

        final Administradora administradora = (Administradora) new Administradora()
                .comNumeroDocumento(cnpj)
                .comEndereco(criaEndereco())
                .comNome("test");

        repository.set(administradora);

    }

    @Test
    public void podeAtualizarAdministradoraQuandoOCNPJInformadoEOMesmoDoRegistroQueSeraAlterado() {

        final String cnpj = this.cnpjs.get(0);

        final Long id = jdbcTemplate.queryForObject("select id from cedente where numeroDocumento = ?", Long.class, cnpj);

        Administradora administradora = (Administradora) new Administradora()
                .comNumeroDocumento(cnpj)
                .comEndereco(criaEndereco())
                .comNome("test");

        administradora.adicionar(new Telefone("021", "33926222"));
        administradora.setId(id);
        repository.set(administradora);

    }

    @Test
    public void podeAtualizarComCNPJQueNaoExisteAindaNoSistema() {

        final String cnpj = GeradorCNPJCPF.cnpj();

        final Long id = jdbcTemplate.queryForObject("select max(id) from cedente", Long.class);

        Administradora administradora = (Administradora) new Administradora()
                .comNumeroDocumento(cnpj)
                .comEndereco(criaEndereco())
                .comNome("test");

        administradora.adicionar(new Telefone("021", "33926222"));
        administradora.setId(id);
        repository.set(administradora);

    }


}