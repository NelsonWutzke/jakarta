package com.nwutzke.apiservlet.webapp.bd.configs;

import com.nwutzke.apiservlet.webapp.bd.util.JpaUtil;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

//@Dependent
@ApplicationScoped
public class ProducerResources {
    @Inject
    private  Logger log;
    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;
    @Produces
    @RequestScoped
    //@Named("conn")
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        //Context initContext = new InitialContext();
        //Context envContext = (Context) initContext.lookup("java:/comp/env");
        //DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        log.info("cerrando la conexion a la bbdd mysql");
    }

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return JpaUtil.getEntityManager();
    }

    public void close (@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            log.info("cerrando la conexion del EntityManager");
        }
    }

}
