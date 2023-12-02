package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Scanner;

public class HibernateSingleResultWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1",Cliente.class);
        System.out.println("Ingrese una forma de pago: ");
        String pago = s.next();
        //query.setParameter(1,"debito");
        query.setParameter(1,pago);
        //query.setMaxResults(1);
        //si o si debe devolver un solo registro de lo contrario lanza excepcion
        Cliente c = (Cliente) query.getSingleResult();
        System.out.println(c);

        em.close();
    }
}
