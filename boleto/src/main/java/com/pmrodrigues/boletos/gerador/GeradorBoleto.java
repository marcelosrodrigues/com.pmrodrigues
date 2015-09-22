package com.pmrodrigues.boletos.gerador;


import com.pmrodrigues.boletos.models.Boleto;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import org.jrimum.domkee.financeiro.banco.febraban.*;

import java.lang.reflect.Field;

import static java.lang.String.format;
import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 16/04/2015.
 */
public class GeradorBoleto {
    public org.jrimum.bopepo.Boleto gerar(final Boleto boleto) {

        final Cedente cedente = criarBeneficiario(boleto);
        final Sacado cliente = criarCliente(boleto);
        final ContaBancaria conta = createConta(boleto);
        final Titulo titulo = new Titulo(conta, cliente, cedente);
        titulo.setNumeroDoDocumento(boleto.getNumeroDocumento());
        titulo.setNossoNumero(boleto.getNossoNumero().getNumero());
        titulo.setDigitoDoNossoNumero(boleto.getNossoNumero().getDigito());
        titulo.setValor(boleto.getValor());
        titulo.setDataDoDocumento(boleto.getDataEmissao());
        titulo.setDataDoVencimento(boleto.getDataVencimento());

        if (boleto.getModalidade() != null) {
            titulo.setParametrosBancarios(new ParametrosBancariosMap("ModalidadeDeCobranca", boleto.getModalidade()));
        }


        final org.jrimum.bopepo.Boleto paraImpressao = new org.jrimum.bopepo.Boleto(titulo);

        if (!isBlankOrNull(boleto.getLocalPagamento())) {
            paraImpressao.setLocalPagamento(boleto.getLocalPagamento());
        }

        int i = 1;
        for (final String instrucao : boleto.getInstrucoes()) {
            adicionaInstrucao(paraImpressao, format("instrucao%s", i), instrucao);
            i++;
        }

        return paraImpressao;
    }

    private void adicionaInstrucao(final org.jrimum.bopepo.Boleto boleto, final String fieldName, final String instrucao) {
        try {
            final Field field = boleto.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(boleto, instrucao);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ContaBancaria createConta(final Boleto boleto) {
        final ContaBancaria conta = new ContaBancaria(boleto.getBanco().novoBanco());
        conta.setCarteira(new Carteira(boleto.getCarteira()));
        conta.setAgencia(new Agencia(boleto.getAgencia().getNumero(), boleto.getAgencia().getDigito()));
        conta.setNumeroDaConta(new NumeroDaConta(boleto.getConta().getNumero(), boleto.getConta().getDigito()));
        return conta;
    }

    private Sacado criarCliente(final Boleto boleto) {

        final Sacado sacado = new Sacado(boleto.getSacado().getNome(), boleto.getSacado().getCpf());
        final Endereco endereco = new Endereco();
        endereco.setBairro(boleto.getSacado().getEndereco().getLogradouro().getBairro().getNome());
        endereco.setCep(boleto.getSacado().getEndereco().getLogradouro().getCep());
        endereco.setLocalidade(boleto.getSacado().getEndereco().getLogradouro().getBairro().getCidade().getNome());
        if (!isBlankOrNull(boleto.getSacado().getEndereco().getLogradouro().getBairro().getCidade().getEstado().getUF())) {
            endereco.setUF(UnidadeFederativa.valueOfSigla(boleto.getSacado().getEndereco().getLogradouro().getBairro().getCidade().getEstado().getUF()));
        }
        endereco.setLocalidade(format("%s %s %s",
                boleto.getSacado().getEndereco().getLogradouro().getLogradouro(),
                boleto.getSacado().getEndereco().getNumero(),
                boleto.getSacado().getEndereco().getComplemento()));
        sacado.addEndereco(endereco);

        return sacado;
    }

    private Cedente criarBeneficiario(final Boleto boleto) {
        final Cedente cedente = new Cedente(
                boleto.getCedente().getNome(),
                boleto.getCedente().getNumeroDocumento());
        return cedente;

    }
}

