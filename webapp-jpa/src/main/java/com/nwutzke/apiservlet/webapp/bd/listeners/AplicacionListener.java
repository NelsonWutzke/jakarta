package com.nwutzke.apiservlet.webapp.bd.listeners;

import com.nwutzke.apiservlet.webapp.bd.models.Carro;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la aplicacion");
        servletContext = sce.getServletContext();
        sce.getServletContext().log("Contexto de la aplicacion " + servletContext.getContextPath());
        servletContext.setAttribute("mensaje", "algun valor global de la app");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la aplicacion" + servletContext.getContextPath());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request");
        sre.getServletRequest().setAttribute("mensaje", "guardando algun valor para el request");
        sre.getServletRequest().setAttribute("title", "Catalogo Servlet");
    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo el request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la session http");
        //Carro carro = new Carro();
        //HttpSession session = se.getSession();
        //session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruyendo la session http");
    }
}
