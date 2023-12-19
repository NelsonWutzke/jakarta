package org.wutzke.webapp.ejb.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.wutzke.webapp.ejb.service.ServiceEjb;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/index")
public class EjemploServlet extends HttpServlet {
    /*@Inject // si iyectamos con EJB el contexto no se aplica, en su lugar se usa Inject
    private ServiceEjb service;

    @Inject
    private ServiceEjb service2;*/
    ServiceEjb service = null;
    ServiceEjb service2 = null;



    {
        try {
            InitialContext ctx = new InitialContext();
            service = (ServiceEjb) ctx.lookup("java:global/webapp-ejb/ServiceEjb!org.wutzke.webapp.ejb.service.ServiceEjb");
            service2 = (ServiceEjb) ctx.lookup("java:global/webapp-ejb/ServiceEjb!org.wutzke.webapp.ejb.service.ServiceEjb");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service si es igual a service2 = " + service.equals(service2));
        req.setAttribute("saludo",service.saludar("Nelson"));
        req.setAttribute("saludo2",service2.saludar("John"));
        getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
