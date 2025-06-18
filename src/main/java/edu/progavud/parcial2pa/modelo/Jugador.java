/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase que representa a un jugador en el sistema.
 * Contiene la información básica de autenticación.
 * 
 * @author hailen
 */
public class Jugador {

    /**
     * Nombre del jugador.
     */
    private String nombre;

    /**
     * Clave o contraseña del jugador.
     */
    private String clave;


    // Flujo de salida hacia el servidor (canal principal)
    private DataOutputStream salida;

    // Flujo de entrada desde el servidor (canal principal)
    private DataInputStream entrada;

    // Flujo adicional de entrada para mensajes
    private DataInputStream entrada2;

    // Dirección IP del servidor
    private static String IPserver;

    // Socket principal para la comunicación
    Socket comunication = null;

    // Segundo socket para recepción de mensajes
    Socket comunication2 = null;


    
    /**
     * Constructor por defecto de la clase Jugador.
     * Crea una instancia vacía sin inicializar nombre ni clave.
     */
    public Jugador() {
    }


    //Reciclado de enviarMensaje() en cliente

    // Método para enviar dos coordenadas de un intento en una instrucción (primera y segunda casilla)
    public void enviarIntento(String instruccion) throws IOException {
        salida.writeInt(1); 
        salida.writeUTF("   [" + this.nombre + "]: "+ instruccion);
    }


        /**
     * Establece la conexión con el servidor utilizando dos sockets.
     * Se inicializan los flujos de entrada y salida para ambos sockets.
     * 
     * @param datosPasar Lista con los datos necesarios para la conexión:
     *                   - datosPasar.get(0): puerto principal (como String).
     *                   - datosPasar.get(1): segundo puerto (como String).
     *                   - datosPasar.get(2): dirección IP del servidor.
     * @throws IOException Si ocurre un error al establecer la conexión o al abrir los flujos.
     */
    public void conexion(ArrayList<String> datosPasar) throws IOException {

        comunication = new Socket(datosPasar.get(2), Integer.parseInt(datosPasar.get(0)));

        comunication2 = new Socket(datosPasar.get(2), Integer.parseInt(datosPasar.get(1)));

        // Primero salida, luego entrada
        salida = new DataOutputStream(comunication.getOutputStream());

        entrada = new DataInputStream(comunication.getInputStream());


        entrada2 = new DataInputStream(comunication2.getInputStream());

        salida.writeUTF(nombre);
        salida.writeUTF(clave);

    }

    // Getters y setters

 

       /**
     * Devuelve el flujo de salida (primer socket) hacia el servidor.
     * 
     * @return Objeto DataOutputStream que permite enviar datos al servidor.
     */
    public DataOutputStream getSalida() {
        return salida;
    }

    /**
     * Establece el flujo de salida (primer socket) hacia el servidor.
     * 
     * @param salida Objeto DataOutputStream a asignar.
     */
    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    /**
     * Devuelve el flujo de entrada (primer socket) desde el servidor.
     * 
     * @return Objeto DataInputStream que permite recibir datos del servidor.
     */
    public DataInputStream getEntrada() {
        return entrada;
    }

    /**
     * Establece el flujo de entrada (primer socket) desde el servidor.
     * 
     * @param entrada Objeto DataInputStream a asignar.
     */
    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    /**
     * Devuelve el flujo de entrada del segundo socket desde el servidor.
     * 
     * @return Objeto DataInputStream para la segunda conexión de entrada.
     */
    public DataInputStream getEntrada2() {
        return entrada2;
    }

    /**
     * Establece el flujo de entrada del segundo socket desde el servidor.
     * 
     * @param entrada2 Objeto DataInputStream a asignar para el segundo socket.
     */
    public void setEntrada2(DataInputStream entrada2) {
        this.entrada2 = entrada2;
    }


        /**
     * Devuelve la dirección IP actual del servidor configurada para los jugadores.
     * 
     * @return Cadena con la dirección IP del servidor.
     */
    public static String getIPserver() {
        return IPserver;
    }

    /**
     * Establece la dirección IP del servidor para los jugadores.
     * 
     * @param IPserver Dirección IP a asignar.
     */
    public static void setIPserver(String IPserver) {
        Jugador.IPserver = IPserver;
    }

    /**
     * Devuelve el socket principal de comunicación con el servidor.
     * 
     * @return Objeto Socket utilizado para la conexión principal.
     */
    public Socket getComunication() {
        return comunication;
    }

    /**
     * Establece el socket principal de comunicación con el servidor.
     * 
     * @param comunication Objeto Socket que representa la conexión principal.
     */
    public void setComunication(Socket comunication) {
        this.comunication = comunication;
    }

    /**
     * Devuelve el segundo socket de comunicación con el servidor.
     * 
     * @return Objeto Socket utilizado para la segunda conexión.
     */
    public Socket getComunication2() {
        return comunication2;
    }

    /**
     * Establece el segundo socket de comunicación con el servidor.
     * 
     * @param comunication2 Objeto Socket que representa la segunda conexión.
     */
    public void setComunication2(Socket comunication2) {
        this.comunication2 = comunication2;
    }


        /**
     * Devuelve el nombre del jugador.
     * 
     * @return Cadena que representa el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre al jugador.
     * 
     * @param nombre Cadena con el nombre que se desea asignar al jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la clave del jugador.
     * 
     * @return Cadena que representa la clave del jugador.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Asigna una clave al jugador.
     * 
     * @param clave Cadena con la clave que se desea asignar al jugador.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    
    

}
