package com.pmrodrigues.pagamentos;


import com.pmrodrigues.pagamentos.akatus.entity.Holder;
import com.pmrodrigues.pagamentos.akatus.entity.Transaction;
import com.pmrodrigues.pagamentos.models.OrdemPagamento;
import org.springframework.stereotype.Service;


@Service
public class BoletoService extends AbstractPagamentoService {

    @Override
    public void pagar(final OrdemPagamento pagamento) {

        final Transaction trans = criarTransacao(pagamento);
        trans.setPaymentMethod(TipoPagamento.BOLETO);
        trans.setHolder(new Holder());
        trans.getHolder().setDocument(
                pagamento.getPortador().getCPF());
        trans.getHolder().setName(
                pagamento.getPortador().getNome());

        super.execute(pagamento);
    }


}



