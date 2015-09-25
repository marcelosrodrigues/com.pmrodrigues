package test.com.pmrodrigues.boletos.model;

import com.pmrodrigues.boletos.gerador.GeradorBoleto;
import com.pmrodrigues.boletos.models.*;
import com.pmrodrigues.endereco.models.Bairro;
import com.pmrodrigues.endereco.models.Cidade;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Estado;
import com.pmrodrigues.pessoa.models.PessoaFisica;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marceloo on 27/04/2015.
 */
public class TestBoleto {

    @Test
    public void imprimir() throws Exception {

        final Boleto boleto = gerarBoleto();
        final GeradorBoleto gerador = new GeradorBoleto();

        Field field = boleto.getClass().getDeclaredField("gerador");
        field.setAccessible(true);
        field.set(boleto, gerador);

        InputStream stream = boleto.imprimir();

        assertNotNull(stream);
    }

    @Test
    public void gerarLinhaDigitavel() throws Exception {
        final Boleto boleto = gerarBoleto();
        final GeradorBoleto gerador = new GeradorBoleto();

        Field field = boleto.getClass().getDeclaredField("gerador");
        field.setAccessible(true);
        field.set(boleto, gerador);

        String linhaDigitavel = boleto.getLinhaDigitavel();
        assertNotNull(linhaDigitavel);
        assertEquals("75691.43022 02973.844406 06312.950014 3 64190000048766", boleto.getLinhaDigitavel());
    }

    private Boleto gerarBoleto() {
        return new Boleto().comCliente(new PessoaFisica()
                .comNome("MARCELO DA SILVA RODRIGUES")
                .comDocumento("07032327702")
                .comEmail("marcelosrodrigues@globo.com")
                .comEndereco(new Endereco()
                        .comNumero("84")
                        .comComplemento("apto 206")
                        .comLogradouro("Estrada do Campo da Areia")
                        .comCEP("22743310")
                        .comBairro(new Bairro().comNome("Pechincha"))
                        .comCidade(new Cidade().comNome("RIO DE JANEIRO"))
                        .comEstado(new Estado().comUF("RJ"))))
                .comCedente(new Cedente()
                        .comNome("ALACORO CLUB CASF")
                        .comNumeroDocumento("04769697000110"))
                .comBanco(Banco.BANCOOB)
                .comAgencia(new Agencia()
                        .comNumero(4302)
                        .comDigito("8"))
                .comNossoNumero(new NossoNumero()
                        .comNumero("00631295"))
                .comContaCorrente(new ContaCorrente()
                        .comNumero(9738444))
                .comDataEmissao(new DateTime(2015, 5, 4, 0, 0, 0).toDate())
                .comDataVencimento(new DateTime(2015, 5, 5, 0, 0, 0).toDate())
                .comInstrucoes("linha 1", "linha 2", "linha 3")
                .comCarteira(1)
                .comNumeroDocumento("29709")
                .comValor(new BigDecimal(487.66))
                .comModalidade(2);
    }
}