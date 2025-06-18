/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.modelo.Jugador;
import edu.progavud.parcial2pa.modelo.JugadorVO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

/**
 * Hilo que maneja la comunicación con un cliente conectado al servidor.
 *
 * <p>
 * Este hilo recibe mensajes del cliente, los procesa (por ejemplo, filtrando
 * groserías), y los reenvía a otros clientes conectados si es necesario.</p>
 *
 * También mantiene una lista estática de clientes activos en el servidor.
 *
 * @author hailen
 */
import java.awt.*;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Administrador
 */
public class ServidorThread extends Thread {

    // Socket principal para la comunicación con el cliente
    Socket scli = null;

// Segundo socket para comunicación adicional (por ejemplo, mensajes privados)
    Socket scli2 = null;

// Flujo de entrada de datos desde el cliente
    DataInputStream entrada = null;

// Flujo de salida de datos hacia el cliente
    DataOutputStream salida = null;

// Segundo flujo de salida (por ejemplo, para mensajes privados)
    DataOutputStream salida2 = null;

     /**
     * Bandera que indica si la partida ha sido iniciada para este jugador.
     * <p>
     * Se declara como {@code volatile} para asegurar que los cambios en su valor
     * sean visibles entre hilos, evitando problemas de sincronización.
     * </p>
     */
    private volatile boolean partidaIniciada = false;

/**
     * Nombre de usuario del cliente conectado.
     * <p>
     * Este nombre se obtiene durante la autenticación y se utiliza para identificar al jugador
     * en la interfaz del servidor y durante la comunicación.
     * </p>
     */
    String nameUser;

    /**
     * Clave del usuario conectado.
     * <p>
     * Se utiliza para verificar las credenciales del jugador durante el proceso de autenticación.
     * </p>
     */
    String clave;

    /**
     * Objeto que representa los datos del jugador autenticado.
     * <p>
     * Contiene información como nombre, aciertos, intentos y eficiencia del jugador.
     * </p>
     */
    private JugadorVO jugadorVO;
    
// Referencia al controlador del servidor
    private ControlServidor cServidor;

    /**
     * Constructor de ServidorThread.
     *
     * Inicializa los sockets de comunicación con el cliente, registra este hilo
     * en la lista de clientes activos y notifica en la vista del servidor que
     * un cliente se ha conectado.
     *
     * @param scliente Socket principal de conexión con el cliente.
     * @param scliente2 Segundo socket (por ejemplo, para mensajes privados).
     * @param cServidor Controlador del servidor que gestiona la lógica del
     * sistema.
     */
    public ServidorThread(Socket scliente, Socket scliente2, ControlServidor cServidor) {
        scli = scliente;
        scli2 = scliente2;
        this.cServidor = cServidor;
        nameUser = "";

    }

    /**
     * Devuelve el nombre de usuario asociado a este cliente.
     *
     * @return Nombre del usuario.
     */
    public String getNameUser() {
        return nameUser;
    }

    /**
     * Asigna el nombre de usuario a este cliente.
     *
     * @param name Nombre que se desea asignar al cliente.
     */
    public void setNameUser(String name) {
        nameUser = name;
    }

        /**
     * Obtiene la clave del jugador conectado.
     *
     * @return Cadena de texto que representa la clave del jugador.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la clave del jugador conectado.
     *
     * @param clave Clave que se asignará al jugador.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene el objeto {@code JugadorVO} asociado al jugador conectado.
     *
     * @return Instancia de {@code JugadorVO} con los datos del jugador.
     */
    public JugadorVO getJugadorVO() {
        return jugadorVO;
    }

    /**
     * Establece el objeto {@code JugadorVO} con los datos del jugador conectado.
     *
     * @param jugadorVO Objeto que contiene los datos del jugador.
     */
    public void setJugadorVO(JugadorVO jugadorVO) {
        this.jugadorVO = jugadorVO;
    }


