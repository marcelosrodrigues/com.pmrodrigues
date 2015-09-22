package test.com.pmrodrigues.taglib.bean;

import javax.persistence.Column;
import java.io.Serializable;


public class Secao implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Column(name = "secao")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }
}
