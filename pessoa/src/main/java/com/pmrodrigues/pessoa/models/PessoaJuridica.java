package com.pmrodrigues.pessoa.models;

import br.com.caelum.stella.bean.validation.CNPJ;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Marceloo on 22/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PessoaJuridica extends Pessoa {


    @NotEmpty(message = "CNPJ é obrigatório")
    @CNPJ(formatted = true,message = "CNPJ inválido")
    @Column
    @Setter
    @Getter
    private String cnpj;

    @Override
    public String getDocumento() {
        return this.getCnpj();
    }

    @Override
    public Pessoa comDocumento(final String documento) {
        this.cnpj = documento;
        return this;
    }
}
