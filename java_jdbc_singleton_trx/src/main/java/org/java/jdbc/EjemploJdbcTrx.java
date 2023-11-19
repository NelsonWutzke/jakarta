package org.java.jdbc;

import org.java.jdbc.modelo.Categoria;
import org.java.jdbc.modelo.Producto;
import org.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.java.jdbc.repositorio.Repositorio;
import org.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcTrx {
    public static void main(String[] args) throws SQLException {


        try (Connection conn = ConexionBaseDatos.getInstance()) {

            if (conn.getAutoCommit())
                conn.setAutoCommit(false);

            try {

                Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

                System.out.println("============== listar ==============");
                repositorio.listar().forEach(System.out::println);

                System.out.println("============== obtener por id  ==============");
                System.out.println(repositorio.porId(1L));

                System.out.println("============== insertar nuevo producto ==============");
                Producto producto = new Producto();
                producto.setNombre("Teclado IBM Mecanico AZUL");
                producto.setPrecio(700);
                Categoria categoria = new Categoria();
                categoria.setId(3L);
                producto.setCategoria(categoria);
                producto.setSku("abcdef1234");
                producto.setFechaRegistro(new Date());
                repositorio.guardar(producto);
                System.out.println("Producto Guardado con Exito");

                System.out.println("============== editar producto ==============");
                producto = new Producto();
                producto.setId(5L);
                producto.setNombre("PenDrive Corsair 32GB");
                producto.setPrecio(1000);
                producto.setSku("bcdefg1234");
                categoria = new Categoria();
                categoria.setId(2L);
                producto.setCategoria(categoria);
                repositorio.guardar(producto);
                System.out.println("Producto Editado con Exito");

                System.out.println("============== listar ==============");
                repositorio.listar().forEach(System.out::println);

                conn.commit();
            } catch (SQLException exception) {
                conn.rollback();
                exception.printStackTrace();
            }

        }
    }
}
