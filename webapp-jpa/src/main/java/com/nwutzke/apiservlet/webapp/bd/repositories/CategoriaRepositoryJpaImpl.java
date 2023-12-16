package com.nwutzke.apiservlet.webapp.bd.repositories;

import com.nwutzke.apiservlet.webapp.bd.configs.Repository;
import com.nwutzke.apiservlet.webapp.bd.models.entities.Categoria;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
@RepositoryJpa
@Repository
public class CategoriaRepositoryJpaImpl implements CrudRepository<Categoria>{

    @Inject
    private EntityManager em;

    @Override
    public List<Categoria> listar() throws Exception {
        return em.createQuery("from Categoria",Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(Long id) throws Exception {
        return em.find(Categoria.class,id);
    }

    @Override
    public void guardar(Categoria categoria) throws Exception {
        if (categoria.getId()!= null && categoria.getId()>0){
            em.merge(categoria);
        }else{
            em.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }
}
