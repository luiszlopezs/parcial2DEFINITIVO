/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.modelo.Jugador;
import edu.progavud.parcial2pa.modelo.JugadorDAO;
import edu.progavud.parcial2pa.modelo.JugadorVO;
import edu.progavud.parcial2pa.modelo.ServidorVO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

/**
 * Controlador encargado de manejar la lógica principal del servidor. Se encarga
 * de gestionar las conexiones entrantes, coordinar la lógica del modelo
 * {@link Servidor}, y comunicarse con el controlador principal del servidor.
 *
 * @author hailen
 */
public class ControlServidor {

        /** 
     * Referencia al controlador principal del servidor, encargado de coordinar
     * el flujo general del juego desde el lado del servidor.
     */
    private ControlPrincipalServidor cPrinc;

    /**
     * Objeto que contiene los datos de configuración y estado del servidor.
     * Forma parte del modelo y encapsula propiedades como IP, puerto, etc.
     */
    private ServidorVO servidorVO;

    /**
     * Objeto encargado del acceso a datos de los jugadores registrados en la base de datos.
     * Implementa el patrón DAO para mantener desacoplada la lógica de persistencia.
     */
    private JugadorDAO jugadorDAO;

    /**
     * Bandera que indica si el servidor se encuentra en modo escucha.
     * <p>
     * Se declara como {@code volatile} para asegurar la visibilidad entre hilos,
     * ya que puede ser accedida y modificada desde diferentes hilos de ejecución.
     * </p>
     */
    private volatile boolean listeniing;


    /**
     * Hilo encargado de atender a los clientes que se conectan al servidor.
     * Maneja la comunicación a través de sockets y permite la interacción en paralelo.
     */
    private ServidorThread servidorThread;

    /**
     * Número que representa el turno actual del juego.
     * <p>
     * Generalmente toma los valores 1 o 2, dependiendo de cuál jugador tiene el turno activo.
     * </p>
     */
    private int turnoActual = 1;

    /**
     * Contador que indica cuántas cartas han sido encontradas hasta el momento.
     * Se incrementa por cada pareja descubierta correctamente.
     */
    private int cartasEncontradas = 0;


// Generador de números aleatorios para funcionalidades del servidor
    private Random random;

       /**
     * Lista estática que almacena las instancias activas de {@code ServidorThread},
     * representando a los clientes actualmente conectados al servidor.
     * <p>
     * Se usa un {@code Vector} para asegurar sincronización en entornos multihilo.
     * </p>
     */
    public static Vector<ServidorThread> clientesActivos = new Vector();

    /**
     * Constructor de ControlServidor.
     *
     * Inicializa el controlador del servidor, el modelo {@link Servidor}, y el
     * generador de números aleatorios.
     *
     * @param cPrinc Referencia al controlador principal del servidor.
     */
    public ControlServidor(ControlPrincipalServidor cPrinc) {
        this.cPrinc = cPrinc;
        random = new Random();
        servidorVO = new ServidorVO();
        jugadorDAO = new JugadorDAO();
        this.listeniing = servidorVO.isListening();

    }

    /**
     * Inicia el servidor y queda a la espera de conexiones de usuarios.
     *
     * <p>
     * Realiza las siguientes acciones:
     * <ul>
     * <li>Inicializa las listas de palabras restringidas desde un archivo de
     * configuración.</li>
     * <li>Establece los sockets del servidor en los puertos 8081 y 8082.</li>
     * <li>Muestra mensajes en la interfaz del servidor.</li>
     * <li>Espera conexiones de clientes y crea un hilo {@link ServidorThread}
     * por cada cliente que se conecta.</li>
     * </ul>
     *
     * Si ocurre un error de entrada/salida, se muestra el mensaje
     * correspondiente en la interfaz del servidor.
     */
    public void runServer() {
        try {

            servidorVO.setServ(new ServerSocket(servidorVO.getPort1()));  //traer de properties
            servidorVO.setServ2(new ServerSocket(servidorVO.getPort2()));  //traer de properties
            cPrinc.getcVentana().getvServidor().mostrar("::Servidor activo::");
            while (listeniing ) {
                if ( clientesActivos.size() < 4){
                                    Socket sock = null, sock2 = null;
                try {
                    //muestra un mensaje en la vista del server
                    cPrinc.getcVentana().getvServidor().mostrar("Esperando Jugadores");
                    sock = servidorVO.getServ().accept();
                    sock2 = servidorVO.getServ2().accept();
                } catch (IOException e) {
                    //muestra un mensaje en la vista del server
                    cPrinc.getcVentana().getvServidor().mostrar("Accept failed: " + servidorVO.getServ() + ", " + e.getMessage());
                    continue;
                }
                ServidorThread user = new ServidorThread(sock, sock2, this);
                user.start();
                }
//                else{
//                    cerrarEntradasDeJugadores();
//                }

            }
            

        } catch (IOException e) {
            //muestra un mensaje en la vista del server
            cPrinc.getcVentana().getvServidor().mostrar("error :" + e);
        }

    }


