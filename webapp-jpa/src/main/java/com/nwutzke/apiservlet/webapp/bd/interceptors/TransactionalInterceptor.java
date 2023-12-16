package com.nwutzke.apiservlet.webapp.bd.interceptors;

import com.nwutzke.apiservlet.webapp.bd.configs.MysqlConn;
import com.nwutzke.apiservlet.webapp.bd.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;
    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        if(conn.getAutoCommit())
            conn.setAutoCommit(false);

        try {
            log.info(" ------> Iniciando transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass().getName());

            Object resultado = invocation.proceed();
            conn.commit();

            log.info(" ------> Realizando commit y finalizando transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass().getName());

            return resultado;
        }catch (ServiceJdbcException e){
            conn.rollback();
            throw e;
        }
    }
}
