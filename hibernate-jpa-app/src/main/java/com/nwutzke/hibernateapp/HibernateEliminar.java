package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Cliente;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese el id del cliente a eliminar: ");
        Long id = s.nextLong();

        try {
            Cliente cliente = em.find(Cliente.class, id);

            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();


        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
