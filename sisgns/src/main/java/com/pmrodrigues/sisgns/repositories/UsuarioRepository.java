package com.pmrodrigues.sisgns.repositories;

import com.pmrodrigues.persistence.daos.Repository;
import com.pmrodrigues.sisgns.models.security.Usuario;

public interface UsuarioRepository extends Repository<Usuario> {

    Usuario findByEmail(String email);


}