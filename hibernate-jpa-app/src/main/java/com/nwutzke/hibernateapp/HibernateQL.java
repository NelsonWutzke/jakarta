package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.dominio.ClienteDto;
import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

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

        System.out.println("========= Consulta por cliente y forma de pago  ==========");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                        .getResultList();

        registros.forEach(reg->{
            Cliente c= (Cliente) reg[0];
            String formaPago= (String) reg[1];
            System.out.println("formaPago= "+formaPago + " , " + c);
        });


        System.out.println("========= Consulta que puebla y devuelve objeto entity de una clase personalizada  ==========");
        clientes = em.createQuery("select new Cliente(c.nombre,c.apellido) from Cliente c",Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= Consulta que puebla y devuelve objeto otro(dto) de una clase personalizada  ==========");
        List<ClienteDto> clientesdto = em.createQuery("select new com.nwutzke.hibernateapp.dominio.ClienteDto(c.nombre,c.apellido) from Cliente c", ClienteDto.class).getResultList();
        clientesdto.forEach(System.out::println);


        System.out.println("========= Consulta con nombres de clientes  ==========");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c",String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========= Consulta con nombres UNICOS de clientes  ==========");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c",String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========= Consulta con forma de pagos UNICAS  ==========");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c",String.class)
                .getResultList();
        formasPago.forEach(System.out::println);

        System.out.println("========= Consulta con numero de formas de pagos UNICAS  ==========");
        Long totalFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c",Long.class)
                .getSingleResult();
        System.out.println(totalFormasPago);



        em.close();

    }
}
