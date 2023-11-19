package com.nwutzke.apiservlet.webapp.bd.controllers;

import com.nwutzke.apiservlet.webapp.bd.models.ItemCarro;
import com.nwutzke.apiservlet.webapp.bd.models.Producto;
import com.nwutzke.apiservlet.webapp.bd.services.ProductoService;
import com.nwutzke.apiservlet.webapp.bd.services.ProductoServiceJdbcImpl;
import com.nwutzke.apiservlet.webapp.bd.models.Carro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);

        Optional<Producto> producto = service.porId(id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            //Se lleva al listener
            /*if (session.getAttribute("carro")== null){
                carro = new Carro();
                session.setAttribute("carro", carro);
            }else {
                carro = (Carro) session.getAttribute("carro");
            }*/
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
