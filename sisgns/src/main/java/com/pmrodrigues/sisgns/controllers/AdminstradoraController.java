package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import com.pmrodrigues.endereco.models.Telefone;
import com.pmrodrigues.sisgns.models.Administradora;
import com.pmrodrigues.sisgns.repositories.AdministradoraRepository;
import com.pmrodrigues.vraptor.crud.Constante;
import com.pmrodrigues.vraptor.crud.annotations.Tiles;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marceloo on 24/09/2015.
 */
@Resource
public class AdminstradoraController {

    private final AdministradoraRepository repository;
    private final Result result;
    private final Validator validator;

    public AdminstradoraController(final AdministradoraRepository repository, final Result result, final Validator validator) {
        this.repository = repository;
        this.result = result;
        this.validator = validator;
    }

    @Get
    @Path("/administradora/novo.do")
    @Tiles(definition = "formulario-template")
    public void formulario() {
        try {

            final Administradora e = new Administradora();
            final List<Field> fields = Arrays.asList(e.getClass().getDeclaredFields());
            for (final Field field : fields) {
                if (field.getType().isAnnotationPresent(Entity.class)) {
                    field.setAccessible(true);
                    final Object value = field.getType().newInstance();
                    field.set(e, value);
                }
            }
            result.include("telefone1", new Telefone());
            result.include("telefone2", new Telefone());
            result.include("telefone3", new Telefone());
            result.include(Constante.OBJECT, e);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Post
    @Path("/administradora/salvar.do")
    public void salvar(final Administradora object, final Telefone telefone1, final Telefone telefone2, final Telefone telefone3) {

        result.forwardTo(this.getClass()).index();
    }

    @Get
    @Path("/administradora/index.do")
    @Tiles(definition = "index-template")
    public void index() {

    }
}
