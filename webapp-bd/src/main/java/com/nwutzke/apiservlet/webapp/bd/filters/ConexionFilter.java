package com.nwutzke.apiservlet.webapp.bd.filters;

import com.nwutzke.apiservlet.webapp.bd.services.ServiceJdbcException;
import com.nwutzke.apiservlet.webapp.bd.util.ConexionBaseDatosDS;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection conn = ConexionBaseDatosDS.getConnection()) {
            if(conn.getAutoCommit())
                conn.setAutoCommit(false);

            try {
                // aca seteamos y queda disponible para el request de ahora en adelante
                servletRequest.setAttribute("conn",conn);
                // resultado de una peticion, devolvemos las llamadas a los doPost o doGet
                filterChain.doFilter(servletRequest,servletResponse);
                conn.commit();
            } catch (SQLException | ServiceJdbcException e) {
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
