package com.pmrodrigues.pagamentos.akatus.v1.status;


import com.pmrodrigues.pagamentos.akatus.AkatusOperation;
import com.pmrodrigues.pagamentos.akatus.v1.AkatusXMLRequestBuilder;

/**
 * Created by Marceloo on 10/03/2015.
 */
public class StatusPagamentoXMLRequestBuilder extends AkatusXMLRequestBuilder {
    @Override
    public String build(AkatusOperation operation) {
        createDocument();

        return transformDocument();
    }
}
