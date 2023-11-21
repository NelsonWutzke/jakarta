package com.nwutzke.apiservlet.webapp.bd.services;

import com.nwutzke.apiservlet.webapp.bd.configs.ProductoServicePrincipal;
import com.nwutzke.apiservlet.webapp.bd.configs.Service;
import com.nwutzke.apiservlet.webapp.bd.interceptors.Logging;
import com.nwutzke.apiservlet.webapp.bd.models.Categoria;
import com.nwutzke.apiservlet.webapp.bd.models.Producto;
import com.nwutzke.apiservlet.webapp.bd.repositories.CrudRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Service
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService{

    @Inject
    private CrudRepository<Producto> repositoryJdbc;
    @Inject
    private CrudRepository<Categoria> repositoryCategoriaJdbc;


    @Override
    public List<Producto> listar() {

        try {
            return repositoryJdbc.listar();
        } catch (SQLException e) {
           throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }

    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
