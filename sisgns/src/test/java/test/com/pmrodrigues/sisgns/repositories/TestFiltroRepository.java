package test.com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.endereco.models.Logradouro;
import com.pmrodrigues.persistence.daos.ResultList;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.models.security.Filtro;
import com.pmrodrigues.sisgns.models.security.Perfil;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.FiltroRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import test.com.pmrodrigues.sisgns.builders.AdministradoraBuilder;
import test.com.pmrodrigues.sisgns.builders.FiltroBuilder;
import test.com.pmrodrigues.sisgns.builders.PerfilBuilder;
import test.com.pmrodrigues.sisgns.builders.UsuarioBuilder;

import static org.hibernate.criterion.Restrictions.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Marceloo on 11/12/2015.
 */
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class TestFiltroRepository extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    private Perfil financeiro;
    private Perfil vendedor;
    private Perfil administrador;
    private Usuario usuario;

    @Autowired
    private FiltroRepository repository;
    private Administradora administradora;

    @Before
    public void setup() {

        Filtro filtro = FiltroBuilder.getFactory().criar();
        Logradouro campoDaAreia = (Logradouro) sessionFactory.getCurrentSession()
                .createCriteria(Logradouro.class)
                .add(eq("cep", "22743310"))
                .uniqueResult();

        this.administradora = AdministradoraBuilder.getFactory()
                .comEndereco(campoDaAreia)
                .criar();
        sessionFactory.getCurrentSession().persist(administradora);

        this.administrador = PerfilBuilder.getFactory()
                .comNome("ADMINISTRADOR")
                .comFiltro(filtro)
                .criar();

        this.vendedor = PerfilBuilder.getFactory()
                .comNome("VENDEDOR")
                .criar();

        this.financeiro = PerfilBuilder.getFactory()
                .comNome("FINANCEIRO")
                .criar();

        this.sessionFactory.getCurrentSession().persist(administrador);
        this.sessionFactory.getCurrentSession().persist(vendedor);
        this.sessionFactory.getCurrentSession().persist(financeiro);


        this.usuario = UsuarioBuilder.getFactory()
                .noPerfil(this.administrador)
                .noPerfil(this.vendedor)
                .noPerfil(this.financeiro)
                .comAdministradora(administradora)
                .criar();

        this.sessionFactory.getCurrentSession().persist(usuario);


    }

    @Test
    public void devePesquisarOsFiltrosPorUsuario() {

        ResultList<Filtro> filtrosDeDados = repository.buscarFiltrosHabilitadosParaOUsuario(this.usuario);
        assertFalse(filtrosDeDados.estaVazio());
        assertEquals(1L, filtrosDeDados.getQuantidadeRegistros());


    }

}