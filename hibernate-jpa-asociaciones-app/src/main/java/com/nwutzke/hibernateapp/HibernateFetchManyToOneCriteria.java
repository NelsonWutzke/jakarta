package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.entity.Factura;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Factura> query = cb.createQuery(Factura.class);
        Root<Factura> factura = query.from(Factura.class);
        Join<Factura, Cliente> cliente = (Join) factura.fetch("cliente", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);
        //query.select(factura);
        query.select(factura).where(cb.equal(cliente.get("id"),1L));
        List<Factura> facturas = em.createQuery(query).getResultList();

        //OJO!!!!! el ToString de cliente va a llamar a realizar consultas de factuas, detalles y direcciones
        //facturas.forEach(f -> System.out.println(f.getDescripcion() + ", cliente: " + f.getCliente()));

        facturas.forEach(f -> System.out.println(f.getDescripcion() + ", cliente: " + f.getCliente().getNombre()));


        em.close();
    }
}
