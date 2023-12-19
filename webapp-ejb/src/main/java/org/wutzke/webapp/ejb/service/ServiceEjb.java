package org.wutzke.webapp.ejb.service;

import jakarta.ejb.Stateless;

//@RequestScoped
@Stateless
public class ServiceEjb implements ServiceEjbLocal {

    private int contador;

    public String saludar(String nombre){
        System.out.println("Imprimiendo dentro del ejb con instancia: " + this);
        contador++;
        System.out.println("valor del contador en metodo saludar " + contador);
        return "Hola que tal " + nombre;
    }

}
