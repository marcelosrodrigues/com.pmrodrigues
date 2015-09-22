package com.pmrodrigues.pagamentos;

import com.pmrodrigues.pagamentos.akatus.entity.Holder;
import com.pmrodrigues.pagamentos.akatus.entity.Transaction;
import com.pmrodrigues.pagamentos.models.OrdemPagamento;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoService extends AbstractPagamentoService {

    @Override
    public void pagar(final OrdemPagamento pagamento) {

        final Transaction trans = criarTransacao(pagamento);


        trans.setPaymentMethod(pagamento.getMeioPagamento());

        trans.setExpiration(DateFormatUtils.format(
                pagamento.getPortador().getCartaoCredito().getDataExpiracao(), "MM/yyyy"));
        trans.setNumber(pagamento.getPortador().getCartaoCredito().getNumero());
        trans.setSecurityNumber(pagamento.getPortador().getCartaoCredito().getCodigoSeguranca());

        trans.setHolder(new Holder());
        trans.getHolder().setDocument(pagamento.getPortador().getCPF());
        trans.getHolder().setName(pagamento.getPortador().getNome());
        trans.getHolder().setPhone(pagamento.getPortador().getTelefone());

        super.execute(pagamento);

    }

}
