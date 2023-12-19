package org.wutzke.webapp.ejb.service;

import jakarta.ejb.Stateless;

@Stateless
public class ServiceEjb {

    public String saludar(String nombre){
        System.out.println("Imprimiendo dentro del ejb con instancia: " + this);
        return "Hola que tal " + nombre;
    }

}
