package com.pmrodrigues.taglib;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 25/05/2015.
 */
public class ImprimirButtonTagLib extends AbstractButtonTagLib {
    @Override
    public String getIcone() {
        return "fa-print";
    }

    @Override
    public String getLabel() {
        if (isBlankOrNull(super.getLabel())) {
            return "Imprimir";
        } else {
            return super.getLabel();
        }
    }
}
