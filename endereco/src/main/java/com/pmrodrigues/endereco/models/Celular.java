package com.pmrodrigues.endereco.models;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marceloo on 23/09/2015.
 */
@Entity
@DiscriminatorValue("C")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Celular extends Telefone {
}
