package com.example.responsivegestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {

    public static ArrayList<Lista_entrada> ENT_LISTA = new ArrayList<Lista_entrada>();

    public static Map<String, Lista_entrada> ENT_LISTA_HASHMAP = new HashMap<String, Lista_entrada>();

    public static class Lista_entrada{
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

    static{
        ponerEntrada(new Lista_entrada("0", R.drawable.witcher, "The witcher 3", "RPG épico de mundo abierto"));
        ponerEntrada(new Lista_entrada("1", R.drawable.smash, "Smash Ultimate", "Juego de peleas en plataforma"));
        ponerEntrada(new Lista_entrada("2", R.drawable.sparking, "Sparking Zero", "Juego de peleas de Dragon Ball"));
        ponerEntrada(new Lista_entrada("3", R.drawable.cyberpunk, "Cyberpunk 2077", "Juego de rol de acción y aventura"));
    }

    private static void ponerEntrada(Lista_entrada entrada){
        ENT_LISTA.add(entrada);
        ENT_LISTA_HASHMAP.put(entrada.id, entrada);
    }
}