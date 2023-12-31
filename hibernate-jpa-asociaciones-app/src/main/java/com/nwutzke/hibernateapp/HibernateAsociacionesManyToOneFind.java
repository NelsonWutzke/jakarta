package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.entity.Factura;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class,1L);

            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);
            em.merge(factura);

            System.out.println(factura.getCliente());

            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
