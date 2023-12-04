package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();


        CriteriaBuilder criteria = em.getCriteriaBuilder();

        System.out.println("=========== listar todo ============");

        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        query.select(from);

        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("=========== listar where equals ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));
        clientes = em.createQuery(query).setParameter("nombre", "Andres").getResultList();
        clientes.forEach(System.out::println);


        System.out.println("=========== usando where like para buscar clientes por nombre ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.like(from.get("nombre"), "%lu%"));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("=========== usando where like para buscar clientes por nombre con PARAMETER EXPRESSION ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");
        query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));
        clientes = em.createQuery(query).setParameter("nombreParam", "%lu%").getResultList();
        clientes.forEach(System.out::println);


        System.out.println("=========== usando where between para rangos ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.between(from.get("id"), 2L, 6L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);


        System.out.println("=========== usando where in  ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<List> listParam = criteria.parameter(List.class, "listParam");
        //query.select(from).where(from.get("nombre").in("Andres","Roberto","Lou"));
        query.select(from).where(from.get("nombre").in(listParam));
        //clientes = em.createQuery(query).getResultList();
        clientes = em.createQuery(query)
                .setParameter("listParam", Arrays.asList("Andres", "Roberto", "Lou"))
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("=========== filtrando usando predicados mayor que o mayor igual que  ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.ge(from.get("id"), 3L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("=========== consulta con los predicados conjuncion and y disyuncion or ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago = criteria.equal(from.get("formaPago"), "debito");
        Predicate p3 = criteria.equal(from.get("id"), 4L);
        query.select(from).where(criteria.and(p3, criteria.or(porNombre, porFormaPago)));
        //query.select(from).where(criteria.and(porNombre,porFormaPago));
        //query.select(from).where(criteria.or(porNombre,porFormaPago));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("=========== consulta con order by asc desc ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("=========== consulta por id ============");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");
        query.select(from).where(criteria.equal(from.get("id"), idParam));
        Cliente cliente = em.createQuery(query)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(cliente);


        System.out.println("=========== consulta solo el nombre de los clientes ============");

        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(from.get("nombre"));
        List<String> nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("=========== consulta solo el nombre de los clientes UNICOS con DISTINCT ============");

        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(criteria.upper(from.get("nombre"))).distinct(true);
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("=========== consulta por nombre y apellidos concatenados ============");

        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido")));
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("=========== consulta por nombre y apellidos concatenados MAYUSCULA ============");

        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(criteria.upper(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("=========== consulta de campos personalizados del entity cliente ============");
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));
        List<Object[]> registros = em.createQuery(queryObject).getResultList();
        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id=" + id + " nombre=" + nombre + " apellido=" + apellido);
        });


        System.out.println("=========== consulta de campos personalizados del entity cliente con WHERE ============");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteria.like(from.get("nombre"), "%Ro%"));
        registros = em.createQuery(queryObject).getResultList();
        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id=" + id + " nombre=" + nombre + " apellido=" + apellido);
        });


        System.out.println("=========== consulta de campos personalizados del entity cliente con WHERE ID ============");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteria.equal(from.get("id"), 1L));
        Object[] registro = em.createQuery(queryObject).getSingleResult();

        Long id = (Long) registro[0];
        String nombre = (String) registro[1];
        String apellido = (String) registro[2];
        System.out.println("id=" + id + " nombre=" + nombre + " apellido=" + apellido);

        System.out.println("=========== contar registros de la consulta con count ============");
        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.count(from.get("id")));
        Long count = em.createQuery(queryLong).getSingleResult();
        System.out.println(count);

        System.out.println("=========== sumar datos de algun dato de la tabla ============");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.sum(from.get("id")));
        Long sum = em.createQuery(queryLong).getSingleResult();
        System.out.println(sum);

        System.out.println("=========== consulta con el maximo id  ============");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.max(from.get("id")));
        Long max = em.createQuery(queryLong).getSingleResult();
        System.out.println(max);


        System.out.println("=========== consulta con el minimo id  ============");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.min(from.get("id")));
        Long min = em.createQuery(queryLong).getSingleResult();
        System.out.println(min);

        System.out.println("=========== ejemplo varios resultados de funciones de agregacion en una sola consulta  ============");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(criteria.count(from.get("id")),
                criteria.sum(from.get("id")),
                criteria.max(from.get("id")),
                criteria.min(from.get("id")));
        registro = em.createQuery(queryObject).getSingleResult();
        count = (Long)registro[0];
        sum = (Long)registro[1];
        max = (Long)registro[2];
        min = (Long)registro[3];
        System.out.println("Count= "+count +" Sum= "+ sum +" Max= "+ max + " Min="+ min);


        em.close();
    }
}
