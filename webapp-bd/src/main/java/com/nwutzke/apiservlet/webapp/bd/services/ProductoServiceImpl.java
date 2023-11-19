package com.nwutzke.apiservlet.webapp.bd.services;

import com.nwutzke.apiservlet.webapp.bd.models.Categoria;
import com.nwutzke.apiservlet.webapp.bd.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L,"notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",10000),
                new Producto(3L,"teclado mecanico","computacion",4000)
        );
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p-> p.getId().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
