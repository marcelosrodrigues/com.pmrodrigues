package com.pmrodrigues.boletos.models;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by Marceloo on 16/04/2015.
 */
@XmlType(name = "InstrucaoType"
        , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table
@EqualsAndHashCode(exclude = {"nome", "convenio"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(staticName = "novo", access = AccessLevel.PUBLIC)
public class Instrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Setter
    @Getter
    private Long id;


    @XmlElement(name = "linha"
            , namespace = "http://data.pmrodrigues.biz/boleto/emissor/1.0"
            , required = true)
    @Column
    @NonNull
    @Setter
    @Getter
    private String instrucao;


}
