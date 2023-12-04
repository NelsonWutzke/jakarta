package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.dominio.ClienteDto;
import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
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


        System.out.println("========= Consulta con nombre y apellido concatenados  ==========");
        //opcion 1
        /* nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c",String.class)
                        .getResultList();
        nombres.forEach(System.out::println);*/
        //opcion 2
        nombres = em.createQuery("select concat(c.nombre || ' ' || c.apellido) as nombreCompleto from Cliente c",String.class)
                .getResultList();
        nombres.forEach(System.out::println);


        System.out.println("========= Consulta con nombre y apellido concatenados en MAYUSCULA ==========");

        nombres = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c",String.class)
                        .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========= Consulta para buscar por nombre ==========");
        String param = "e";
        clientes = em.createQuery("select c from Cliente c where upper(c.nombre) like upper(:parametro)",Cliente.class)
                .setParameter("parametro","%"+param+"%")
                .getResultList();

        clientes.forEach(System.out::println);


        System.out.println("========= Consultas por rangos ==========");
        //clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5",Cliente.class).getResultList();
        // cuando es between sobre string el segundo parametro no se incluye, en este caso la P
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'P' ",Cliente.class).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("========= Consultas con orden ==========");
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc, c.apellido desc", Cliente.class).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("========= Consultas con total de registros de la tabla ==========");
        Long total = em.createQuery("select count(c) as total from Cliente c ",Long.class).getSingleResult();
        System.out.println(total);

        System.out.println("========= Consultas con valor minimo del id ==========");
        Long minId = em.createQuery("select min(c.id) as minimo from Cliente c ",Long.class).getSingleResult();
        System.out.println(minId);


        System.out.println("========= Consultas con valor maximo del id ==========");
        Long maxId = em.createQuery("select max(c.id) as maximo from Cliente c ",Long.class).getSingleResult();
        System.out.println(maxId);


        System.out.println("========= Consultas con nombre y su largo ==========");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c ",Object[].class).getResultList();
        registros.forEach(reg->{
            String nom = (String) reg[0];
            Integer largo= (Integer) reg[1];
            System.out.println("Nombre= "+nom + " , Largo=" + largo);
        });

        System.out.println("========= Consultas con nombre mas corto ==========");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c ",Integer.class).getSingleResult();
        System.out.println(minLargoNombre);


        System.out.println("========= Consultas con nombre mas largo ==========");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c ",Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);


        System.out.println("========= Consultas resumen funciones agregaciones count min max avg sum ==========");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                        .getSingleResult();
        Long min= (Long) estadisticas[0];
        Long max= (Long) estadisticas[1];
        Long sum= (Long) estadisticas[2];
        Long count= (Long) estadisticas[3];
        Double avg= (Double) estadisticas[4];
        System.out.println("Min= "+min + " Max="+max + " Sum=" + sum + " Count=" + count + " Avg="+avg);


        System.out.println("========= Consultas con el nombre mas corto y su largo ==========");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where " +
                        "length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre=" + nom + ", largo=" + largo);
        });


        System.out.println("========= Consultas para obtener el ultimo registro ==========");
        Cliente ultimoCliente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)",Cliente.class).getSingleResult();
        System.out.println(ultimoCliente);


        System.out.println("========= Consultas where in ==========");
        //clientes = em.createQuery("select c from Cliente c where c.id in (1,2,7)", Cliente.class).getResultList();
        clientes = em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class)
                .setParameter("ids", Arrays.asList(1L,2L,4L,40L))
                .getResultList();
        clientes.forEach(System.out::println);

        em.close();

    }
}
