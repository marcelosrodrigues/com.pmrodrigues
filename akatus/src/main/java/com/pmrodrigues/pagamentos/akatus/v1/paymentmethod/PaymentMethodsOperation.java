package com.pmrodrigues.pagamentos.akatus.v1.paymentmethod;

import com.pmrodrigues.pagamentos.akatus.Akatus;
import com.pmrodrigues.pagamentos.akatus.AkatusOperation;
import com.pmrodrigues.pagamentos.akatus.AkatusRequestBuilder;
import com.pmrodrigues.pagamentos.akatus.AkatusResponseBuilder;

/**
 * Implementação da operação meios de pagamento da API da Akatus.
 *
 * @author João Batista Neto
 */
public class PaymentMethodsOperation extends AkatusOperation {
    public PaymentMethodsOperation(Akatus akatus) {
        super(akatus);
    }

    protected AkatusRequestBuilder createDefaultRequestBuilder() {
        return new PaymentMethodsXMLRequestBuilder();
    }

    protected AkatusResponseBuilder createDefaultResponseBuilder() {
        return new PaymentMethodsXMLResponseBuilder();
    }

    public String getPath() {
        final StringBuilder pathBuilder = new StringBuilder();

        pathBuilder.append("/api/");
        pathBuilder.append(getVersion());
        pathBuilder.append("/meios-de-pagamento.xml");

        return pathBuilder.toString();
    }
}