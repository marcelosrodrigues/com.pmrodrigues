package com.pmrodrigues.taglib;

/**
 * Created by Marceloo on 17/11/2015.
 */
public class AdicionarButtonTaglLib extends AbstractButtonTagLib {
    @Override
    public String getIcone() {
        return "fa-plus";
    }

    @Override
    protected String getTemplate() {
        return "<button name=\"acao\" value=\"%s\" class=\"btn btn-success\"><i class=\"fa %s\"></i> %s</button>";
    }
}
