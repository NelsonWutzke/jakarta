package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("========= consultar todos =============");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("========= consulta por id =============");
        //Cliente cliente = em.createQuery("select c from Cliente c where c.id=?1")
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id",1L)
                .getSingleResult();
        System.out.println(cliente);


        System.out.println("========= Consulta solo el nombre por el id ==========");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id=:id",String.class)
                .setParameter("id",2L)
                .getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("========= Consulta por campos personalizados ==========");
        Object[] objectoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id",Object[].class)
                .setParameter("id",1L)
                .getSingleResult();
        Long id = (Long)objectoCliente[0];
        String nombre = (String) objectoCliente[1];
        String apellido = (String) objectoCliente[2];
        System.out.println("id= "+id+" nombre= "+nombre+" apellido= "+apellido);

        System.out.println("========= Consulta por campos personalizados lista ==========");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c",Object[].class)
                .getResultList();

        /*for (Object[] reg : registros) {
            id = (Long)reg[0];
            nombre = (String) reg[1];
            apellido = (String) reg[2];
            System.out.println("id= "+id+" nombre= "+nombre+" apellido= "+apellido);
        }*/
        registros.forEach(reg->{
            Long idCli = (Long)reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println("id= "+idCli+" nombre= "+nombreCli+" apellido= "+apellidoCli);
        });

        em.close();

    }
}
