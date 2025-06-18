/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.modelo.Carta;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.Timer;

/**
 * Controlador principal del servidor. Se encarga de inicializar y coordinar los
 * subcontroladores del servidor y la interfaz gráfica asociada.
 *
 * @author hailen
 */
public class ControlPrincipalServidor {

    // Controlador encargado de gestionar la lógica del servidor
    private ControlServidor cServidor;

    /** Controlador encargado de manejar las acciones sobre el tablero de juego. */
    private ControlTablero cTablero;
    
    /** Indica si la partida ha sido iniciada o no. */
    private boolean isPartidaIniciada = false;

//    private int pasarPort1;
//    private int pasarPort2;
// Controlador de la ventana de interfaz gráfica del servidor
    private ControlVentanaServidor cVentana;
    
        /**
         * Mapa que relaciona la posición (índice del botón) con la carta correspondiente en el tablero.
     * <p>
     * Este mapa se utiliza para asociar cada botón del tablero con una instancia de {@code Carta},
     * facilitando así la verificación de parejas y la gestión de las jugadas durante la partida.
     * </p>
     */
    private Map<Integer, Carta> mapaBotonCarta = new HashMap<>();

    /**
     * Constructor de ControlPrincipalServidor.
     *
     * Inicializa el controlador de la ventana del servidor y el controlador de
     * la lógica del servidor. Luego, inicia la ejecución del servidor.
     */
    public ControlPrincipalServidor() {

        try {

            cVentana = new ControlVentanaServidor(this);
            cTablero = new ControlTablero(this);
            cServidor = new ControlServidor(this);

            cServidor.inicializarDesdeProperties(cVentana.getDatosPasar());
            cServidor.runServer();
        } catch (SQLException ex) {

            System.getLogger(ControlPrincipalServidor.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

//    public void iniciarPartida() {
    ////        cServidor.setCartasEncontradas(0);
////        cServidor.vaciarAciertosEIntentos();
//        cServidor.cerrarEntradasDeJugadores();
//        cServidor.enviarMensajeJuegoIniciado();
//        cTablero.generarCartas();
//        asignarCartasABotones();
//        cVentana.activarBotonesCartas();
//
//        asignarNombresABotones();
//        cVentana.siguienteTurnoEnVista(cServidor.getTurnoActual());
//        // asignar turnos a jugadores
//
//    }
//    
    
        /**
     * Inicia la partida del juego Concéntrese en el servidor.
     * <p>
     * Este método marca el inicio de la partida, notificando a los jugadores que el juego ha comenzado,
     * generando las cartas del tablero, asignándolas a los botones y actualizando la vista del servidor.
     * También activa los botones para que los jugadores puedan comenzar a jugar y muestra el turno inicial.
     * </p>
     */
    public void iniciarPartida() {
        
        isPartidaIniciada = true;
        // Primero cerramos las entradas ANTES de hacer cualquier otra cosa
//        cServidor.cerrarEntradasDeJugadores();

        // Pequeña pausa para asegurar que el cierre se complete
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        // Ahora continuamos con el resto de la lógica
        cServidor.enviarMensajeJuegoIniciado();
        cTablero.generarCartas();
        asignarCartasABotones();
        cVentana.activarBotonesCartas();
//        asignarNombresABotones();
        cVentana.siguienteTurnoEnVista(cServidor.getTurnoActual());

        // Informar en la vista del servidor
        cVentana.getvServidor().mostrar("¡PARTIDA INICIADA! No se aceptarán más jugadores.");
    }

    /**
     * Devuelve el controlador del servidor.
     *
     * @return El controlador de la lógica del servidor.
     */
    public ControlServidor getcServidor() {
        return cServidor;
    }

    /**
     * Devuelve el controlador de la ventana del servidor.
     *
     * @return El controlador de la interfaz gráfica del servidor.
     */
    public ControlVentanaServidor getcVentana() {
        return cVentana;
    }

        /**
     * Asigna las cartas generadas en el tablero a cada botón del tablero en la interfaz.
     * <p>
     * Este método limpia el mapa actual de botones y cartas, y luego recorre la matriz
     * de cartas del tablero y la matriz de índices de botones de la vista del servidor.
     * Cada índice de botón se asocia con la carta correspondiente en el mapa
     * {@code mapaBotonCarta} para facilitar su posterior identificación durante la partida.
     * </p>
     */
    public void asignarCartasABotones() {
        mapaBotonCarta.clear(); // importante si se reinicia

        //JButton[][] botones = cVentana.getvServidor().getBotones();
        int[][] botonesIndice = cVentana.getvServidor().getIndiceBotones();
        Carta[][] matrizCartas = cTablero.getMatrizCartas();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                int indiceBoton = botonesIndice[i][j];
                System.out.println(indiceBoton);
                Carta carta = matrizCartas[i][j];
                mapaBotonCarta.put(indiceBoton, carta);
                System.out.println("aaaaaaaaaaaaaaaaaa" + mapaBotonCarta.get(indiceBoton));
                System.out.println(mapaBotonCarta.get(indiceBoton).toString());
                // Aquí puedes asignar el nuevo ActionListener o resetear el texto
//                boton.setText("");
//                boton.setEnabled(true);
            }
        }
    }

        /**
     * Verifica si las cartas seleccionadas por los botones forman una pareja.
     * <p>
     * Compara las cartas asociadas a los botones recibidos. Si tienen el mismo ID,
     * se considera una pareja correcta: se notifica al servidor, se muestra un mensaje en la vista
     * y se incrementa el contador de aciertos. Si no coinciden, se notifica el error, se incrementa
     * el contador de intentos, y tras una breve pausa, las cartas se ocultan nuevamente.
     * </p>
     *
     * @param btn1 Índice del primer botón seleccionado.
     * @param btn2 Índice del segundo botón seleccionado.
     */
    public void verificarPareja(int btn1, int btn2) {
        Carta c1 = mapaBotonCarta.get(btn1);
        Carta c2 = mapaBotonCarta.get(btn2);
        System.out.println(c1);
        System.out.println(c2);
        if (c1.getId() == c2.getId()) {
            // Pareja correcta
            System.out.println("roorecootoot");
            cServidor.avisarAcierto(btn1, btn2);
            cVentana.mostrarJDialogParejaEncontrada();
            System.out.println("corecorotooo despueeeeeeeeees");

            cServidor.incrementarAcierto();
        } else {
            cServidor.avisarError(btn1, btn2);
            cServidor.incrementarIntento();
            System.out.println("no son pareja jajajajajajaj");

            // No es pareja → volver a ocultar después de un momento
            Timer timer = new Timer(1000, e -> {
                //resetear a la imagen volteada, no lo he hecho
                cVentana.resetearParejaBotones(btn1, btn2);
            });
            timer.setRepeats(false);
            timer.start();

        }

        c1 = null;
        c2 = null;
        cVentana.setPrimerBoton(10000);
    }

        /**
     * Obtiene el mapa que asocia los índices de los botones con sus respectivas cartas.
     *
     * @return Mapa que relaciona cada botón con una instancia de {@code Carta}.
     */
    public Map<Integer, Carta> getMapaBotonCarta() {
        return mapaBotonCarta;
    }

    /**
     * Establece el mapa que asocia los índices de los botones con sus respectivas cartas.
     *
     * @param mapaBotonCarta Mapa a asignar, donde cada índice representa un botón del tablero
     *                       y cada valor es una instancia de {@code Carta}.
     */
    public void setMapaBotonCarta(Map<Integer, Carta> mapaBotonCarta) {
        this.mapaBotonCarta = mapaBotonCarta;
    }


        /**
     * Inicializa una lista de configuraciones a partir de un archivo de propiedades.
     * <p>
     * Este método lee un archivo `.properties` que contiene información de configuración,
     * incluyendo dos propiedades de red (props1 y props2), además de hasta seis pares de
     * usuarios y contraseñas (usuario1-clave1 hasta usuario6-clave6). Todos estos valores
     * se agregan a una lista y se devuelven en el orden en que se leen.
     * </p>
     *
     * @param archivo Archivo de propiedades desde el cual se leen los parámetros de configuración.
     * @return Una lista con las propiedades extraídas del archivo: props1, props2,
     *         y los pares de usuario y clave del 1 al 6. Si el archivo es nulo o no se puede leer,
     *         la lista se retorna vacía.
     * @throws SQLException Esta excepción está declarada pero actualmente no se lanza dentro del método.
     */
    public ArrayList<String> inicializarPuertosDesdeProps(File archivo) throws SQLException {
        ArrayList<String> datos = new ArrayList<>();
        if (archivo != null) {
            try (FileInputStream fis = new FileInputStream(archivo)) {
                Properties props = new Properties();
                props.load(fis);

// Guardar los puertos
                datos.add(props.getProperty("props1"));
                datos.add(props.getProperty("props2"));

// Guardar usuarios y claves
                for (int i = 1; i <= 6; i++) {
                    datos.add(props.getProperty("usuario" + i));
                    datos.add(props.getProperty("clave" + i));

                }
            } catch (IOException e) {

            }
        } else {

        }
        return datos;
    }

//    public void asignarNombresABotones() {
//        int i = 1;
//        for (ServidorThread jugador : ControlServidor.clientesActivos) {
//            cVentana.habilitarBotonesAlIniciarSwitch(jugador.getJugadorVO().getNombre(), i);
//            i++;
//        }
//    }

        /**
     * Obtiene el controlador del tablero del juego.
     *
     * @return Instancia de {@code ControlTablero} que gestiona la lógica del tablero.
     */
    public ControlTablero getcTablero() {
        return cTablero;
    }

    /**
     * Establece el controlador del tablero del juego.
     *
     * @param cTablero Objeto {@code ControlTablero} que se asignará para manejar la lógica del tablero.
     */
    public void setcTablero(ControlTablero cTablero) {
        this.cTablero = cTablero;
    }

    
    

//    public int getPasarPort1() {
//        return pasarPort1;
//    }
//
//    public void setPasarPort1(int pasarPort1) {
//        this.pasarPort1 = pasarPort1;
//    }
//
//    public int getPasarPort2() {
//        return pasarPort2;
//    }
//
//    public void setPasarPort2(int pasarPort2) {
//        this.pasarPort2 = pasarPort2;
//    }

        /**
     * Verifica si la partida ha sido iniciada.
     *
     * @return {@code true} si la partida ya ha comenzado; {@code false} en caso contrario.
     */
    public boolean isIsPartidaIniciada() {
        return isPartidaIniciada;
    }

    /**
     * Establece el estado de inicio de la partida.
     *
     * @param isPartidaIniciada Valor booleano que indica si la partida ha comenzado ({@code true})
     *                          o aún no ha sido iniciada ({@code false}).
     */
    public void setIsPartidaIniciada(boolean isPartidaIniciada) {
        this.isPartidaIniciada = isPartidaIniciada;
    }

}
