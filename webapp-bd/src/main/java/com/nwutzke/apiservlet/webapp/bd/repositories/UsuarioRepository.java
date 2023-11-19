package com.nwutzke.apiservlet.webapp.bd.repositories;

import com.nwutzke.apiservlet.webapp.bd.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends Repository<Usuario>{

    Usuario porUsername(String username)throws SQLException;
}
