/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.modelo;

/**
 * Representa una carta en el juego de "Concéntrese".
 * 
 * Cada carta contiene información sobre su posición en el tablero (fila y columna),
 * su estado (si está destapada o emparejada), su nombre, la ruta de su imagen y un ID
 * que identifica la pareja a la que pertenece.
 * 
 * Las cartas se comparan por su ID para determinar si forman pareja.
 * 
 * @author sangr
 */
public class Carta {
    
    /** Fila en la que se encuentra la carta dentro del tablero. */
    private int fila;

    /** Columna en la que se encuentra la carta dentro del tablero. */
    private int columna;

    /** Indica si la carta ya ha sido emparejada exitosamente. */
    private boolean esEmparejada = false;

    /** Indica si la carta está actualmente destapada. */
    private boolean esDestapada = false;

    /** Nombre o descripción de la carta (opcional, puede usarse para mostrar en interfaz o debug). */
    private String nombre;

    /** Ruta de la imagen asociada a la carta, usada para mostrar la imagen en pantalla. */
    private String rutaImg;

    /** Identificador único de la carta para emparejamiento (dos cartas con el mismo ID forman pareja). */
    private int id;


        /**
     * Constructor completo para crear una carta con todos sus atributos definidos.
     *
     * @param fila           Fila en la que se ubica la carta en el tablero.
     * @param columna        Columna en la que se ubica la carta en el tablero.
     * @param esEmparejada   Indica si la carta ya ha sido emparejada.
     * @param esDestapada    Indica si la carta está actualmente destapada.
     * @param nombre         Nombre o descripción de la carta.
     * @param rutaImg        Ruta del archivo de imagen que representa la carta.
     * @param id             Identificador único de la carta (para emparejamiento).
     */
    public Carta(int fila, int columna, boolean esEmparejada, boolean esDestapada, String nombre, String rutaImg, int id) {
        this.fila = fila;
        this.columna = columna;
        this.esEmparejada = esEmparejada;
        this.esDestapada = esDestapada;
        this.nombre = nombre;
        this.rutaImg = rutaImg;
        this.id = id;
    }

    /**
     * Constructor básico para crear una carta con solo ID y ruta de imagen.
     * 
     * Este constructor es útil cuando las cartas se inicializan al azar
     * y aún no se han asignado posición ni estados.
     *
     * @param id        Identificador único de la carta (para emparejamiento).
     * @param rutaImg   Ruta del archivo de imagen que representa la carta.
     */
    public Carta(int id, String rutaImg) {
        this.id = id;
        this.rutaImg = rutaImg;
    }

    
    
    
    //Getters y setters

        /**
     * Obtiene la fila en la que se encuentra la carta en el tablero.
     *
     * @return Número de fila.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Establece la fila en la que se ubicará la carta.
     *
     * @param fila Número de fila.
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Obtiene la columna en la que se encuentra la carta en el tablero.
     *
     * @return Número de columna.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Establece la columna en la que se ubicará la carta.
     *
     * @param columna Número de columna.
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * Indica si la carta ya ha sido emparejada correctamente con su par.
     *
     * @return {@code true} si la carta está emparejada, {@code false} en caso contrario.
     */
    public boolean isEsEmparejada() {
        return esEmparejada;
    }

    /**
     * Establece el estado de emparejamiento de la carta.
     *
     * @param esEmparejada {@code true} si la carta ha sido emparejada, {@code false} si no.
     */
    public void setEsEmparejada(boolean esEmparejada) {
        this.esEmparejada = esEmparejada;
    }


        /**
     * Indica si la carta está actualmente destapada.
     *
     * @return {@code true} si la carta está destapada, {@code false} si está tapada.
     */
    public boolean isEsDestapada() {
        return esDestapada;
    }

    /**
     * Establece si la carta debe estar destapada o no.
     *
     * @param esDestapada {@code true} para mostrar la carta, {@code false} para ocultarla.
     */
    public void setEsDestapada(boolean esDestapada) {
        this.esDestapada = esDestapada;
    }

    /**
     * Obtiene el nombre asignado a la carta.
     *
     * @return Nombre de la carta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la carta.
     *
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ruta de la imagen asociada a la carta.
     *
     * @return Ruta del archivo de imagen.
     */
    public String getRutaImg() {
        return rutaImg;
    }

    /**
     * Establece la ruta de la imagen asociada a la carta.
     *
     * @param rutaImg Ruta del archivo de imagen.
     */
    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }


        /**
     * Obtiene el identificador único de la carta.
     *
     * @return ID de la carta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la carta.
     *
     * @param id ID a asignar a la carta.
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
    
    
}
