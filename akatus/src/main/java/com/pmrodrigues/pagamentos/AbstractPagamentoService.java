package com.pmrodrigues.pagamentos;

import com.pmrodrigues.pagamentos.akatus.Akatus;
import com.pmrodrigues.pagamentos.akatus.entity.Address;
import com.pmrodrigues.pagamentos.akatus.entity.Payer;
import com.pmrodrigues.pagamentos.akatus.entity.Phone;
import com.pmrodrigues.pagamentos.akatus.entity.Transaction;
import com.pmrodrigues.pagamentos.akatus.v1.cart.CartOperation;
import com.pmrodrigues.pagamentos.akatus.v1.cart.CartResponse;
import com.pmrodrigues.pagamentos.models.Item;
import com.pmrodrigues.pagamentos.models.OrdemPagamento;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:akatus.properties")
public abstract class AbstractPagamentoService implements PagamentoService {

    private final static Logger LOGGER = Logger
            .getLogger(AbstractPagamentoService.class);

    private CartOperation carrinho;

    @Value("${AUTH_USER}")
    private String user;

    @Value("${AUTH_PASSWORD}")
    private String password;

    @Value("${AKATUR_URL}")
    private String enviroment;


    protected Transaction criarTransacao(final OrdemPagamento pagamento) {
        this.carrinho = new Akatus(Akatus.Environment.valueOf(enviroment),
                user,
                password).cart();

        final Payer pagador = create(pagamento);

        carrinho.setPayer(pagador);

        for (final Item item : pagamento.getItens()) {
            carrinho.addProduct(item.getId(), item.getDescricao(),
                    item.getPreco().doubleValue(), 0D, item.getQuantidade().intValue(), 0D);
        }

        final Transaction trans = carrinho.getTransaction();
        trans.setReference(pagamento.getCodigoReferencia());
        trans.setInstallments(pagamento.getNumeroPrestacoes());

        return trans;
    }

    protected Payer create(final OrdemPagamento pagamento) {
        final Payer cliente = new Payer();
        cliente.setEmail(pagamento.getPortador().getEmail());
        cliente.setName(pagamento.getPortador().getNome());

        final Address address = new Address();
        address.setCity(pagamento.getPortador().getEndereco().getCidade());
        address.setComplement(pagamento.getPortador().getEndereco().getComplemento());
        address.setCountry(Address.Country.BRA);
        address.setNeighbourhood(pagamento.getPortador().getEndereco().getBairro());
        address.setNumber(pagamento.getPortador().getEndereco().getNumero());
        address.setState(Address.State.valueOf(pagamento.getPortador().getEndereco().getEstado().getUf()));
        address.setStreet(pagamento.getPortador().getEndereco().getLogradouro());
        address.setType(Address.Type.SHIPPING);
        address.setZip(pagamento.getPortador().getEndereco().getCEP());

        cliente.addAddress(address);
        cliente.addPhone(getPhone(Phone.Type.RESIDENTIAL, pagamento.getPortador().getTelefone()));

        return cliente;
    }

    protected Phone getPhone(final Phone.Type type, final String number) {
        final Phone phone = new Phone();
        phone.setNumber(number);
        phone.setType(type);
        return phone;
    }

    protected void execute(final OrdemPagamento pagamento) {

        LOGGER.info("Mandando ordem de pagamento para a akatus");
        final CartResponse response = (CartResponse) this.carrinho.execute();
        LOGGER.info("Ordem enviada com sucesso");
        pagamento.comTransacao(response.getTransaction())
                .status(response.getStatus(), response.getDescription())
                .comDocumento(response.getReturnURL());

    }

}
