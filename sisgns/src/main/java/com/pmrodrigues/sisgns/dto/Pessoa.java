package com.pmrodrigues.sisgns.dto;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import com.pmrodrigues.endereco.models.Celular;
import com.pmrodrigues.endereco.models.Endereco;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.pessoa.models.PessoaFisica;
import com.pmrodrigues.pessoa.models.PessoaJuridica;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(exclude = {"id", "nome", "endereco", "password"})
@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Pessoa implements Serializable {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    @NotEmpty
    private String nome;

    @Column
    @Setter
    @Getter
    @NotEmpty
    private String email;

    @Setter
    @Getter
    private Endereco endereco;

    @Getter
    @Setter
    private Collection<Telefone> residenciais = new ArrayList<>();

    @Getter
    @Setter
    private Collection<Celular> celulares = new ArrayList<>();

    @Getter
    @Setter
    private String documento;

    @Getter
    @Setter
    private Date dataNascimento;

    public com.pmrodrigues.pessoa.models.Pessoa novoEntity() {
        final CPFValidator cpfValidator = new CPFValidator();
        final CNPJValidator cnpjValidator = new CNPJValidator();
        if (cpfValidator.isEligible(this.documento)) {
            return ((PessoaFisica) new PessoaFisica()
                    .comNome(this.nome)
                    .comEndereco(this.endereco)
                    .comEmail(this.email)
                    .comDocumento(this.documento))
                    .comDataNascimento(this.dataNascimento);
        } else if (cnpjValidator.isEligible(this.documento)) {
            return new PessoaJuridica()
                    .comNome(this.nome)
                    .comEndereco(this.endereco)
                    .comEmail(this.email)
                    .comDocumento(this.documento);
        }
        return null;
    }
}
