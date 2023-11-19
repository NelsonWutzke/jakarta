package org.java.jdbc;

import org.java.jdbc.modelo.Categoria;
import org.java.jdbc.modelo.Producto;
import org.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.java.jdbc.repositorio.Repositorio;
import org.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {


        try (Connection conn = ConexionBaseDatos.getInstance()){
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("============== listar ==============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============== obtener por id  ==============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============== insertar nuevo producto ==============");
            Producto producto = new Producto();
            producto.setNombre("Teclado Green Dragon Mecanico");
            producto.setPrecio(550);
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            producto.setFechaRegistro(new Date());
            repositorio.guardar(producto);
            System.out.println("Producto Guardado con Exito");

            System.out.println("============== listar ==============");
            repositorio.listar().forEach(System.out::println);






        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
