package test.com.pmrodrigues.sisgns.acceptables.pageobjects;

import com.pmodrigues.pageobjects.AbstractPageObject;
import com.pmodrigues.pageobjects.annotations.URL;
import com.pmodrigues.pageobjects.tags.TagFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 01/10/2015.
 */
@URL("http://localhost:8080/administradora/novo.do")
public class AdministradoraPlanosSaudePage extends AbstractPageObject {

    protected AdministradoraPlanosSaudePage(final WebDriver driver, final boolean navigateTo) {
        super(driver, navigateTo);
    }

    public AdministradoraPlanosSaudePage nome(final String nome) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.nome")
                .setValue(nome);
        return this;
    }

    public AdministradoraPlanosSaudePage cnpj(final String cnpj) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.numeroDocumento")
                .setValue(cnpj);
        return this;
    }

    public AdministradoraPlanosSaudePage cep(final String cep) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.endereco.cep")
                .setValue(cep);
        return this;
    }

    public AdministradoraPlanosSaudePage logradouro(final String logradouro) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.endereco.logradouro")
                .setValue(logradouro);
        return this;
    }

    public AdministradoraPlanosSaudePage numero(final String numero) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.endereco.numero")
                .setValue(numero);
        return this;
    }

    public AdministradoraPlanosSaudePage complemento(final String complemento) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.endereco.complemento")
                .setValue(complemento);
        return this;
    }

    public AdministradoraPlanosSaudePage estado(final String estado) {
        TagFactory.getInstance(super.getDriver())
                .byId("object.endereco.estado.nome")
                .setValue(estado);

        return this;
    }

    public AdministradoraPlanosSaudePage cidade(final String cidade, final Integer estado) {

        boolean atualizouEstado = new WebDriverWait(this.getDriver(), 10)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(final WebDriver input) {
                        return String.valueOf(estado).equals(TagFactory.getInstance(input).byId("object.endereco.estado").getValue()) &&
                                String.valueOf(estado).equals(TagFactory.getInstance(input).byId("object.endereco.cidade.estado").getValue());
                    }
                });

        if (atualizouEstado) {
            TagFactory.getInstance(super.getDriver())
                    .byId("object.endereco.cidade.nome")
                    .setValue(cidade);
        }

        return this;
    }

    public AdministradoraPlanosSaudePage bairro(final String bairro, final Integer cidade) {

        boolean atualizouCidade = new WebDriverWait(this.getDriver(), 10)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(final WebDriver input) {
                        return String.valueOf(cidade).equals(TagFactory.getInstance(input).byId("object.endereco.cidade").getValue()) &&
                                String.valueOf(cidade).equals(TagFactory.getInstance(input).byId("object.endereco.bairro.cidade").getValue());
                    }
                });

        if (atualizouCidade) {
            TagFactory.getInstance(super.getDriver())
                    .byId("object.endereco.bairro.nome")
                    .setValue(bairro);

            new WebDriverWait(this.getDriver(), 10)
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(final WebDriver input) {
                            return !isBlankOrNull((String) TagFactory.getInstance(input).byId("object.endereco.bairro").getValue());
                        }
                    });

        }

        return this;
    }

    public AdministradoraPlanosSaudePage(final WebDriver driver) {
        super(driver);
    }

    public AdministradoraPlanosSaudePage adicionar(final String ddd, final String telefone) throws Exception {
        TagFactory.getInstance(super.getDriver())
                .byId("residencial.ddd")
                .setValue(ddd);
        TagFactory.getInstance(super.getDriver())
                .byId("residencial.numero")
                .setValue(telefone);

        this.getDriver().findElement(By.id("adicionar-telefone"))
                .click();
        return this;
    }

    @Override
    public AbstractPageObject submit() {
        this.getDriver().findElement(By.name("acao"))
                .click();

        return new ListAdministradoraPlanosSaudePage(this.getDriver());
    }

    @Override
    public AbstractPageObject abrir(String value) {
        return null;
    }

    public AdministradoraPlanosSaudePage apagar(final String telefone) {
        List<WebElement> buttons = this.getDriver().findElements(By.id("remover-telefone"));

        for (WebElement button : buttons) {
            if (telefone.equalsIgnoreCase(button.getAttribute("telefone"))) {
                button.click();

                boolean apagouTelefone = new WebDriverWait(this.getDriver(), 5)
                        .until(new ExpectedCondition<Boolean>() {
                            @Override
                            public Boolean apply(final WebDriver input) {
                                return !input.getPageSource().contains(telefone);
                            }
                        });
                if (apagouTelefone) {
                    break;
                }
            }
        }

        return this;
    }
}
