package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        List clientes = em.createQuery("select c from Cliente c").getResultList();
        clientes.forEach(System.out::println);
        em.close();
    }
}