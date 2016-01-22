package com.pmrodrigues.endereco.models;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "logradouro", "numero", "complemento", "cep", "bairro", "cidade", "estado"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Valid
    @NotNull(message = "Bairro é obrigatório")
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "bairro_id")
    @Setter
    @Getter
    private Bairro bairro;

    @Valid
    @NotNull(message = "Cidade é obrigatório")
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cidade_id")
    @Setter
    @Getter
    private Cidade cidade;

    @Valid
    @NotNull(message = "Estado é obrigatório")
    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id")
    @Setter
    @Getter
    private Estado estado;

    @NotEmpty(message = "Número é obrigatório")
    @Column
    @Setter
    @Getter
    private String numero;

    @Column
    @Setter
    @Getter
    private String complemento;

    @NotEmpty(message = "Logradouro é obrigatório")
    @Column
    @Setter
    @Getter
    private String logradouro;

    @NotEmpty(message = "CEP é obrigatório")
    @Column
    @Setter
    @Getter
    private String cep;

    public Endereco(Bairro bairro, Cidade cidade, Estado estado, String numero, String logradouro, String cep) {

        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.logradouro = logradouro;
        this.cep = cep;
    }

    public Endereco comCEP(final String cep) {
        this.cep = cep;
        return this;
    }

    public Endereco comLogradouro(final String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public Endereco comNumero(final String numero) {
        this.numero = numero;
        return this;
    }

    public Endereco comComplemento(final String complemento) {
        this.complemento = complemento;
        return this;
    }

    public Endereco comCidade(final Cidade cidade) {
        this.cidade = cidade;
        return this;
    }

    public Endereco comEstado(final Estado estado) {
        this.estado = estado;
        return this;
    }

    public Endereco comBairro(final Bairro bairro) {
        this.bairro = bairro;
        return this;
    }

    public Endereco comId(final Long id) {
        this.id = id;
        return this;
    }
}
