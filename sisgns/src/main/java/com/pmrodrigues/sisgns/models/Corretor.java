package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.sisgns.models.security.Usuario;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.Entity;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@DiscriminatorFormula("exists ( select 1 from usuario u " +
                         " inner join membros m on u.id = m.usuario_id " +
                         " inner join perfil p on m.perfil_id = p.id " +
                         " where p.nome = 'CORRETOR' )")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Corretor extends Usuario {

}
