package org.java.jdbc;

import org.java.jdbc.modelo.Categoria;
import org.java.jdbc.modelo.Producto;
import org.java.jdbc.servicio.CatalogoServicio;
import org.java.jdbc.servicio.Servicio;

import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) throws SQLException {

        Servicio servicio = new CatalogoServicio();


        System.out.println("============== listar ==============");
        servicio.listar().forEach(System.out::println);

        Categoria categoria = new Categoria();
        categoria.setNombre("Iluminacion Parque");

        Producto producto = new Producto();
        producto.setNombre("Lampara LED exterior");
        producto.setPrecio(1990);
        producto.setFechaRegistro(new Date());
        producto.setSku("123456cdef");
        servicio.guardarProductoConCategoria(producto,categoria);
        System.out.println("Producto Guardado con Exito: " + producto.getId());

        System.out.println("============== listar ==============");
        servicio.listar().forEach(System.out::println);


    }
}
