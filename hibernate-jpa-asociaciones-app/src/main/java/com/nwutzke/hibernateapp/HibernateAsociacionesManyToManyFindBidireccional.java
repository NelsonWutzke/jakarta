package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Alumno;
import com.nwutzke.hibernateapp.entity.Curso;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesManyToManyFindBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);

            Curso curso1 = em.find(Curso.class,1L);//new Curso("Java","Andres");
            Curso curso2 = em.find(Curso.class,2L);//new Curso("Hibernate y Jpa","Andres");

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso1);

            em.getTransaction().commit();
            System.out.println(alumno1);
            System.out.println(alumno2);

            em.getTransaction().begin();

            //Curso c2 = em.find(Curso.class,3L);
            Curso c2 = new Curso("Curso NodeJS","Juan");
            c2.setId(2L);
            alumno1.removeCurso(c2);

            em.getTransaction().commit();
            System.out.println(alumno1);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
