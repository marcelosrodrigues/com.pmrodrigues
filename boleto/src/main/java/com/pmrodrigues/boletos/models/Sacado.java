package com.pmrodrigues.boletos.models;

import br.com.caelum.stella.bean.validation.CPF;
import com.pmrodrigues.endereco.models.Endereco;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;


@XmlType(name = "SacadoType"
        , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table
@EqualsAndHashCode(exclude = {"nome", "endereco", "password"})
@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Sacado implements Serializable {

    @XmlElement(name = "id"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = false
            , type = Long.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @XmlElement(name = "cpf"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(unique = true, nullable = false)
    @CPF(formatted = true, message = "CPF é inválido")
    @NotEmpty(message = "CPF é obrigatório")
    @NonNull
    @Setter
    @Getter
    private String cpf;

    @XmlElement(name = "nome"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(nullable = false)
    @NotEmpty(message = "Nome é obrigatório")
    @NonNull
    @Setter
    @Getter
    private String nome;

    @XmlElement(name = "endereco"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Embedded
    @NonNull
    @Setter
    @Getter
    private Endereco endereco;

    @XmlElement(name = "email"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(nullable = false)
    @NotEmpty(message = "E-mail do cliente é obrigatório")
    @NonNull
    @Setter
    @Getter
    private String email;

    @XmlTransient
    @Column
    @Setter
    @Getter
    private String password;

    public Sacado comEndereco(final Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public Sacado comNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public Sacado comCPF(final String cpf) {
        this.cpf = cpf;
        return this;
    }

    public Sacado comEmail(final String email) {
        this.email = email;
        return this;
    }
}
