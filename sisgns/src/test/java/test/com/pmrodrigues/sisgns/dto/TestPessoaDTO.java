package test.com.pmrodrigues.sisgns.dto;

import com.pmrodrigues.pessoa.models.PessoaFisica;
import com.pmrodrigues.pessoa.models.PessoaJuridica;
import com.pmrodrigues.sisgns.dto.Pessoa;
import org.junit.Test;
import test.com.pmrodrigues.sisgns.utilities.GeradorCNPJCPF;

import static org.junit.Assert.assertTrue;

/**
 * Created by Marceloo on 16/11/2015.
 */
public class TestPessoaDTO {

    @Test
    public void deveCriarUmaInstanciaDePessoaFisica() {

        Pessoa pessoa = new Pessoa();
        pessoa.setDocumento(GeradorCNPJCPF.cpf());

        assertTrue(pessoa.novoEntity() instanceof PessoaFisica);

    }

    @Test
    public void deveCriarUmaPessoaJuridica() {
        Pessoa pessoa = new Pessoa();
        pessoa.setDocumento(GeradorCNPJCPF.cnpj());

        assertTrue(pessoa.novoEntity() instanceof PessoaJuridica);
    }
}
