package com.nwutzke.apiservlet.webapp.bd.filters;

import com.nwutzke.apiservlet.webapp.bd.services.LoginService;
import com.nwutzke.apiservlet.webapp.bd.services.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

//@WebFilter({"/ver-carro","/agregar-carro","/actualizar-carro"})
//@WebFilter({"/*-carro"}) solo es compatible cuando hay un -
@WebFilter({"/carro/*","/productos/form/*","/productos/eliminar/*"})
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) servletRequest);
        if (username.isPresent()){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos no estas autorizado para ingresar a esta pagina");
        }
    }
}
