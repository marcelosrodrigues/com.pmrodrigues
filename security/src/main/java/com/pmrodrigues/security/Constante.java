package com.pmrodrigues.security;

import java.util.ResourceBundle;

/**
 * Created by Marceloo on 21/09/2015.
 */
public interface Constante {

    String NUMERO_MAXIMO_TENTATIVAS_FALHAS_FIELD = "numero_maximo_tentativas_falhas";

    Long NUMERO_MAXIMO_TENTATIVAS_FALHAS = Long.parseLong(ResourceBundle.getBundle("seguranca").getString(NUMERO_MAXIMO_TENTATIVAS_FALHAS_FIELD));
}
