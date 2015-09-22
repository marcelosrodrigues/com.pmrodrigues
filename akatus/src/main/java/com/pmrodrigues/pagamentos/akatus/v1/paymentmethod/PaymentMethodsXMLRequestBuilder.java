package com.pmrodrigues.pagamentos.akatus.v1.paymentmethod;


import com.pmrodrigues.pagamentos.akatus.AkatusOperation;
import com.pmrodrigues.pagamentos.akatus.v1.AkatusXMLRequestBuilder;
import org.w3c.dom.Element;

class PaymentMethodsXMLRequestBuilder extends AkatusXMLRequestBuilder {
    public String build(AkatusOperation operation) {
        createDocument();
        createPaymentMethods((PaymentMethodsOperation) operation);

        return transformDocument();
    }

    private void createPaymentMethods(PaymentMethodsOperation operation) {
        final Element paymentMethodsElement = document
                .createElement("meios_de_pagamento");

        paymentMethodsElement.appendChild(createAccountElement("correntista",
                operation));

        document.appendChild(paymentMethodsElement);
    }
}