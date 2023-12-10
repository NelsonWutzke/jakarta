package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Alumno;
import com.nwutzke.hibernateapp.entity.Curso;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesManyToManyFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);

            Curso curso1 = em.find(Curso.class,1L);//new Curso("Java","Andres");
            Curso curso2 = em.find(Curso.class,2L);//new Curso("Hibernate y Jpa","Andres");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            em.getTransaction().commit();
            System.out.println(alumno1);
            System.out.println(alumno2);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
