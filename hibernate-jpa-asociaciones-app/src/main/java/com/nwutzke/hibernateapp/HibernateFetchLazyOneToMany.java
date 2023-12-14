package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFetchLazyOneToMany {

    /**
     * IMPORTANTE:
     * Relaciones terminadas en Many por defecto son LAZY.
     * Relaciones terminadas en One por defecto son EAGER.
     */
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.find(Cliente.class, 1L);
        // se van a ver 2 consultas porque esta como LAZY
        System.out.println(cliente.getNombre() + ", direcciones: " + cliente.getDirecciones());




        em.close();
    }
}
