/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.Vista.VistaJugador;
import edu.progavud.parcial2pa.modelo.Jugador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de la interfaz gráfica del cliente.Se encarga de gestionar los
 * eventos de las vistas {@link VistaJugador}, {@link VistaPrivada} y
 * {@link VistaAyuda}, así como de coordinar acciones entre la vista y el
 * controlador principal del cliente.
 *
 * @author hailen
 */
public class ControlVentanaJugador implements ActionListener {

    // Controlador principal del cliente
    private ControlPrincipalJugador cPrinc;

// Ventana principal del cliente (interfaz de chat general)
    private VistaJugador vJugador;

// Ventana de chat privado con un usuario
//    private VistaPrivada vPrivada;
// Nombre del cliente
    private String nombre;

// Contraseña del cliente
    private String clave;

// Dirección IP del servidor
    private ArrayList<String> datosPasar;

// Mapa para ventanas privadas por cada amigo
//    private Map<String, VistaPrivada> chatsPrivados = new HashMap<>();
    /**
     * Constructor de ControlVentanaCliente.
     *
     * Inicializa las vistas del cliente (general y privada), obtiene el nombre
     * de usuario e IP ingresados por el usuario, y los almacena para uso
     * posterior.
     *
     * @param cPrinc Controlador principal del cliente.
     */
    public ControlVentanaJugador(ControlPrincipalJugador cPrinc) throws SQLException {
        this.cPrinc = cPrinc;
        cargarVistaJugador();
        datosPasar = cPrinc.inicializarPuertosDesdeProps(vJugador.rutaJfileChooserPorts());
        nombre = vJugador.nombreJugador(); //Reciclado de vCliente
        vJugador.setNombreJugador(nombre); //Reciclado de vCliente, recibe del JOptionPane el nombre y contraseña
        clave = vJugador.claveJugador();


    }

    /**
     * Maneja los eventos de la interfaz gráfica del cliente.
     *
     * <p>
     * Dependiendo del comando recibido, realiza una de las siguientes
     * acciones:</p>
     * <ul>
     * <li><b>"CLIENTE_ENVIAR"</b>: Envía un mensaje público a todos los
     * usuarios.</li>
     * <li><b>"CLIENTE_PRIVADO"</b>: Abre la ventana de chat privado con el
     * usuario seleccionado.</li>
     * <li><b>"PRIVADO_ENVIAR"</b>: Envía un mensaje privado al destinatario
     * desde la ventana privada.</li>
     * </ul>
     *
     * @param e Evento de acción generado por un componente de la vista.
     */
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
//        String destinatario = "";
        switch (comando) {
            case "CLIENTE_ENVIAR":
                String mensaje = vJugador.getMensaje();
                cPrinc.enviarMensajePublico(mensaje);
                vJugador.getTxtMensage().setText("");
                break;
            case "AYUDA":
                vJugador.mostrarVentanaAyuda();
                break;
        }
    }

    /**
     * Inicializa y configura la ventana principal del cliente.
     *
     * <p>
     * Asocia los botones de enviar mensaje y chat privado con sus respectivos
     * comandos de acción y los vincula a este controlador como listener.</p>
     */
    public void cargarVistaJugador() {
        vJugador = new VistaJugador(this);

        vJugador.getBtnEnviar().setActionCommand("CLIENTE_ENVIAR");
        vJugador.getBtnEnviar().addActionListener(this);

        vJugador.getItemAyuda().setActionCommand("AYUDA");
        vJugador.getItemAyuda().addActionListener(this);


    }


    /**
     * Devuelve el nombre del usuario cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del cliente.
     *
     * @param nombre Nombre que se desea asignar al cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la dirección IP del servidor ingresada por el cliente.
     *
     * @return Dirección IP como cadena de texto.
     */
    /**
     * Obtiene la vista gráfica asociada al jugador.
     *
     * @return Objeto {@code VistaJugador} que representa la interfaz del
     * jugador cliente.
     */
    public VistaJugador getvJugador() {
        return vJugador;
    }

    /**
     * Obtiene la clave ingresada por el jugador.
     *
     * @return Cadena de texto que representa la contraseña del jugador.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la clave ingresada por el jugador.
     *
     * @param clave Cadena de texto que representa la nueva contraseña a
     * asignar.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene la lista de datos cargados desde el archivo de propiedades.
     * <p>
     * Esta lista puede incluir información como puertos, usuarios y
     * contraseñas.
     * </p>
     *
     * @return Lista de cadenas con los datos cargados para inicializar la
     * conexión.
     */
    public ArrayList<String> getDatosPasar() {
        return datosPasar;
    }

    /**
     * Establece la lista de datos cargados desde el archivo de propiedades.
     *
     * @param datosPasar Lista de cadenas con información como puertos, usuarios
     * o claves.
     */
    public void setDatosPasar(ArrayList<String> datosPasar) {
        this.datosPasar = datosPasar;
    }

}
