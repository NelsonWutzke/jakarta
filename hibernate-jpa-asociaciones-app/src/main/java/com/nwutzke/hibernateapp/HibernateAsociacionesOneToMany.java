package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.entity.Direccion;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Catalina","Robleto");
            cliente.setFormaPago("debito");

            Direccion d1 = new Direccion("el vergel",123);
            Direccion d2 = new Direccion("salcedo",234);
            Direccion d3 = new Direccion("pinedo",543);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);
            cliente.getDirecciones().add(d3);
            em.persist(cliente);





            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
