package com.pmrodrigues.taglib;

/**
 * Created by Marceloo on 28/05/2015.
 */
public class LoginButtonTagLib extends AbstractButtonTagLib {
    @Override
    public String getIcone() {
        return "fa-lock";
    }

    protected String getTemplate() {
        return "<button name=\"acao\" value=\"%s\" class=\"btn btn-lg btn-block btn-info\"><i class=\"fa %s\"></i> %s</button>";
    }
}
