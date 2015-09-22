package com.pmrodrigues.pagamentos.akatus.entity;

import com.pmrodrigues.pagamentos.TipoPagamento;

public class Transaction implements Entity {
    public enum Currency {
        BRL("BRL");

        private final String currency;

        Currency(String currency) {
            this.currency = currency;
        }

        @Override
        public String toString() {
            return currency;
        }
    }

    private final Currency currency = Currency.BRL;
    private Double discountAmount;
    private String expiration;
    private Holder holder;
    private Integer installments = 0;
    private String number;
    private TipoPagamento paymentMethod = TipoPagamento.BOLETO;
    private String reference;
    private String securityNumber;
    private Double shippingAmount;
    private Double weight;

    public Currency getCurrency() {
        return currency;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public String getExpiration() {
        return expiration;
    }

    public Holder getHolder() {
        return holder;
    }

    public Integer getInstallments() {
        return installments;
    }

    public String getNumber() {
        return number;
    }

    public TipoPagamento getPaymentMethod() {
        return paymentMethod;
    }

    public String getReference() {
        return reference;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public Double getWeight() {
        return weight;
    }

    public Holder holder() {
        if (holder == null) {
            holder = new Holder();
        }

        return holder;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPaymentMethod(TipoPagamento paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}