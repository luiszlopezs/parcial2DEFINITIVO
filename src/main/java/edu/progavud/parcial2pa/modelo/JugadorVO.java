/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.modelo;

/**
 * Clase que representa un jugador con sus datos personales y métricas de rendimiento
 * en el juego, como intentos, aciertos y eficiencia.
 * 
 * @author hailen
 */
public class JugadorVO {

    private String nombre;     // Nombre del jugador
    private String clave;      // Clave del jugador
    private int intentos;      // Número de intentos realizados
    private int aciertos;      // Número de aciertos logrados
    private double eficiencia; // Porcentaje de eficiencia calculado

    /**
     * Constructor que inicializa el nombre y la clave del jugador.
     * 
     * @param nombre Nombre del jugador.
     * @param clave Clave del jugador.
     */
    public JugadorVO(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    /**
     * Constructor vacío para crear un objeto sin inicializar atributos.
     */
    public JugadorVO() {
    }
  
    

    //Método que incrementa el número de intentos, se llama cada vez que el jugador envía una orden al servidor, sea un acierto o no
    public void incrementarIntento() {
        intentos++;
    }

    //Método que incrementa el número de aciertos, se llama cada vez que el usuario destapa dos casillas con la misma imagen
    public void incrementarAciertos() {
        aciertos++;
    }

    //Método que calcula la eficiencia, se calcula dividiendo el número de aciertos entre el número de intentos (esto determinará el ganador)
    public double calcularEficiencia() {
        return (double) aciertos / intentos;
    }

       /**
     * Devuelve el nombre del jugador.
     * 
     * @return Nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del jugador.
     * 
     * @param nombre Nombre a asignar al jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la clave del jugador.
     * 
     * @return Clave del jugador.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Asigna la clave del jugador.
     * 
     * @param clave Clave a asignar al jugador.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Devuelve el número de intentos realizados por el jugador.
     * 
     * @return Número de intentos.
     */
    public int getIntentos() {
        return intentos;
    }

    /**
     * Asigna el número de intentos realizados por el jugador.
     * 
     * @param intentos Número de intentos a asignar.
     */
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }


       /**
     * Devuelve la cantidad de aciertos del jugador.
     * 
     * @return Número de aciertos.
     */
    public int getAciertos() {
        return aciertos;
    }

    /**
     * Asigna la cantidad de aciertos del jugador.
     * 
     * @param aciertos Número de aciertos a asignar.
     */
    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    /**
     * Devuelve la eficiencia del jugador.
     * 
     * @return Valor de eficiencia como número decimal.
     */
    public double getEficiencia() {
        return eficiencia;
    }

    /**
     * Asigna la eficiencia del jugador.
     * 
     * @param eficiencia Valor de eficiencia a asignar.
     */
    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    
    
}
