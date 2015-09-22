package test.com.pmrodrigues.vraptor.crud.controller;

import java.io.Serializable;

/**
 * Created by Marceloo on 13/10/2014.
 */
public class Loja implements Serializable {

    private Long id;

    private String nome;

    public void setNome(final String nome) {
        this.nome = nome;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
}
