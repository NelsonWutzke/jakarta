package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.entity.Factura;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesOneToManyBidireccionalFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class,1L);

            Factura f1 = new Factura("compra de supermercado", 2000L);
            Factura f2 = new Factura("compra de tecnologia", 5000L);
            cliente.addFactura(f1)
                    .addFactura(f2);

            //em.merge(cliente);

            em.getTransaction().commit();
            System.out.println(cliente);

            em.getTransaction().begin();
            //Factura f3 = em.find(Factura.class, 1L);//opcion 1
            Factura f3 = new Factura("compra de supermercado", 2000L); //opcion 2 implementando metodo equals
            f3.setId(1L);
            cliente.removeFactura(f3);
            em.getTransaction().commit();
            System.out.println(cliente);


        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
