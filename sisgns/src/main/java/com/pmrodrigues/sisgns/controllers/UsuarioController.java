package com.pmrodrigues.sisgns.controllers;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.ValidationMessage;
import com.pmrodrigues.sisgns.Constante;
import com.pmrodrigues.sisgns.exceptions.PasswordNotChangedException;
import com.pmrodrigues.sisgns.models.security.Usuario;
import com.pmrodrigues.sisgns.repositories.PerfilRepository;
import com.pmrodrigues.sisgns.repositories.UsuarioRepository;
import com.pmrodrigues.vraptor.crud.annotations.Before;
import com.pmrodrigues.vraptor.crud.annotations.CRUD;
import com.pmrodrigues.vraptor.crud.annotations.Tiles;
import com.pmrodrigues.vraptor.crud.controllers.AbstractCRUDController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.apache.commons.validator.GenericValidator.isBlankOrNull;

/**
 * Created by Marceloo on 11/12/2015.
 */
@CRUD
@Resource
public class UsuarioController extends AbstractCRUDController<Usuario> {
    private final PerfilRepository perfilRepository;

    public UsuarioController(final UsuarioRepository repository, final PerfilRepository perfilRepository,
                             final Result result, final Validator validator) {
        super(repository, result, validator);
        this.perfilRepository = perfilRepository;
    }

    @Before
    public void montaTela() {

        this.getResult().include(Constante.PERFIS, perfilRepository.all().getConsulta());

    }

    @Post
    @Path("/usuario/trocasenha.do")
    public void trocarSenha(final String senhaAntiga, final String novaSenha, final String confirmacaoDaSenha) {

        final Authentication userAuthenticated = SecurityContextHolder.getContext().getAuthentication();
        final Usuario usuarioLogado = ((UsuarioRepository) this.getRepository()).findByEmail((String) userAuthenticated.getPrincipal());
        this.getValidator().onErrorForwardTo(this.getClass()).montaTelaDeTrocaDeSenha();
        try {

            if (isBlankOrNull(senhaAntiga)) {
                this.getValidator().add(new ValidationMessage("Senha antiga é obrigatória", "senhaAntiga"));
            }

            if (isBlankOrNull(novaSenha)) {
                this.getValidator().add(new ValidationMessage("A nova senha é obrigatória", "novaSenha"));
            }

            if (isBlankOrNull(confirmacaoDaSenha)) {
                this.getValidator().add(new ValidationMessage("A confirmação da nova senha é obrigatória", "confirmacaoDaSenha"));
            }

            usuarioLogado.trocarSenha(senhaAntiga, novaSenha, confirmacaoDaSenha);
            this.getRepository().set(usuarioLogado);
            this.getResult().include(com.pmrodrigues.vraptor.crud.Constante.SUCESSO, "Senha trocada com sucesso");
            this.getResult().forwardTo(this.getClass()).montaTelaDeTrocaDeSenha();
        } catch (PasswordNotChangedException e) {
            this.getValidator().add(new ValidationMessage(e.getMessage(), e.getMessage()));

        }


    }

    @Get
    @Path("/usuario/trocasenha.do")
    @Tiles(definition = "trocasenha-definition")
    public void montaTelaDeTrocaDeSenha() {
    }
}
