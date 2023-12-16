package com.nwutzke.apiservlet.webapp.bd.services;

import com.nwutzke.apiservlet.webapp.bd.models.entities.Categoria;
import com.nwutzke.apiservlet.webapp.bd.models.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);


}
