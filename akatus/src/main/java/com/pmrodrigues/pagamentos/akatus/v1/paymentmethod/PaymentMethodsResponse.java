package com.pmrodrigues.pagamentos.akatus.v1.paymentmethod;


import com.pmrodrigues.pagamentos.akatus.AkatusResponse;
import com.pmrodrigues.pagamentos.akatus.entity.PaymentMethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentMethodsResponse extends AkatusResponse {
    private final List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
    }

    public Iterator<PaymentMethod> getPaymentMethodIterator() {
        return paymentMethods.iterator();
    }
}