    /**
     * Ejecuta el hilo que gestiona la comunicación con un cliente.
     *
     * <p>
     * Este método realiza lo siguiente:</p>
     * <ul>
     * <li>Inicializa los flujos de entrada y salida.</li>
     * <li>Lee el nombre del usuario conectado.</li>
     * <li>Escucha continuamente los comandos del cliente:</li>
     * <ul>
     * <li><b>1:</b> Recibe un mensaje público y lo reenvía a todos los clientes
     * conectados.</li>
     * <li><b>2:</b> Envía al cliente la lista actual de usuarios
     * conectados.</li>
     * <li><b>3:</b> Recibe un mensaje privado y lo envía al destinatario
     * correspondiente.</li>
     * </ul>
     * <li>Cuando el cliente se desconecta, lo elimina de la lista de clientes
     * activos y actualiza la lista para los demás clientes.</li>
     * </ul>
     */
    public void setPartidaIniciada(boolean estado) {
        this.partidaIniciada = estado;
    }

        /**
     * Método principal del hilo que gestiona la conexión y comunicación con un cliente jugador.
     * <p>
     * Este método:
     * <ul>
     *   <li>Establece los flujos de entrada y salida de datos del socket.</li>
     *   <li>Recibe el nombre de usuario y la clave enviados por el cliente.</li>
     *   <li>Verifica si el jugador está autenticado y si la partida aún no ha comenzado.</li>
     *   <li>Agrega al jugador a la lista de clientes activos si es válido.</li>
     *   <li>Escucha mensajes entrantes desde el cliente para realizar acciones como enviar coordenadas o mensajes privados.</li>
     *   <li>Finaliza la conexión si el jugador no está autorizado, si hay más de 4 jugadores o si ya inició la partida.</li>
     * </ul>
     * </p>
     */
    public void run() {
        cServidor.getcPrinc().getcVentana().getvServidor().mostrar(".::Esperando Mensajes :");

        try {
            entrada = new DataInputStream(scli.getInputStream());
            salida = new DataOutputStream(scli.getOutputStream());
            salida2 = new DataOutputStream(scli2.getOutputStream());
            this.setNameUser(entrada.readUTF());
            this.setClave(entrada.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int opcion = 0, numUsers = 0;
        String jugador = "", mencli = "";
        boolean estaAgregado = false;
        System.out.println("holaaaa, paso por qquiii");
        boolean condicion = true;
        if (cServidor.getcPrinc().isIsPartidaIniciada()) {
            this.enviaMsgSalida(this.nameUser, "desconectado3");
            condicion = false;
        }
        while (condicion) {

            try {
                if (cServidor.verificarUsuario(this.nameUser, this.clave) == null) {
                    this.enviaMsgSalida(this.nameUser, "desconectado");
                    break;
                }

                if (ControlServidor.clientesActivos.size() >= 4) {
                    this.enviaMsgSalida(this.nameUser, "desconectado2");
                    break;
                }

                if (!estaAgregado) {
                    jugadorVO = cServidor.verificarUsuario(this.nameUser, this.clave);
                    System.out.println(jugadorVO.getNombre() + jugadorVO.getClave() + "ed-------------------------");
                    ControlServidor.clientesActivos.add(this);
                    cServidor.getcPrinc().getcVentana().habilitarBotonesAlIniciar(nameUser);
                    cServidor.getcPrinc().getcVentana().getvServidor().mostrar("Ingresó un nuevo Jugador: " + this.nameUser);

                    this.enviaMsg(ControlServidor.clientesActivos.get(ControlServidor.clientesActivos.size() - 1).getNameUser(), "Turno Asignado ->" + ControlServidor.clientesActivos.size());
                    this.enviaMsg(ControlServidor.clientesActivos.get(ControlServidor.clientesActivos.size() - 1).getNameUser(), "Espera a que el Servidor inicie la partida...");
                    estaAgregado = true;
                    cServidor.getcPrinc().getcVentana().activarPartidaBasica();
                }

            } catch (SQLException ex) {
                System.getLogger(ServidorThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            String filtrado = "";
            try {
                opcion = entrada.readInt();
                switch (opcion) {

                    case 1: // Mensaje a todos
                        mencli = entrada.readUTF();

                        enviaMsg(mencli);
                        cServidor.getcPrinc().getcVentana().getvServidor().mostrar("------------------------- \n Coordenadas de:" + ControlServidor.clientesActivos.get(cServidor.getTurnoActual() - 1).getJugadorVO().getNombre() + " -> Turno " + cServidor.getTurnoActual() + " \n" + mencli);

                        break;
                    case 3: // Mensaje privado
                        jugador = entrada.readUTF();
                        mencli = entrada.readUTF();
                        enviaMsg(jugador, mencli);
                        cServidor.getcPrinc().getcVentana().getvServidor().mostrar(mencli);

                        break;

                }
            } catch (IOException e) {
                break;
            }
        }

        enviaMsg(this.getNameUser() + " se ha desconectado del juego.");
        cServidor.getcPrinc().getcVentana().getvServidor().mostrar("Se removio un usuario");

        //        
        ControlServidor.clientesActivos.removeElement(this);
//        if (ControlServidor.clientesActivos.size() < 2) {
//            cServidor.getcPrinc().getcVentana().getvServidor().getBtnIniciarJuego().setEnabled(false);
//        } else {
//            cServidor.getcPrinc().getcVentana().getvServidor().getBtnIniciarJuego().setEnabled(false);
//        }
        System.out.println(ControlServidor.clientesActivos);

        try {
            cServidor.getcPrinc().getcVentana().getvServidor().mostrar("Se desconecto un usuario");
            scli.close();
        } catch (Exception et) {
            cServidor.getcPrinc().getcVentana().getvServidor().mostrar("no se puede cerrar el socket");
        }
    }

    /**
     * Envía un mensaje público a todos los clientes conectados.
     *
     * <p>
     * El mensaje se envía a través del segundo canal de salida de cada cliente
     * (utilizando {@code salida2}), precedido por la opción 1 para indicar que
     * se trata de un mensaje general.</p>
     *
     * @param mencli2 El contenido del mensaje a enviar.
     */
    public void enviaMsg(String mencli2) {
        ServidorThread user = null;
        for (int i = 0; i < ControlServidor.clientesActivos.size(); i++) {
            //cServidor.getcPrinc().getcVentana().getvServidor().mostrar("MENSAJE DEVUELTO:" + mencli2);
            try {
                user = ControlServidor.clientesActivos.get(i);
                user.salida2.writeInt(1);//opcion de mensage 
                user.salida2.writeUTF(mencli2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Envía un mensaje privado a un cliente específico.
     *
     * <p>
     * Busca al cliente con el nombre indicado y le envía el mensaje a través de
     * {@code salida2}, usando el código <b>3</b> para indicar que es un mensaje
     * privado.</p>
     *
     * @param amigo Nombre del destinatario del mensaje.
     * @param mencli Contenido del mensaje privado.
     */
    public void enviaMsg(String jugador, String mencli) {
        ServidorThread user = null;
        for (int i = 0; i < ControlServidor.clientesActivos.size(); i++) {
            try {
                user = ControlServidor.clientesActivos.get(i);
                if (user.nameUser.equals(jugador)) {
                    user.salida2.writeInt(3);//opcion de mensaje amigo   
                    user.salida2.writeUTF(this.getNameUser());
                    user.salida2.writeUTF(mencli);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     /**
     * Envía un mensaje de salida (desconexión o estado especial) al cliente desde el servidor.
     * 
     * Este método escribe tres datos a través del flujo de salida `salida2`:
     * <ul>
     *   <li>Un entero con el valor 3, que representa el tipo de mensaje (privado o de control).</li>
     *   <li>El nombre del usuario que envía el mensaje.</li>
     *   <li>El contenido del mensaje (por ejemplo, "desconectado", "desconectado2").</li>
     * </ul>
     * 
     * @param jugador Nombre del jugador receptor del mensaje (parámetro no utilizado dentro del método).
     * @param mencli Mensaje a enviar al cliente, indicando un estado o instrucción especial.
     */
    public void enviaMsgSalida(String jugador, String mencli) {
        try {
            this.salida2.writeInt(3);
            this.salida2.writeUTF(this.getNameUser());
            this.salida2.writeUTF(mencli);
        } catch (IOException ex) {
            System.getLogger(ServidorThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /**
     * Devuelve la lista de hilos de clientes actualmente conectados al
     * servidor.
     *
     * @return Un vector con las instancias de {@link ServidorThread} activas.
     */
}
