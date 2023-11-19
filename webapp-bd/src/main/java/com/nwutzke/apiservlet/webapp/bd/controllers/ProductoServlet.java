package com.nwutzke.apiservlet.webapp.bd.controllers;

import com.nwutzke.apiservlet.webapp.bd.models.Producto;
import com.nwutzke.apiservlet.webapp.bd.services.LoginService;
import com.nwutzke.apiservlet.webapp.bd.services.LoginServiceSessionImpl;
import com.nwutzke.apiservlet.webapp.bd.services.ProductoService;
import com.nwutzke.apiservlet.webapp.bd.services.ProductoServiceJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        req.setAttribute("productos",productos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", req.getAttribute("title")+": Listado de Productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
