package com.nwutzke.hibernateapp;

import com.nwutzke.hibernateapp.entity.Alumno;
import com.nwutzke.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateFetchManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();


        /*List<Alumno> alumnos = em.createQuery("select a from Alumno a", Alumno.class).getResultList();
        //hace consultas intermedias para buscar los cursos
        alumnos.forEach(alumno -> System.out.println(alumno.getNombre() + ", cursos: " + alumno.getCursos()));
        */

        List<Alumno> alumnos = em.createQuery("select distinct a from Alumno a left join fetch a.cursos", Alumno.class).getResultList();
        //NO hace consultas intermedias
        alumnos.forEach(alumno -> System.out.println(alumno.getNombre() + ", cursos: " + alumno.getCursos()));

        em.close();
    }
}