    /**
     * Revisa un mensaje y reemplaza automáticamente cualquier palabra
     * considerada grosería por una palabra alternativa tomada aleatoriamente de
     * una lista de reemplazos.
     *
     * @param mensaje El mensaje original enviado por el usuario.
     * @return El mensaje filtrado, con las groserías reemplazadas.
     */
    /**
     * Inicializa las listas de palabras ofensivas y sus reemplazos desde un
     * archivo de propiedades.
     *
     * <p>
     * Busca claves con formato <b>"groseria1", "groseria2", ...</b> y
     * <b>"reemplazo1", "reemplazo2", ...</b> hasta que no encuentre más. Los
     * valores encontrados se agregan a las listas correspondientes del modelo
     * {@link Servidor}.
     *
     * @param props Archivo de propiedades cargado previamente.
     */
    public void inicializarDesdeProperties(ArrayList<String> datosPasar) {
        servidorVO.setPort1(Integer.parseInt(datosPasar.get(0)));
        servidorVO.setPort2(Integer.parseInt(datosPasar.get(1)));
        for (int i = 2; i <= 12; i += 2) {

            try {
                getJugadorDAO().insertarJugador(
                        datosPasar.get(i),
                        datosPasar.get(i + 1)
                );
                System.out.println(datosPasar.get(i) + " " + datosPasar.get(i + 1));
            } catch (SQLException ex) {
                System.getLogger(ControlServidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

        System.out.println(datosPasar.get(0));
        System.out.println(datosPasar.get(1));
    }

        /**
     * Verifica si un usuario con la combinación de nombre y clave existe en la base de datos.
     * <p>
     * Este método utiliza el objeto {@code JugadorDAO} para consultar la base de datos 
     * y obtener los datos del jugador, en caso de que las credenciales coincidan.
     * </p>
     *
     * @param usuario Nombre de usuario a verificar.
     * @param clave Clave correspondiente al usuario.
     * @return Objeto {@code JugadorVO} con los datos del jugador si existe; de lo contrario, {@code null}.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     */
    public JugadorVO verificarUsuario(String usuario, String clave) throws SQLException {
        //conexion con JUGADOR DAO o SERVIDOR DAO para verificar si el usuario existe
        jugadorDAO = new JugadorDAO();
        return jugadorDAO.consultarJugador(usuario, clave);

    }

    /**
     * Devuelve la referencia al controlador principal del servidor.
     *
     * @return El controlador principal del servidor.
     */
    public ControlPrincipalServidor getcPrinc() {
        return cPrinc;
    }

    /**
     * Devuelve el objeto del modelo {@link Servidor} que contiene la lógica del
     * servidor.
     *
     * @return El modelo del servidor.
     */
    public ServidorVO getServidorVO() {
        return servidorVO;
    }

    /**
     * Devuelve el hilo actual del servidor encargado de manejar la conexión con
     * un cliente.
     *
     * @return El hilo del servidor.
     */
    public ServidorThread getServidorThread() {
        return servidorThread;
    }

        /**
     * Obtiene la lista de clientes actualmente conectados al servidor.
     *
     * @return Un {@code Vector} de objetos {@code ServidorThread} que representan los clientes activos.
     */
    public static Vector<ServidorThread> getClientesActivos() {
        return clientesActivos;
    }

    /**
     * Establece la lista de clientes activos conectados al servidor.
     *
     * @param clientesActivos {@code Vector} que contiene las instancias de {@code ServidorThread}
     *                        correspondientes a los clientes conectados.
     */
    public static void setClientesActivos(Vector<ServidorThread> clientesActivos) {
        ControlServidor.clientesActivos = clientesActivos;
    }


    public void incrementarIntento() {
        int intentos = clientesActivos.get(turnoActual - 1).getJugadorVO().getIntentos();
        clientesActivos.get(turnoActual - 1).getJugadorVO().setIntentos(intentos + 1);
        cPrinc.getcVentana().aumentarIntentosEnVista(intentos + 1, turnoActual);
        if (turnoActual == clientesActivos.size()) {
            turnoActual = 1;
        } else {
            turnoActual++;
        }
        terminarPartida();
        cPrinc.getcVentana().siguienteTurnoEnVista(getTurnoActual());

    }

     /**
     * Incrementa el número de intentos del jugador actual y cambia el turno al siguiente jugador.
     * <p>
     * Este método incrementa el contador de intentos en el objeto {@code JugadorVO} del jugador activo,
     * actualiza la vista del servidor con el nuevo número de intentos y rota el turno hacia el siguiente jugador.
     * Además, verifica si la partida debe finalizar e informa en la vista el cambio de turno.
     * </p>
     */
    public void incrementarAcierto() {
        cartasEncontradas++;
        int acierto = clientesActivos.get(turnoActual - 1).getJugadorVO().getAciertos();
        int intentos = clientesActivos.get(turnoActual - 1).getJugadorVO().getIntentos();
        clientesActivos.get(turnoActual - 1).getJugadorVO().setAciertos(acierto + 1);
        clientesActivos.get(turnoActual - 1).getJugadorVO().setIntentos(intentos + 1);
        cPrinc.getcVentana().aumentarAciertoEnVista(acierto + 1, intentos + 1, turnoActual);
        terminarPartida();
    }

        /**
     * Finaliza la partida si se han encontrado todas las parejas de cartas.
     * <p>
     * Cuando el contador de cartas encontradas alcanza el total esperado (20),
     * este método desactiva los botones de la partida en la vista del servidor
     * y envía un mensaje a cada jugador conectado notificando que el juego ha terminado.
     * </p>
     */
    public void terminarPartida() {
        if (cartasEncontradas >= 20) {
            // Inhabilitar botones en la interfaz del servidor
            cPrinc.getcVentana().inhabilitarBotonesPartida();

            // Notificar a todos los jugadores
            for (ServidorThread jugador : clientesActivos) {
                jugador.enviaMsg(jugador.getJugadorVO().getNombre(), "El Juego se ha terminado :)");
            }
        }
    }


     /**
     * Calcula las eficiencias de los jugadores, determina al ganador y envía los resultados finales.
     * <p>
     * Este método se ejecuta al finalizar la partida. Calcula la eficiencia de cada jugador como
     * el cociente entre aciertos e intentos, identifica al jugador con mayor eficiencia como ganador,
     * y luego:
     * <ul>
     *   <li>Muestra un mensaje con estadísticas en la consola del servidor.</li>
     *   <li>Envía el resumen de resultados a todos los jugadores conectados.</li>
     *   <li>Envía un mensaje de felicitación al ganador.</li>
     *   <li>Envía un mensaje de cierre a los demás jugadores.</li>
     * </ul>
     * </p>
     */
    public void enviarResultados() {
        // Determinar ganador

        for (ServidorThread jugador : clientesActivos) {
            jugador.getJugadorVO().setEficiencia((double) jugador.getJugadorVO().getAciertos() / jugador.getJugadorVO().getIntentos());
        }

        ServidorThread ganador = null;
        double MayorEficiencia = -1;

        for (ServidorThread jugador : clientesActivos) {
            if (jugador.getJugadorVO().getEficiencia() > MayorEficiencia) {
                MayorEficiencia = jugador.getJugadorVO().getEficiencia();
                ganador = jugador;
            }
        }

        // MENSAJES MEJORADOS DE FIN DE PARTIDA:
        if (ganador != null) {
            // 1. Mensaje público de fin de partida con estadísticas
            String mensajeFinal = " ------------------------- \n¡PARTIDA TERMINADA! \n"
                    + "Ganador: " + ganador.getJugadorVO().getNombre()
                    + " con eficiencia :" + ganador.getJugadorVO().getEficiencia() + "\n"
                    + "Estadísticas finales:";

            for (ServidorThread jugador : clientesActivos) {
                mensajeFinal += "\n- " + jugador.getJugadorVO().getNombre()
                        + ": aciertos->" + jugador.getJugadorVO().getAciertos()
                        + " intentos->" + jugador.getJugadorVO().getIntentos()
                        + " eficiencia->" + jugador.getJugadorVO().getEficiencia();
            }

            cPrinc.getcVentana().getvServidor().mostrar(mensajeFinal);

            // Enviar estadísticas a todos
            for (ServidorThread jugador : clientesActivos) {
                jugador.enviaMsg(mensajeFinal);
            }

            // 2. Mensaje privado personalizado al ganador
            ganador.enviaMsg(ganador.getJugadorVO().getNombre(), " ¡FELICIDADES! Has ganado la partida con eficiencia->" + MayorEficiencia + ". \n¡Eres el maestro de la memoria!");

            // 3. Mensaje privado personalizado a los demás jugadores
            for (ServidorThread jugador : clientesActivos) {
                if (!jugador.equals(ganador)) {
                    jugador.enviaMsg(jugador.getJugadorVO().getNombre(), "Partida terminada. " + ganador.getJugadorVO().getNombre()
                            + " ha ganado. \n¡Mejor suerte la próxima vez!");
                }
            }
        }

    }

        /**
     * Reinicia los contadores de aciertos e intentos para todos los jugadores activos.
     * <p>
     * Este método se utiliza para preparar una nueva partida o reiniciar el estado de los jugadores,
     * estableciendo sus aciertos e intentos en cero.
     * </p>
     */
    public void vaciarAciertosEIntentos() {
        for (ServidorThread jugador : clientesActivos) {
            jugador.getJugadorVO().setIntentos(0);
            jugador.getJugadorVO().setAciertos(0);
        }
    }

        /**
     * Informa a todos los jugadores cuál es el turno actual y envía instrucciones específicas al jugador activo.
     * <p>
     * Este método:
     * <ul>
     *   <li>Envía un mensaje indicando el número de turno y el nombre del jugador al que le corresponde jugar.</li>
     *   <li>Al jugador activo se le solicita ingresar las coordenadas de dos cartas y se le habilita su entrada.</li>
     *   <li>A los demás jugadores se les notifica que no es su turno y se les inhabilita la entrada.</li>
     * </ul>
     * </p>
     */
    public void avisarTurnos() {
        clientesActivos.get(turnoActual - 1).enviaMsg(" ");
        clientesActivos.get(turnoActual - 1).enviaMsg("Turno N°: " + getTurnoActual() + ", para jugador -> " + clientesActivos.get(cPrinc.getcServidor().getTurnoActual() - 1).getJugadorVO().getNombre());
        for (ServidorThread jugador : clientesActivos) {
            if (jugador.getJugadorVO().getNombre().equalsIgnoreCase(clientesActivos.get(getTurnoActual() - 1).getJugadorVO().getNombre())) {
                clientesActivos.get(turnoActual - 1).enviaMsg(jugador.getJugadorVO().getNombre(), "Ingresa las coordenadas de 2 cartas.");
                clientesActivos.get(turnoActual - 1).enviaMsg(jugador.getJugadorVO().getNombre(), "habilitar");
            } else {

                clientesActivos.get(turnoActual - 1).enviaMsg(jugador.getJugadorVO().getNombre(), "Aun no es tu turno, Espera");
                clientesActivos.get(turnoActual - 1).enviaMsg(jugador.getJugadorVO().getNombre(), "inhabilitar");
            }
        }
    }

        /**
     * Informa al jugador activo que las cartas seleccionadas no forman una pareja.
     * <p>
     * Se envía un mensaje indicando el error y se inhabilita la entrada del jugador.
     * </p>
     *
     * @param carta1 Índice de la primera carta seleccionada.
     * @param carta2 Índice de la segunda carta seleccionada.
     */
    public void avisarError(int carta1, int carta2) {
        clientesActivos.get(turnoActual - 1).enviaMsg("   " + clientesActivos.get(turnoActual - 1).getJugadorVO().getNombre()
                + " se ha equivocado \n" + "   Las cartas " + carta1 + " " + carta2 + " no coinciden");
        clientesActivos.get(turnoActual - 1).enviaMsg(clientesActivos.get(turnoActual - 1).getJugadorVO().getNombre(), "inhabilitar");
    }

    /**
     * Informa al jugador activo que ha escrito coordenadas inválidas.
     * <p>
     * El mensaje se acompaña de una orden para inhabilitar la entrada.
     * </p>
     */
    public void avisarErrorDeEscritura() {
        clientesActivos.get(turnoActual - 1).enviaMsg("   " + clientesActivos.get(turnoActual - 1).getJugadorVO().getNombre()
                + " se ha equivocado \n" + "   Ha introducido coordenadas inválidas");
        clientesActivos.get(turnoActual - 1).enviaMsg(clientesActivos.get(turnoActual - 1).getJugadorVO().getNombre(), "inhabilitar");
    }

    /**
     * Informa al jugador activo que ha acertado al seleccionar una pareja correcta.
     *
     * @param carta1 Índice de la primera carta seleccionada.
     * @param carta2 Índice de la segunda carta seleccionada.
     */
    public void avisarAcierto(int carta1, int carta2) {
        clientesActivos.get(turnoActual - 1).enviaMsg("   " + clientesActivos.get(turnoActual - 1).getJugadorVO().getNombre()
                + " ha acertado \n" + "   Las cartas " + carta1 + " " + carta2 + " son pareja");
    }

    /**
     * Envía un mensaje a todos los jugadores indicando que el juego ha comenzado.
     * <p>
     * Este mensaje se envía cuando se han validado ambos jugadores y el servidor inicia la partida.
     * </p>
     */
    public void enviarMensajeJuegoIniciado() {
        for (ServidorThread jugador : clientesActivos) {
            jugador.enviaMsg(jugador.getJugadorVO().getNombre(), "El Juego ha iniciado :)");
        }
    }



        /**
     * Método reservado para asignar los turnos iniciales a los jugadores.
     * <p>
     * Actualmente no implementado. Puede utilizarse en el futuro para definir
     * aleatoriamente el jugador que empieza o establecer un orden específico.
     * </p>
     */
    public void asignarTurnos() {

    }

    /**
     * Obtiene el número del turno actual.
     *
     * @return Un número entero que indica a cuál jugador le corresponde jugar.
     */
    public int getTurnoActual() {
        return turnoActual;
    }

    /**
     * Establece el número del turno actual.
     *
     * @param turnoActual Número que representa al jugador que debe jugar.
     */
    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    /**
     * Obtiene la cantidad de parejas de cartas encontradas en la partida.
     *
     * @return Número de cartas encontradas correctamente por todos los jugadores.
     */
    public int getCartasEncontradas() {
        return cartasEncontradas;
    }

    /**
     * Establece la cantidad de parejas de cartas encontradas en la partida.
     *
     * @param cartasEncontradas Número total de cartas que han sido emparejadas correctamente.
     */
    public void setCartasEncontradas(int cartasEncontradas) {
        this.cartasEncontradas = cartasEncontradas;
    }


        /**
     * Obtiene el objeto {@code JugadorDAO} utilizado para acceder a los datos de los jugadores.
     *
     * @return Instancia actual de {@code JugadorDAO}.
     */
    public JugadorDAO getJugadorDAO() {
        return jugadorDAO;
    }

    /**
     * Establece el objeto {@code JugadorDAO} que se utilizará para consultar los datos de los jugadores.
     *
     * @param jugadorDAO Objeto {@code JugadorDAO} que manejará el acceso a la base de datos.
     */
    public void setJugadorDAO(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
    }


}
