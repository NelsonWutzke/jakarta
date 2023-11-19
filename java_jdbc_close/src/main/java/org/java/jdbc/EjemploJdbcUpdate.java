package org.java.jdbc;

import org.java.jdbc.modelo.Categoria;
import org.java.jdbc.modelo.Producto;
import org.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.java.jdbc.repositorio.Repositorio;
import org.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {



            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("============== listar ==============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============== obtener por id  ==============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============== editar producto ==============");
            Producto producto = new Producto();
            producto.setId(5L);
            producto.setNombre("PenDrive Corsair 32GB");
            producto.setPrecio(150);
            Categoria categoria = new Categoria();
            categoria.setId(2L);
            producto.setCategoria(categoria);
            repositorio.guardar(producto);
            System.out.println("Producto Editado con Exito");

            System.out.println("============== listar ==============");
            repositorio.listar().forEach(System.out::println);


    }
}
