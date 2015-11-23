package com.pmrodrigues.pessoa.models;

import br.com.caelum.stella.bean.validation.CPF;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(callSuper = true, exclude = {"pai", "dependentes"})
@ToString(callSuper = true, exclude = {"dependentes"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PessoaFisica extends Pessoa {

    @Column(unique = true, nullable = false)
    @CPF(formatted = true, message = "CPF é inválido")
    @NotEmpty(message = "CPF é obrigatório")
    @Setter
    @Getter
    private String cpf;

    @OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "pai_id")
    @Getter
    private Collection<PessoaFisica> dependentes = new HashSet<>();

    @ManyToOne()
    @Setter
    @Getter
    private PessoaFisica pai;

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

    public PessoaFisica comDataNascimento(final Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public void setDependentes(final Collection<PessoaFisica> dependentes) {
        for (final PessoaFisica dependente : dependentes) {
            if (!this.dependentes.contains(dependente)) {
                dependente.setPai(this);
                dependente.setEndereco(this.getEndereco());
                this.dependentes.add(dependente);
            }
        }
    }
}
