package com.nwutzke.apiservlet.webapp.bd.interceptors;

import com.nwutzke.apiservlet.webapp.bd.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

import java.util.logging.Logger;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        try {
            log.info(" ------> Iniciando transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass().getName());
            em.getTransaction().begin();
            Object resultado = invocation.proceed();
            em.getTransaction().commit();

            log.info(" ------> Realizando commit y finalizando transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass().getName());

            return resultado;
        }catch (ServiceJdbcException e){
            em.getTransaction().rollback();
            throw e;
        }
    }
}
