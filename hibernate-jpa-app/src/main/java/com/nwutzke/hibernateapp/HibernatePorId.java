package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el id: ");
        Long id = s.nextLong();

        EntityManager em = JpaUtil.getEntityManager();
        //Query query = em.createQuery("select c from Cliente c where c.id=?1",Cliente.class);
        Cliente cliente = em.find(Cliente.class, id);
        //query.setParameter(1,"debito");
        //query.setParameter(1,pago);
        //si o si debe devolver un solo registro de lo contrario lanza excepcion
        //Cliente c = (Cliente) query.getSingleResult();
        System.out.println(cliente);
        //find funciona como un cache de primer nivel, solo hay una consulta sql
        Cliente cliente2 = em.find(Cliente.class, id);
        System.out.println(cliente2);



        em.close();
    }
}
