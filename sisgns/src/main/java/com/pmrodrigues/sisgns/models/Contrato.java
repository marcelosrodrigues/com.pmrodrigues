package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.boletos.models.Boleto;
import com.pmrodrigues.pessoa.models.Pessoa;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@SQLDelete(sql = "update contrato set excluido = 1 where id = ?")
@Where(clause = "excluido = 0")
@EqualsAndHashCode(of = {"numeroContrato"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @Getter
    @Setter
    @ManyToOne(optional = false , cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Pessoa cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contrato_id", nullable = false)
    @Getter
    private Collection<Beneficiario> beneficiarios = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "corretor_id")
    @Getter
    @Setter
    private Corretor vendidoPor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administradora_id")
    @Getter
    @Setter
    private Administradora administradora;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="contrato_id")
    @OrderBy("dataEmissao DESC")
    @Getter
    private Collection<Boleto> boletosEmitidos = new HashSet<>();


    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataInicio;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dataTermino;

    @Setter
    @Getter
    @Column
    private String numeroContrato;

    public Contrato comBeneficiarios(final Collection<Beneficiario> beneficiarios) {
        this.beneficiarios.addAll(beneficiarios);
        return this;
    }

    public Contrato daAdministradora(final Administradora administradora) {
        this.administradora = administradora;
        return this;
    }

    public Contrato comPlano(final Plano plano) {
        this.plano = plano;
        return this;
    }

    public Contrato vendidoPor(final Corretor corretor) {
        this.vendidoPor = corretor;
        return this;
    }

    public Contrato comNumeroContrato(final String numero) {
        this.numeroContrato = numero;
        return this;
    }

    public Contrato comDataAssinatura(final Date dataAssinatura) {
        this.dataInicio = dataAssinatura;
        return this;
    }

    public Contrato paraCliente(final Pessoa cliente) {
        this.cliente = cliente;
        return this;
    }
}
