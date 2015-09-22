package com.pmrodrigues.boletos.models;

import com.pmrodrigues.boletos.gerador.GeradorBoleto;
import com.pmrodrigues.pessoa.models.Pessoa;
import com.pmrodrigues.xml.adapters.BigDecimalTypeAdapter;
import com.pmrodrigues.xml.adapters.DateTypeAdapter;
import lombok.*;
import org.apache.log4j.Logger;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.joda.time.DateTime;
import org.jrimum.bopepo.view.BoletoViewer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by Marceloo on 15/04/2015.
 */
@XmlType(name = "BoletoType", namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
@Filters({
        @Filter(name = "boletos_por_emissor")
})
@EqualsAndHashCode()
@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Boleto implements Serializable {

    private static final Logger logging = Logger.getLogger(Boleto.class);

    @XmlTransient
    @Autowired
    @Transient
    private GeradorBoleto gerador = new GeradorBoleto();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @XmlElement(name = "numeroDocumento", required = true,
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
    @Setter
    @Getter
    private String numeroDocumento;

    @XmlJavaTypeAdapter(value = DateTypeAdapter.class, type = Date.class)
    @XmlElement(name = "dataVencimento",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @XmlSchemaType(name = "date")
    @Column
    @Setter
    @Getter
    private Date dataVencimento;

    @XmlJavaTypeAdapter(value = DateTypeAdapter.class, type = Date.class)
    @XmlElement(name = "dataEmissao",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @XmlSchemaType(name = "date")
    @Column
    @Setter
    @Getter
    private Date dataEmissao;

    @XmlElement(name = "agencia",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Embedded
    @Setter
    @Getter
    private Agencia agencia;

    @XmlElement(name = "conta",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Embedded
    @Setter
    @Getter
    private ContaCorrente conta;

    @XmlElement(name = "nossoNumero",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Embedded
    @Setter
    @Getter
    private NossoNumero nossoNumero;

    @XmlElement(name = "cedente",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "cedente_id")
    @Setter
    @Getter
    private Cedente cedente;

    @XmlElement(name = "sacado",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sacado_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    @Setter
    @Getter
    private Pessoa sacado;

    @XmlElement(name = "banco",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Enumerated(EnumType.STRING)
    @Setter
    @Getter
    private Banco banco;

    @XmlElement(name = "carteira",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Setter
    @Getter
    private Integer carteira;

    @XmlElement(name = "instrucao",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "boleto_id")
    @Getter
    private List<Instrucao> instrucoes = new ArrayList<>();

    @XmlElement(name = "valor",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @XmlJavaTypeAdapter(value = BigDecimalTypeAdapter.class, type = BigDecimal.class)
    @XmlSchemaType(name = "decimal")
    @Setter
    @Getter
    private BigDecimal valor;

    @XmlTransient
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    private Date dataCriacao;

    @XmlTransient
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    private Date dataAlteracao;

    @XmlElement(name = "modalidade",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Column(name = "modalidade")
    @Setter
    @Getter
    private Integer modalidade;


    @XmlJavaTypeAdapter(value = DateTypeAdapter.class, type = Date.class)
    @XmlElement(name = "dataProcessamento",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @XmlSchemaType(name = "date")
    @Setter
    @Getter
    private Date dataProcessamento;

    @XmlElement(name = "localPagamento",
            namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0",
            required = true)
    @Column(name = "localPagamento")
    @Setter
    @Getter
    private String localPagamento;


    @PrePersist
    public void onInsert() {
        dataCriacao = DateTime.now().toDate();
        dataAlteracao = DateTime.now().toDate();
    }

    @PreUpdate
    public void onUpdate() {
        dataAlteracao = DateTime.now().toDate();
    }

    public String[] getInstrucoes() {
        final List<String> instrucoes = new ArrayList<>();
        for (final Instrucao instrucao : this.instrucoes) {
            instrucoes.add(instrucao.getInstrucao());
        }

        return instrucoes.toArray(new String[0]);
    }

    public Boleto comCliente(final Pessoa sacado) {
        this.sacado = sacado;
        return this;
    }

    public Boleto comCedente(final Cedente cedente) {
        this.cedente = cedente;
        return this;
    }

    public Boleto comBanco(final Banco banco) {
        this.banco = banco;
        return this;
    }

    public Boleto comAgencia(final Agencia agencia) {
        this.agencia = agencia;
        return this;
    }

    public Boleto comNossoNumero(final NossoNumero nossoNumero) {
        this.nossoNumero = nossoNumero;
        return this;
    }

    public Boleto comContaCorrente(final ContaCorrente conta) {
        this.conta = conta;
        return this;
    }

    public Boleto comDataEmissao(final Date dataEmissao) {
        this.dataEmissao = dataEmissao;
        return this;
    }

    public Boleto comDataVencimento(final Date dataVencimento) {
        this.dataVencimento = dataVencimento;
        return this;
    }

    public Boleto comInstrucoes(final String... linhas) {
        for (final String linha : linhas) {
            this.instrucoes.add(Instrucao.novo(linha));
        }
        return this;
    }

    public Boleto comCarteira(final Integer carteira) {
        this.carteira = carteira;
        return this;
    }

    public Boleto comValor(final BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Boleto comNumeroDocumento(final String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public Boleto comDataProcessamento(final Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
        return this;
    }

    public String getLinhaDigitavel() {

        try {
            return this.gerador.gerar(this).getLinhaDigitavel().write();
        } catch (Exception e) {
            return "";
        }

    }

    public InputStream imprimir() {

        try {
            logging.info(format("Inicio da impressão do boleto para o cedente %s - cliente %s - data de vencimento %s - nosso numero %s - valor %s",
                    this.getCedente().getNome(),
                    this.getSacado().getNome(),
                    this.getDataVencimento(),
                    this.getNossoNumero().getNumero(),
                    this.getValor()));

            final BoletoViewer viewer = BoletoViewer.create(this.gerador.gerar(this));
            return new ByteArrayInputStream(viewer.getPdfAsByteArray());

        } finally {
            logging.info(format("Inicio da impressão do boleto para o cedente %s - cliente %s - data de vencimento %s - nosso numero %s - valor %s",
                    this.getCedente().getNome(),
                    this.getSacado().getNome(),
                    this.getDataVencimento(),
                    this.getNossoNumero().getNumero(),
                    this.getValor()));
        }

    }

    public Boleto comModalidade(final Integer modalidade) {
        this.modalidade = modalidade;
        return this;
    }

    public Boleto comLocalPagamento(final String localPagamento) {
        this.localPagamento = localPagamento;
        return this;
    }

}
