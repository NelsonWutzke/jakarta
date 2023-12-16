package com.nwutzke.apiservlet.webapp.bd.services;

import com.nwutzke.apiservlet.webapp.bd.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password);
}
