package com.example.responsivegestion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contenido {

    public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<>();
    public static Map<String, Lista_entrada> ENT_LISTA_HASHMAP = new HashMap<>();

    public static class Lista_entrada {
        public String id;
        public int idImagen;
        public String textoEncima;
        public String textoDebajo;

        public Lista_entrada(String id, int idImagen, String textoEncima, String textoDebajo) {
            this.id = id;
            this.idImagen = idImagen;
            this.textoEncima = textoEncima;
            this.textoDebajo = textoDebajo;
        }
    }

    private static void onEntrada(Lista_entrada entrada) {
        ENT_LISTA.add(entrada);
        ENT_LISTA_HASHMAP.put(entrada.id, entrada);
    }

    public static void cargarVideojuegos(List<Videojuego> videojuegos) {
        for (Videojuego videojuego : videojuegos) {
            String id = videojuego.getNombre(); // Usamos el nombre como ID único
            int idImagen = videojuego.getPortadaResId();
            String textoEncima = videojuego.getNombre();
            String textoDebajo = videojuego.getDescripcion();

            Lista_entrada entrada = new Lista_entrada(id, idImagen, textoEncima, textoDebajo);
            onEntrada(entrada);
        }
    }
}