package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFetchLazyOneToManyJoinFetch {

    /**
     * IMPORTANTE:
     * Relaciones terminadas en Many por defecto son LAZY.
     * Relaciones terminadas en One por defecto son EAGER.
     */
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        // si se deja asi hace 3 consultas
        //Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id",Cliente.class)

        // hace 2 consultas
        //left join trae clientes con o sin direcciones
        //inner join solo trae a los que tienen direcciones
        //Cliente cliente = em.createQuery("select c from Cliente c left join fetch c.direcciones where c.id=:id",Cliente.class)

        // 1 sola consulta
        Cliente cliente = em.createQuery("select c from Cliente c " +
                        " left join fetch c.direcciones " +
                        " left join fetch c.detalle " +
                        "where c.id=:id",Cliente.class)
                .setParameter("id",1L)
                .getSingleResult();

        System.out.println(cliente.getNombre() + ", direcciones: " + cliente.getDirecciones() );
        System.out.println(cliente.getNombre() + ", detalle: " + cliente.getDetalle() );




        em.close();
    }
}
