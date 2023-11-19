package com.nwutzke.apiservlet.webapp.headers.services;

import com.nwutzke.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L,"notebook","computacion",175000),
                new Producto(2L,"mesa escritorio","oficina",10000),
                new Producto(3L,"teclado mecanico","computacion",4000)
        );
    }
}
