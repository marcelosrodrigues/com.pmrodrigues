package com.pmrodrigues.sisgns.models.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@Table
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String nome;


    @Override
    public String getAuthority() {
        return nome;
    }
}
