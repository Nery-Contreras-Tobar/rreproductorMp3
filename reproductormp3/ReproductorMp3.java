/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.reproductormp3;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author NERY
 */
public class ReproductorMp3 {
    
    private Cancion[] canciones;
    
    public ReproductorMp3(String rutaCarpeta) {
        // Obtener archivos de música en la carpeta
        File dir = new File(rutaCarpeta);
        File[] archivos = dir.listFiles((dir1, name) -> name.endsWith(".mp3"));
        
        // Crear arreglo de canciones y ordenarlos alfabéticamente
        canciones = new Cancion[archivos.length];
        for (int i = 0; i < archivos.length; i++) {
            canciones[i] = new Cancion(archivos[i].getName());
        }
        Arrays.sort(canciones);
        
    }
    
    public void reproducir() {
        if (canciones.length == 0) {
            System.out.println("No hay canciones para reproducir.");
            return;
        }
        
        // Crear estructura de cola y agregar canciones
        Cola cola = new Cola();
        for (Cancion cancion : canciones) {
            cola.encolar(cancion);
        }
        
        // Reproducir canciones en orden de la cola
        while (!cola.estaVacia()) {
            Cancion cancion = (Cancion) cola.desencolar();
            System.out.println("Reproduciendo: " + cancion.getNombre());
        }
    }
    
    public static void main(String[] args) {
        ReproductorMp3 reproductor = new ReproductorMp3("C:\\Users\\NERY\\Desktop\\musica");
        reproductor.reproducir();
    }
}

class Cancion implements Comparable<Cancion> {
    private String nombre;
    
    public Cancion(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public int compareTo(Cancion otra) {
        return this.nombre.compareTo(otra.nombre);
    }
}

class Cola {
    private Nodo frente;
    private Nodo fondo;
    
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (fondo == null) {
            frente = nuevo;
            fondo = nuevo;
        } else {
            fondo.setSiguiente(nuevo);
            fondo = nuevo;
        }
    }
    
    public Object desencolar() {
        if (frente == null) {
            return null;
        } else {
            Object dato = frente.getDato();
            frente = frente.getSiguiente();
            if (frente == null) {
                fondo = null;
            }
            return dato;
        }
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}

class Nodo {
    private Object dato;
    private Nodo siguiente;
    
    public Nodo(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Object getDato() {
        return dato;
    }
    
    public void setDato(Object dato) {
        this.dato = dato;
    }
    
    public Nodo getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    } 
}