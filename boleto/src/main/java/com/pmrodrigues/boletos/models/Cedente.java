package com.pmrodrigues.boletos.models;

import br.com.caelum.stella.bean.validation.CNPJ;
import com.pmrodrigues.endereco.models.Endereco;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Marceloo on 19/09/2014.
 */
@Entity
@Table
@XmlType(name = "CedenteType"
        , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(of = {"id", "numeroDocumento"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(staticName = "novoCedente", access = AccessLevel.PUBLIC)
public class Cedente implements Serializable {

    @XmlElement(name = "id"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = false
            , type = Long.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @XmlElement(name = "nome"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(unique = true)
    @NonNull
    @NotEmpty(message = "Nome é obrigatório")
    @Setter
    @Getter
    private String nome;

    @XmlElement(name = "numeroDocumento"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column(nullable = false, unique = true)
    @CNPJ(formatted = true, message = "CNPJ inválido")
    @NotEmpty(message = "CNPJ do cedente é obrigatório")
    @NonNull
    @Setter
    @Getter
    private String numeroDocumento;

    @XmlElement(name = "convenio"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column
    @Setter
    @Getter
    private String convenio;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Getter
    @Setter
    private Endereco endereco;


    public Cedente comNome(final String nome) {
        this.nome = nome;
        return this;
    }

    public Cedente comNumeroConvenio(final String convenio) {
        this.convenio = convenio;
        return this;
    }

    public Cedente comNumeroDocumento(final String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public Cedente comId(final Long id) {
        this.id = id;
        return this;
    }

    public Cedente comEndereco(final Endereco endereco) {
        this.endereco = endereco;
        return this;
    }
}
