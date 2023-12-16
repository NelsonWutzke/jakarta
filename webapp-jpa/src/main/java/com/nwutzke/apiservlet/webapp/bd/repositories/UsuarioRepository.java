package com.nwutzke.apiservlet.webapp.bd.repositories;

import com.nwutzke.apiservlet.webapp.bd.models.entities.Usuario;



public interface UsuarioRepository extends CrudRepository<Usuario> {

    Usuario porUsername(String username)throws Exception;
}
