package com.pmrodrigues.pessoa.models;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PessoaFisica extends Pessoa {

    @Column(unique = true, nullable = false)
    @CPF(formatted = true, message = "CPF é inválido")
    @NotEmpty(message = "CPF é obrigatório")
    @NonNull
    @Setter
    @Getter
    private String cpf;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "pai_id")
    @Setter
    @Getter
    private Collection<PessoaFisica> dependentes;

    @Temporal(TemporalType.DATE)
    @Setter
    @Getter
    private Date dataNascimento;

    @Override
    public String getDocumento() {
        return this.getCpf();
    }

    @Override
    public Pessoa comDocumento(final String documento) {
        this.cpf = documento;
        return this;
    }
}
