package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

       /* // hace una consulta por cada cliente para ir a buscar el detalle porque es EAGER por defecto
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class)
                        .getResultList();
        // y aca solo ejecuta la consulta de direcciones cada vez que se hace el getDirecciones
        clientes.forEach(c-> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));*/


        // hace una consulta por cada cliente para ir a buscar el detalle porque es EAGER por defecto
        /*List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left join fetch c.direcciones ", Cliente.class)
                .getResultList();
        // y aca ya tengo las direcciones y no hace consultas intermedias para direcciones pero sigue habiendo consultas intermedias para detalle
        clientes.forEach(c-> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones() + ", detalles: " + c.getDetalle()));
        */

        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left join fetch c.direcciones left join fetch c.detalle ", Cliente.class)
                .getResultList();
        // 1 sola consulta
        clientes.forEach(c-> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones() + ", detalles: " + c.getDetalle()));

        em.close();
    }
}
