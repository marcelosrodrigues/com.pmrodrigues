package test.com.pmrodrigues.persistence.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marceloo on 13/10/2014.
 */
@Entity
@Table
public class Loja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    public void setNome(final String nome) {
        this.nome = nome;
    }


}
