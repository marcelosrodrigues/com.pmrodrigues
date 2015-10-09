package com.pmrodrigues.sisgns.models.security;


import com.pmrodrigues.security.utilities.MD5;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

import static com.pmrodrigues.security.Constante.NUMERO_MAXIMO_TENTATIVAS_FALHAS;
import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"password"})
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Usuario implements com.pmrodrigues.security.models.Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Setter
    @Getter
    @Column
    public String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Column(unique = true, nullable = false)
    @Setter
    @Getter
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    @Column
    @Getter
    private String password;

    @Column
    @Setter
    @Getter
    private boolean bloqueado = false;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "membros", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "perfil_id"}))
    @Getter
    private Collection<Perfil> perfis = new HashSet<>();

    @Column
    @Getter
    private Long tentativas = 0L;

    @Override
    public void realizouTentativaInvalida() {
        this.incrementarTentativasFalhas();
    }

    public void incrementarTentativasFalhas() {
        tentativas++;
        if (tentativas >= NUMERO_MAXIMO_TENTATIVAS_FALHAS) {
            bloquear();
        }
    }

    public void desbloquear() {
        this.tentativas = 0L;
        this.bloqueado = false;
    }

    public void bloquear() {
        this.bloqueado = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !bloqueado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(final String password) {
        if (!isBlankOrNull(password)) {
            this.password = MD5.encrypt(password);
        }
    }
}
