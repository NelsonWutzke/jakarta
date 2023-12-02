package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class HibernateResultListWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1",Cliente.class);
        System.out.println("Ingrese una forma de pago: ");
        String pago = s.next();
        //query.setParameter(1,"debito");
        query.setParameter(1,pago);
        //si o si debe devolver un solo registro de lo contrario lanza excepcion
        List<Cliente> c = query.getResultList();
        System.out.println(c);

        em.close();
    }
}
