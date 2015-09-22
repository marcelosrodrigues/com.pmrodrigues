package com.pmrodrigues.endereco.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
@EqualsAndHashCode(exclude = {"id" , "nome"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public @Data class Estado implements Serializable {

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

    public Estado comUF(final String uf) {
        this.uf = uf;
        return this;
    }
}
