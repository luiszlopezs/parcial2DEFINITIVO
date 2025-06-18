/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.modelo.Jugador;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador que gestiona la lógica principal del cliente. Coordina la
 * interacción entre la vista, el modelo {@link Cliente} y el hilo encargado de
 * la comunicación {@link ClienteThread}.
 *
 * @author hailen
 */
public class ControlJugador {

    // Referencia al controlador principal del cliente
    private ControlPrincipalJugador cPrinc;

// Objeto que representa al cliente (modelo de datos)
    private Jugador jugador;

// Hilo que gestiona la recepción de mensajes del servidor
    private JugadorThread jugadorThread;

    /**
     * Crea una nueva instancia de ControlCliente, inicializando el modelo,
     * estableciendo la conexión con el servidor e iniciando el hilo de escucha.
     *
     * @param cPrinc Referencia al controlador principal del cliente.
     * @throws IOException Si ocurre un error al establecer la conexión o al
     * iniciar el hilo.
     */
    public ControlJugador(ControlPrincipalJugador cPrinc)  {
        this.cPrinc = cPrinc;
        jugador = new Jugador();
        
        //Jugador.setIPserver(cPrinc.getcVentana().getIp());
        jugador.setNombre(cPrinc.getcVentana().getNombre());
        jugador.setClave(cPrinc.getcVentana().getClave());

        try {
            jugador.conexion(cPrinc.getcVentana().getDatosPasar());
        } catch (IOException ex) {
            
        }

        try {
            jugadorThread = new JugadorThread(jugador.getEntrada2(), this);
        } catch (IOException ex) {
            
        }
        jugadorThread.start();

    }

    /**
     * Envía un mensaje público a todos los usuarios conectados.
     *
     * @param mensaje El texto del mensaje que se desea enviar.
     * @throws IOException Si ocurre un error al enviar el mensaje.
     */
    public void enviarIntento(String instruccion) throws IOException  {
            jugador.enviarIntento(instruccion);
        
    }
    

    /**
     * Devuelve el objeto cliente asociado a este controlador.
     *
     * @return El jugador actual.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Asigna un nuevo objeto cliente a este controlador.
     *
     * @param cliente El nuevo cliente a asignar.
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Devuelve el hilo de cliente encargado de recibir mensajes del servidor.
     *
     * @return El hilo cliente actualmente en uso.
     */
    public JugadorThread getClienteThread() {
        return jugadorThread;
    }

    /**
     * Asigna un nuevo hilo cliente a este controlador.
     *
     * @param clienteThread El nuevo hilo de cliente a asignar.
     */
    public void setJugadorThread(JugadorThread jugadorThread) {
        this.jugadorThread = jugadorThread;
    }

    /**
     * Establece la conexión del cliente con el servidor.
     *
     * @throws IOException Si ocurre un error al conectarse.
     */
    public void conexionJugador() throws IOException {
        jugador.conexion(cPrinc.getcVentana().getDatosPasar());
    }

    /**
     * Devuelve el controlador principal del cliente.
     *
     * @return Referencia al controlador principal del cliente.
     */
    public ControlPrincipalJugador getcPrinc() {
        return cPrinc;
    }

}