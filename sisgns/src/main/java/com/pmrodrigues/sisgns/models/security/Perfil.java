package com.pmrodrigues.sisgns.models.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    @NonNull
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "perfil_id")
    @Getter
    @Setter
    private Collection<Filtro> filtrosDeDados = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "membros", joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "perfil_id"}))
    @Getter
    private Collection<Usuario> usuarios = new HashSet<>();


    @Override
    public String getAuthority() {
        return nome;
    }
}
