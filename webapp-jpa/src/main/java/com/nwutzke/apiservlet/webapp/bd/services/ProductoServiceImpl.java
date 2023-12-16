package com.nwutzke.apiservlet.webapp.bd.services;

import com.nwutzke.apiservlet.webapp.bd.configs.ProductoServicePrincipal;
import com.nwutzke.apiservlet.webapp.bd.configs.Service;
import com.nwutzke.apiservlet.webapp.bd.interceptors.TransactionalJpa;
import com.nwutzke.apiservlet.webapp.bd.models.entities.Categoria;
import com.nwutzke.apiservlet.webapp.bd.models.entities.Producto;
import com.nwutzke.apiservlet.webapp.bd.repositories.CrudRepository;
import com.nwutzke.apiservlet.webapp.bd.repositories.RepositoryJpa;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
@Service
@ProductoServicePrincipal
@TransactionalJpa
public class ProductoServiceImpl implements ProductoService{

    @Inject
    @RepositoryJpa
    private CrudRepository<Producto> repositoryJdbc;
    @Inject
    @RepositoryJpa
    private CrudRepository<Categoria> repositoryCategoriaJdbc;


    @Override
    public List<Producto> listar() {

        try {
            return repositoryJdbc.listar();
        } catch (Exception e) {
           throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }

    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }
}
