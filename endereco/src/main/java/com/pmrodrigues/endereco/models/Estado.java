package com.pmrodrigues.endereco.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(columnDefinition = "char(2)", nullable = false, unique = true)
    private String uf;

    @NotNull
    @Column(insertable = true, updatable = true, nullable = false)
    private String nome;

    public String getUF() {
        return uf;
    }

    public void setUF(final String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Estado comUF(final String uf) {
        this.uf = uf;
        return this;
    }
}
