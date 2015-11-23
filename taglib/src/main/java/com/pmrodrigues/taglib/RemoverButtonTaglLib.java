package com.pmrodrigues.taglib;

/**
 * Created by Marceloo on 17/11/2015.
 */
public class RemoverButtonTaglLib extends AbstractButtonTagLib {
    @Override
    public String getIcone() {
        return "fa-times";
    }

    @Override
    protected String getTemplate() {
        return "<button name=\"acao\" value=\"%s\" class=\"btn btn-danger\"><i class=\"fa %s\"></i> %s</button>";
    }
}
