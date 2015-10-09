package com.pmrodrigues.sisgns.models;

import com.pmrodrigues.sisgns.models.security.Usuario;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Created by Marceloo on 21/09/2015.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Corretor extends Usuario {

}
