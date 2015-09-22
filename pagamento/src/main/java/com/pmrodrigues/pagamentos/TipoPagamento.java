package com.pmrodrigues.pagamentos;

/**
 * Created by Marceloo on 15/04/2015.
 */
public enum TipoPagamento {
    BOLETO("boleto"), TEF_ITAU("tef_itau"), TEF_BRADESCO("tef_bradesco"), CARTAO_VISA(
            "cartao_visa"), CARTAO_MASTER("cartao_master"), CARTAO_AMEX(
            "cartao_amex"), CARTAO_ELO("cartao_elo"), CARTAO_DINERS(
            "cartao_diners"), DINHEIRO("dinheiro");

    private final String paymentMethod;

    private TipoPagamento(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return paymentMethod;
    }
}
