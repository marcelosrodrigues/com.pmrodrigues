package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.Action;
import com.pmodrigues.pageobjects.annotations.URL;
import com.pmodrigues.pageobjects.tags.Tag;
import com.pmodrigues.pageobjects.tags.TagFactory;
import org.openqa.selenium.WebDriver;

/**
 * Created by Marceloo on 01/10/2015.
 */
@URL(url = "http://localhost:8080/administradora/novo.do")
@Action(byName = "acao")
public class AdministradoraPlanosSaudePage extends AbstractPageObject {

    public AdministradoraPlanosSaudePage nome(final String nome) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.nome");
        tag.setValue(nome);
        return this;
    }

    public AdministradoraPlanosSaudePage cnpj(final String cnpj) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.numeroDocumento");
        tag.setValue(cnpj);
        return this;
    }

    public AdministradoraPlanosSaudePage cep(final String cep) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.cep");
        tag.setValue(cep);
        return this;
    }

    public AdministradoraPlanosSaudePage logradouro(final String logradouro) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.logradouro");
        tag.setValue(logradouro);
        return this;
    }

    public AdministradoraPlanosSaudePage numero(final String numero) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.numero");
        tag.setValue(numero);
        return this;
    }

    public AdministradoraPlanosSaudePage complemento(final String complemento) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.complemento");
        tag.setValue(complemento);
        return this;
    }

    public AdministradoraPlanosSaudePage estado(final String estado) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.estado.nome");
        tag.setValue(estado);
        return this;
    }

    public AdministradoraPlanosSaudePage cidade(final String cidade) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.cidade.nome");
        tag.setValue(cidade);
        return this;
    }

    public AdministradoraPlanosSaudePage bairro(final String bairro) {
        Tag tag = TagFactory.getInstance(super.getDriver()).createById("object.endereco.bairro.nome");
        tag.setValue(bairro);
        return this;
    }

    public AdministradoraPlanosSaudePage(final WebDriver driver) {
        super(driver);
    }

    public AdministradoraPlanosSaudePage adicionar(final String ddd, final String telefone) throws Exception {
        TagFactory.getInstance(super.getDriver())
                .createById("residencial.ddd")
                .setValue(ddd);
        TagFactory.getInstance(super.getDriver())
                .createById("residencial.numero")
                .setValue(telefone);
        this.clickById("adicionar-telefone");
        return this;
    }
}
