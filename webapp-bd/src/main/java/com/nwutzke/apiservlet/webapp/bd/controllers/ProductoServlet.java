package com.nwutzke.apiservlet.webapp.bd.controllers;

import com.nwutzke.apiservlet.webapp.bd.configs.ProductoServicePrincipal;
import com.nwutzke.apiservlet.webapp.bd.models.Producto;
import com.nwutzke.apiservlet.webapp.bd.services.LoginService;
import com.nwutzke.apiservlet.webapp.bd.services.LoginServiceSessionImpl;
import com.nwutzke.apiservlet.webapp.bd.services.ProductoService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Inject
    @ProductoServicePrincipal
    private ProductoService service;
    @Inject
    private LoginService auth;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto> productos = service.listar();


        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos",productos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", req.getAttribute("title") + ": Listado de Productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
