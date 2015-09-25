package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import com.pmrodrigues.vraptor.crud.annotations.Tiles;


/**
 * Created by Marceloo on 24/09/2015.
 */
@Resource
public class IndexController {

    @Tiles(definition = "index-template")
    @Path("/index.do")
    public void index() {
        //NOPMD
    }
}
