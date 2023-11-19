package org.java.jdbc;

import org.java.jdbc.modelo.Producto;
import org.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.java.jdbc.repositorio.Repositorio;
import org.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcDelete {
    public static void main(String[] args) {


        try (Connection conn = ConexionBaseDatos.getInstance()){
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            System.out.println("============== listar ==============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============== obtener por id  ==============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============== eliminar producto ==============");
            Producto producto = new Producto();
            producto.setId(6L);
            repositorio.eliminar(producto.getId());
            System.out.println("Producto eliminado con Exito");

            System.out.println("============== listar ==============");
            repositorio.listar().forEach(System.out::println);






        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
