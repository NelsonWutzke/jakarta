package com.nwutzke.apiservlet.webapp.bd.services;

import com.nwutzke.apiservlet.webapp.bd.configs.Service;
import com.nwutzke.apiservlet.webapp.bd.interceptors.TransactionalJpa;
import com.nwutzke.apiservlet.webapp.bd.models.entities.Usuario;
import com.nwutzke.apiservlet.webapp.bd.repositories.RepositoryJpa;
import com.nwutzke.apiservlet.webapp.bd.repositories.UsuarioRepository;
import jakarta.inject.Inject;


import java.util.Optional;
//@ApplicationScoped
@Service
@TransactionalJpa
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;
    @Inject
    public UsuarioServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username))
                    .filter(u->u.getPassword().equals(password));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
