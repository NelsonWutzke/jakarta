package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.services.ClienteService;
import com.nwutzke.hibernateapp.services.ClienteServiceImpl;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("+++++++++ listar +++++++++");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("+++++++++ Obtener por id +++++++++++");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        System.out.println("+++++++++ Insertar nuevo cliente +++++++++++");
        Cliente cliente = new Cliente();
        cliente.setNombre("Matias");
        cliente.setApellido("Rebrec");
        cliente.setFormaPago("paypal");
        service.guardar(cliente);
        System.out.println("cliente guardado con exito");

        service.listar().forEach(System.out::println);

        System.out.println("+++++++++ Editar cliente +++++++++++");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c-> {
            c.setFormaPago("efectivo");
            service.guardar(c);
            System.out.println("Cliente editado con exito");
            service.listar().forEach(System.out::println);
        });

        System.out.println("+++++++++ Eliminar cliente +++++++++++");

        id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c-> {
            service.eliminar(c.getId());
            System.out.println("Cliente eliminado con exito");
            service.listar().forEach(System.out::println);
        });


        em.close();
    }
}
