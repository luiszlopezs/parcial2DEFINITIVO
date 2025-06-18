/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.modelo.Carta;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase encargada de gestionar la lógica del tablero de cartas del juego Concéntrese.
 * <p>
 * Controla la generación y organización de las cartas en una matriz de 5x8,
 * y mantiene la referencia al controlador principal del servidor para coordinar acciones conjuntas.
 * </p>
 * 
 * @author hailen
 */
public class ControlTablero {

    /**
     * Matriz de cartas que representa el tablero del juego.
     * Contiene las 20 parejas distribuidas aleatoriamente.
     */
    private Carta[][] matrizCartas;

    /**
     * Referencia al controlador principal del servidor.
     * Se utiliza para comunicarse y coordinar acciones entre componentes.
     */
    private ControlPrincipalServidor cPrinc;

    /**
     * Constructor que inicializa el controlador del tablero con la referencia al controlador principal.
     *
     * @param cPrinc Objeto {@code ControlPrincipalServidor} que orquesta el funcionamiento general del servidor.
     */
    public ControlTablero(ControlPrincipalServidor cPrinc) {
        this.cPrinc = cPrinc;
    }
 

        /**
     * Genera aleatoriamente las cartas del tablero y las asigna a una matriz de 5 filas por 8 columnas.
     * <p>
     * Se crean 20 pares de cartas con el mismo ID e imagen, se almacenan en una lista,
     * se desordenan con {@code Collections.shuffle()}, y se asignan secuencialmente
     * a la matriz {@code matrizCartas}, que representa el tablero del juego.
     * </p>
     */
    public void generarCartas() {
        matrizCartas = null;
        ArrayList<Carta> cartaLista = new ArrayList<>();
        int filas = 5;
        int columnas = 8;
        int total = filas * columnas;

        for (int i = 1; i <= total / 2; i++) {
            // Se crean dos objetos distintos con el mismo valor
            Carta carta1 = new Carta(i, "/cartas/carta" + i + ".jpg");
            Carta carta2 = new Carta(i, "/cartas/carta" + i + ".jpg");
            cartaLista.add(carta1);
            cartaLista.add(carta2);
        }

        Collections.shuffle(cartaLista);

        matrizCartas = new Carta[filas][columnas];
        int idx = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizCartas[i][j] = cartaLista.get(idx);
                idx++;
            }
        }
        
        System.out.println(matrizCartas);
    }

        /**
     * Obtiene la matriz de cartas generada para el tablero del juego.
     *
     * @return Matriz de objetos {@code Carta} que representa el estado actual del tablero.
     */
    public Carta[][] getMatrizCartas() {
        return matrizCartas;
    }

    /**
     * Establece la matriz de cartas que representa el tablero del juego.
     *
     * @param matrizCartas Matriz de objetos {@code Carta} que será asignada como tablero actual.
     */
    public void setMatrizCartas(Carta[][] matrizCartas) {
        this.matrizCartas = matrizCartas;
    }
   
    
}
