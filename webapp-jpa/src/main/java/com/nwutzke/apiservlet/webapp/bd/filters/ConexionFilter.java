package com.nwutzke.apiservlet.webapp.bd.filters;

import com.nwutzke.apiservlet.webapp.bd.configs.MysqlConn;
import com.nwutzke.apiservlet.webapp.bd.services.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    //@Named("conn")

    //se cambio por TransactionalInterceptor
    /*@Inject
    @MysqlConn
    private Connection conn;*/

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //try (Connection connRequest = this.conn) { // se reemplaza por metodo close en ProducerResources
        //se cambio por TransactionalInterceptor
        /* try {
            Connection connRequest = this.conn;
            if (connRequest.getAutoCommit())
                connRequest.setAutoCommit(false);*/

            try {
                // resultado de una peticion, devolvemos las llamadas a los doPost o doGet
                filterChain.doFilter(servletRequest, servletResponse);
                //connRequest.commit();
            } catch (/*SQLException | */ServiceJdbcException e) {
                //connRequest.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
       /* } catch (SQLException e) { //se cambio por TransactionalInterceptor
            e.printStackTrace();
        }*/
    }
}
