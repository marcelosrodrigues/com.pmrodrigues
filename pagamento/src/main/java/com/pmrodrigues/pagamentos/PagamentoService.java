package com.pmrodrigues.pagamentos;

import com.pmrodrigues.pagamentos.models.OrdemPagamento;

public interface PagamentoService {

    void pagar(final OrdemPagamento pagamento);
}
