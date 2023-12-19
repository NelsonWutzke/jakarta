package org.wutzke.webapp.ejb.service;

import jakarta.ejb.Stateful; //no es buena practica inyectar Stateful en un Servlet
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Stateful
public class ServiceEjb {

    private int contador;

    public String saludar(String nombre){
        System.out.println("Imprimiendo dentro del ejb con instancia: " + this);
        contador++;
        System.out.println("valor del contador en metodo saludar " + contador);
        return "Hola que tal " + nombre;
    }

}
