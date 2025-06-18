/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.control;

import edu.progavud.parcial2pa.modelo.Carta;
import edu.progavud.parcial2pa.vista.VistaServidor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Controlador de la interfaz gráfica del servidor. Se encarga de manejar los
 * eventos de la vista {@link VistaServidor} y coordinar acciones con el
 * controlador principal del servidor.
 *
 * @author hailen
 */
public class ControlVentanaServidor implements ActionListener {

    // Controlador principal del servidor
    private ControlPrincipalServidor cPrinc;

    /**
     * Vista gráfica del servidor.
     * <p>
     * Representa la interfaz de usuario que muestra el tablero del juego y los mensajes
     * relacionados con la partida desde el lado del servidor.
     * </p>
     */
    private VistaServidor vServidor;

    /**
     * Índice del primer botón seleccionado por el jugador.
     * <p>
     * Se usa para llevar el control del primer clic del turno actual.
     * El valor por defecto (10000) indica que aún no se ha seleccionado ningún botón.
     * </p>
     */
    private int primerBoton = 10000;

    /**
     * Bandera que indica si se está esperando que el jugador seleccione la segunda carta del turno.
     */
    private boolean esperandoSegundo = false;

    /**
     * Lista de datos cargados desde un archivo de propiedades.
     * <p>
     * Contiene información como puertos, usuarios y contraseñas necesarias para la conexión y autenticación.
     * </p>
     */
    private ArrayList<String> datosPasar;


    /**
     * Constructor de ControlVentanaServidor.
     *
     * Inicializa la vista del servidor y la vincula a este controlador.
     *
     * @param cPrinc Controlador principal del servidor.
     */
    public ControlVentanaServidor(ControlPrincipalServidor cPrinc) throws SQLException {
        this.cPrinc = cPrinc;
        cargarVistaServidor();
        datosPasar = cPrinc.inicializarPuertosDesdeProps(vServidor.rutaJfileChooserPorts());

    }

    /**
     * Maneja los eventos generados por la interfaz gráfica del servidor.
     *
     * <p>
     * Actualmente no tiene acciones implementadas, pero está listo para
     * procesar eventos según el comando recibido.</p>
     *
     * @param e Evento de acción generado por un componente.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "INICIAR_JUEGO":
                vServidor.getBtnIniciarJuego().setEnabled(false);
//                vServidor.getBtnIniciarJuego().setVisible(false);
                cPrinc.iniciarPartida();

// También puedes inicializar cartas aquí si tienes lógica en ControlTablero
                break;
            case "AUMENTAR_INTENTO":
                cPrinc.getcServidor().avisarErrorDeEscritura();
                cPrinc.getcServidor().incrementarIntento();

                break;
            case "ENVIAR_RESULTADOS":
                vServidor.getBtnEnviarResultados().setEnabled(false);
                cPrinc.getcServidor().enviarResultados();
                break;
            case "COORDS_JUG1":
                cPrinc.getcServidor().avisarTurnos();

                break;
            case "COORDS_JUG2":
                cPrinc.getcServidor().avisarTurnos();
                break;
            case "COORDS_JUG3":
                cPrinc.getcServidor().avisarTurnos();
                break;
            case "COORDS_JUG4":
                cPrinc.getcServidor().avisarTurnos();
                break;

            case "BOTON_40":
                manejarClick(40);
                vServidor.getBtnCarta40().setEnabled(true);
                break;
            case "BOTON_1":
                manejarClick(1);
                vServidor.getBtnCarta1().setEnabled(true);
                break;
            case "BOTON_2":
                manejarClick(2);
                vServidor.getBtnCarta2().setEnabled(false);
                break;
            case "BOTON_3":
                manejarClick(3);
                vServidor.getBtnCarta3().setEnabled(false);
                break;
            case "BOTON_4":
                manejarClick(4);
                vServidor.getBtnCarta4().setEnabled(false);
                break;
            case "BOTON_5":
                manejarClick(5);
                vServidor.getBtnCarta5().setEnabled(false);
                break;
            case "BOTON_6":
                manejarClick(6);
                vServidor.getBtnCarta6().setEnabled(false);
                break;
            case "BOTON_7":
                manejarClick(7);
                vServidor.getBtnCarta7().setEnabled(false);
                break;
            case "BOTON_8":
                manejarClick(8);
                vServidor.getBtnCarta8().setEnabled(false);
                break;
            case "BOTON_9":
                manejarClick(9);
                vServidor.getBtnCarta9().setEnabled(false);
                break;
            case "BOTON_10":
                manejarClick(10);
                vServidor.getBtnCarta10().setEnabled(false);
                break;
            case "BOTON_11":
                manejarClick(11);
                vServidor.getBtnCarta11().setEnabled(false);
                break;
            case "BOTON_12":
                manejarClick(12);
                vServidor.getBtnCarta12().setEnabled(false);
                break;
            case "BOTON_13":
                manejarClick(13);
                vServidor.getBtnCarta13().setEnabled(false);
                break;
            case "BOTON_14":
                manejarClick(14);
                vServidor.getBtnCarta14().setEnabled(false);
                break;
            case "BOTON_15":
                manejarClick(15);
                vServidor.getBtnCarta15().setEnabled(false);
                break;
            case "BOTON_16":
                manejarClick(16);
                vServidor.getBtnCarta16().setEnabled(false);
                break;
            case "BOTON_17":
                manejarClick(17);
                vServidor.getBtnCarta17().setEnabled(false);
                break;
            case "BOTON_18":
                manejarClick(18);
                vServidor.getBtnCarta18().setEnabled(false);
                break;
            case "BOTON_19":
                manejarClick(19);
                vServidor.getBtnCarta19().setEnabled(false);
                break;
            case "BOTON_20":
                manejarClick(20);
                vServidor.getBtnCarta20().setEnabled(false);
                break;
            case "BOTON_21":
                manejarClick(21);
                vServidor.getBtnCarta21().setEnabled(false);
                break;
            case "BOTON_22":
                manejarClick(22);
                vServidor.getBtnCarta22().setEnabled(false);
                break;
            case "BOTON_23":
                manejarClick(23);
                vServidor.getBtnCarta23().setEnabled(false);
                break;
            case "BOTON_24":
                manejarClick(24);
                vServidor.getBtnCarta24().setEnabled(false);
                break;
            case "BOTON_25":
                manejarClick(25);
                vServidor.getBtnCarta25().setEnabled(false);
                break;
            case "BOTON_26":
                manejarClick(26);
                vServidor.getBtnCarta26().setEnabled(false);
                break;
            case "BOTON_27":
                manejarClick(27);
                vServidor.getBtnCarta27().setEnabled(false);
                break;
            case "BOTON_28":
                manejarClick(28);
                vServidor.getBtnCarta28().setEnabled(false);
                break;
            case "BOTON_29":
                manejarClick(29);
                vServidor.getBtnCarta29().setEnabled(false);
                break;
            case "BOTON_30":
                manejarClick(30);
                vServidor.getBtnCarta30().setEnabled(false);
                break;
            case "BOTON_31":
                manejarClick(31);
                vServidor.getBtnCarta31().setEnabled(false);
                break;
            case "BOTON_32":
                manejarClick(32);
                vServidor.getBtnCarta32().setEnabled(false);
                break;
            case "BOTON_33":
                manejarClick(33);
                vServidor.getBtnCarta33().setEnabled(false);
                break;
            case "BOTON_34":
                manejarClick(34);
                vServidor.getBtnCarta34().setEnabled(false);
                break;
            case "BOTON_35":
                manejarClick(35);
                vServidor.getBtnCarta35().setEnabled(false);
                break;
            case "BOTON_36":
                manejarClick(36);
                vServidor.getBtnCarta36().setEnabled(false);
                break;
            case "BOTON_37":
                manejarClick(37);
                vServidor.getBtnCarta37().setEnabled(false);
                break;
            case "BOTON_38":
                manejarClick(38);
                vServidor.getBtnCarta38().setEnabled(false);
                break;
            case "BOTON_39":
                manejarClick(39);
                vServidor.getBtnCarta39().setEnabled(false);
                break;
            default:
                System.out.println("Comando desconocido: " + comando);
        }

    }

        /**
     * Carga y muestra la vista gráfica del servidor.
     * <p>
     * Este método crea una nueva instancia de {@code VistaServidor}, le asigna
     * los respectivos {@code ActionListener} a los botones, y la hace visible en pantalla.
     * </p>
     */
    public void cargarVistaServidor() {
        vServidor = new VistaServidor();
        asignarActionListeners();
        vServidor.setVisible(true);
    }

        /**
     * Asigna los {@code ActionListener} a todos los botones de la vista del servidor.
     * <p>
     * Este método:
     * <ul>
     *   <li>Asocia los 40 botones del tablero con un comando único y su listener.</li>
     *   <li>Configura botones de control como "Iniciar Juego", "Enviar Resultados" y "Aumentar Intento".</li>
     *   <li>Asigna eventos a los botones de coordenadas de jugadores (hasta 4 jugadores).</li>
     *   <li>Oculta y desactiva todos los botones de control y etiquetas de estadísticas al inicio.</li>
     *   <li>Desactiva todos los botones del tablero para evitar interacción antes del inicio del juego.</li>
     * </ul>
     * Esto garantiza que la interfaz inicie en un estado controlado y solo habilite componentes cuando sea necesario.
     * </p>
     */
    public void asignarActionListeners() {
        for (int i = 1; i <= 40; i++) {
            JButton boton = obtenerBotonCarta(i);
            if (boton != null) {
                boton.setActionCommand("BOTON_" + i);
                boton.addActionListener(this);
            }
        }

        vServidor.getBtnEnviarResultados().setActionCommand("ENVIAR_RESULTADOS");
        vServidor.getBtnEnviarResultados().addActionListener(this);

        vServidor.getBtnAumentarIntento().setActionCommand("AUMENTAR_INTENTO");
        vServidor.getBtnAumentarIntento().addActionListener(this);

        vServidor.getBtnIniciarJuego().setActionCommand("INICIAR_JUEGO");
        vServidor.getBtnIniciarJuego().addActionListener(this);

        vServidor.getBtnJug1().setActionCommand("COORDS_JUG1");
        vServidor.getBtnJug1().addActionListener(this);
        vServidor.getBtnJug2().setActionCommand("COORDS_JUG2");
        vServidor.getBtnJug2().addActionListener(this);
        vServidor.getBtnJug3().setActionCommand("COORDS_JUG3");
        vServidor.getBtnJug3().addActionListener(this);
        vServidor.getBtnJug4().setActionCommand("COORDS_JUG4");
        vServidor.getBtnJug4().addActionListener(this);

        vServidor.getBtnAumentarIntento().setVisible(false);
        vServidor.getBtnEnviarResultados().setVisible(false);
        vServidor.getBtnIniciarJuego().setVisible(false);
        vServidor.getBtnJug1().setVisible(false);
        vServidor.getBtnJug2().setVisible(false);
        vServidor.getBtnJug3().setVisible(false);
        vServidor.getBtnJug4().setVisible(false);

        vServidor.getBtnAumentarIntento().setEnabled(false);
        vServidor.getBtnEnviarResultados().setEnabled(false);
        vServidor.getBtnIniciarJuego().setEnabled(false);

        vServidor.getBtnJug1().setEnabled(false);
        vServidor.getBtnJug2().setEnabled(false);
        vServidor.getBtnJug3().setEnabled(false);
        vServidor.getBtnJug4().setEnabled(false);
        vServidor.getLblAciertosJug1().setVisible(false);
        vServidor.getLblAciertosJug2().setVisible(false);
        vServidor.getLblAciertosJug3().setVisible(false);
        vServidor.getLblAciertosJug4().setVisible(false);
        vServidor.getLblIntentosJug1().setVisible(false);
        vServidor.getLblIntentosJug2().setVisible(false);
        vServidor.getLblIntentosJug3().setVisible(false);
        vServidor.getLblIntentosJug4().setVisible(false);
        for (int i = 1; i <= 40; i++) {
            JButton boton = obtenerBotonCarta(i);
            if (boton != null) {
                boton.setEnabled(false);
            }
        }

    }

        /**
     * Retorna el botón del tablero correspondiente al número de carta indicado.
     * <p>
     * Este método utiliza una estructura {@code switch} para acceder a uno de los
     * 40 botones de carta presentes en la vista del servidor. Si el número proporcionado
     * no está en el rango 1 a 40, el método retorna {@code null}.
     * </p>
     *
     * @param numero Número de carta (entre 1 y 40) cuyo botón se desea obtener.
     * @return Objeto {@code JButton} correspondiente a la carta especificada, o {@code null} si el número es inválido.
     */
    private JButton obtenerBotonCarta(int numero) {
        switch (numero) {
            case 1:
                return vServidor.getBtnCarta1();
            case 2:
                return vServidor.getBtnCarta2();
            case 3:
                return vServidor.getBtnCarta3();
            case 4:
                return vServidor.getBtnCarta4();
            case 5:
                return vServidor.getBtnCarta5();
            case 6:
                return vServidor.getBtnCarta6();
            case 7:
                return vServidor.getBtnCarta7();
            case 8:
                return vServidor.getBtnCarta8();
            case 9:
                return vServidor.getBtnCarta9();
            case 10:
                return vServidor.getBtnCarta10();
            case 11:
                return vServidor.getBtnCarta11();
            case 12:
                return vServidor.getBtnCarta12();
            case 13:
                return vServidor.getBtnCarta13();
            case 14:
                return vServidor.getBtnCarta14();
            case 15:
                return vServidor.getBtnCarta15();
            case 16:
                return vServidor.getBtnCarta16();
            case 17:
                return vServidor.getBtnCarta17();
            case 18:
                return vServidor.getBtnCarta18();
            case 19:
                return vServidor.getBtnCarta19();
            case 20:
                return vServidor.getBtnCarta20();
            case 21:
                return vServidor.getBtnCarta21();
            case 22:
                return vServidor.getBtnCarta22();
            case 23:
                return vServidor.getBtnCarta23();
            case 24:
                return vServidor.getBtnCarta24();
            case 25:
                return vServidor.getBtnCarta25();
            case 26:
                return vServidor.getBtnCarta26();
            case 27:
                return vServidor.getBtnCarta27();
            case 28:
                return vServidor.getBtnCarta28();
            case 29:
                return vServidor.getBtnCarta29();
            case 30:
                return vServidor.getBtnCarta30();
            case 31:
                return vServidor.getBtnCarta31();
            case 32:
                return vServidor.getBtnCarta32();
            case 33:
                return vServidor.getBtnCarta33();
            case 34:
                return vServidor.getBtnCarta34();
            case 35:
                return vServidor.getBtnCarta35();
            case 36:
                return vServidor.getBtnCarta36();
            case 37:
                return vServidor.getBtnCarta37();
            case 38:
                return vServidor.getBtnCarta38();
            case 39:
                return vServidor.getBtnCarta39();
            case 40:
                return vServidor.getBtnCarta40();
            default:
                return null;
        }
    }

    /**
     * Devuelve la vista gráfica del servidor.
     *
     * @return Objeto {@link VistaServidor} asociado.
     */
    public VistaServidor getvServidor() {
        return vServidor;
    }

    /**
     * Asigna la vista gráfica del servidor.
     *
     * @param vServidor Objeto {@link VistaServidor} a asignar.
     */
    public void setvServidor(VistaServidor vServidor) {
        this.vServidor = vServidor;
    }

        /**
     * Maneja el clic sobre una carta del tablero y gestiona la lógica de selección de pareja.
     * <p>
     * Si es el primer clic del turno, se guarda el índice del botón y se muestra la imagen de la carta.
     * Si es el segundo clic, se muestra la segunda carta, se deshabilita su botón,
     * y se llama al método {@code verificarPareja()} para validar si forman una pareja.
     * </p>
     *
     * @param btn Número del botón que ha sido presionado (de 1 a 40).
     */
    public void manejarClick(int btn) {
//        boton.setIcon(new ImageIcon(cPrinc.getMapaBotonCarta().get(boton).getRutaImg()));
//        boton.setEnabled(false);

        if (!esperandoSegundo) {
            primerBoton = btn;
            System.out.println(cPrinc.getMapaBotonCarta().get(primerBoton).getRutaImg());
            obtenerBotonCarta(primerBoton).setIcon(vServidor.cargarCarta(cPrinc.getMapaBotonCarta().get(primerBoton).getRutaImg()));
            obtenerBotonCarta(primerBoton).setDisabledIcon(vServidor.cargarCarta(cPrinc.getMapaBotonCarta().get(primerBoton).getRutaImg()));
            obtenerBotonCarta(primerBoton).setEnabled(false);
            System.out.println(primerBoton);
            esperandoSegundo = true;
        } else {
            int segundoBoton = btn;
            obtenerBotonCarta(segundoBoton).setIcon(vServidor.cargarCarta(cPrinc.getMapaBotonCarta().get(segundoBoton).getRutaImg()));
            obtenerBotonCarta(segundoBoton).setDisabledIcon(vServidor.cargarCarta(cPrinc.getMapaBotonCarta().get(segundoBoton).getRutaImg()));
            System.out.println(segundoBoton);
            obtenerBotonCarta(segundoBoton).setEnabled(false);
            cPrinc.verificarPareja(primerBoton, segundoBoton);
            esperandoSegundo = false;
        }

    }

        /**
     * Muestra un cuadro de diálogo informando que se ha encontrado una pareja correcta.
     * <p>
     * Este método simplemente delega la acción a la vista del servidor,
     * llamando al método {@code mostrarJDialogParejaEncontrada()} de {@code VistaServidor}.
     * </p>
     */
    public void mostrarJDialogParejaEncontrada() {
        vServidor.mostrarJDialogParejaEncontrada();
    }

    // resetear a la imagen volteada
    public void resetearParejaBotones(int btn1, int btn2) {
        obtenerBotonCarta(btn1).setIcon(vServidor.cargarCarta("/cn.jpg"));
        obtenerBotonCarta(btn1).setDisabledIcon(vServidor.cargarCarta("/cn.jpg"));
        obtenerBotonCarta(btn1).setEnabled(true);
        obtenerBotonCarta(btn2).setIcon(vServidor.cargarCarta("/cn.jpg"));
        obtenerBotonCarta(btn2).setDisabledIcon(vServidor.cargarCarta("/cn.jpg"));
        obtenerBotonCarta(btn2).setEnabled(true);
    }

        /**
     * Activa los componentes gráficos básicos para iniciar una partida cuando hay al menos dos jugadores conectados.
     * <p>
     * Si el número de jugadores activos es igual o mayor a 2, se hacen visibles los botones y etiquetas
     * necesarias para gestionar la partida: botones de control, botones por jugador y etiquetas de estadísticas
     * (aciertos e intentos) para los dos primeros jugadores.
     * </p>
     */
    public void activarPartidaBasica() {
        if (ControlServidor.clientesActivos.size() >= 2) {
            vServidor.getBtnAumentarIntento().setVisible(true);
            vServidor.getBtnEnviarResultados().setVisible(true);
            vServidor.getBtnIniciarJuego().setVisible(true);
            vServidor.getBtnJug1().setVisible(true);
            vServidor.getBtnJug2().setVisible(true);

            vServidor.getLblAciertosJug1().setVisible(true);
            vServidor.getLblAciertosJug2().setVisible(true);

            vServidor.getLblIntentosJug1().setVisible(true);
            vServidor.getLblIntentosJug2().setVisible(true);

        }
    }

        /**
     * Activa el botón correspondiente al jugador cuyo turno está en curso.
     * <p>
     * Según el número de jugador recibido, este método habilita el botón asociado
     * en la vista del servidor para que el operador pueda registrar sus coordenadas.
     * </p>
     *
     * @param i Número del jugador cuyo turno se debe activar (1 a 4).
     */
    public void siguienteTurnoEnVista(int i) {
        switch (i) {
            case 1:
                vServidor.getBtnJug1().setEnabled(true);

                break;
            case 2:
                vServidor.getBtnJug2().setEnabled(true);
                break;
            case 3:
                vServidor.getBtnJug3().setEnabled(true);
                break;
            case 4:
                vServidor.getBtnJug4().setEnabled(true);
                break;
        }
    }

//    public void habilitarBotonesAlIniciarSwitch(String nombre, int i) {
//        switch (i) {
//            case 1:
//                getvServidor().getBtnJug1().setVisible(true);
//                getvServidor().getLblAciertosJug1().setVisible(true);
//                getvServidor().getLblIntentosJug1().setVisible(true);
//                getvServidor().getBtnJug1().setText("Pedir coords " + nombre);
//                break;
//            case 2:
//                getvServidor().getBtnJug2().setVisible(true);
//                getvServidor().getLblAciertosJug2().setVisible(true);
//                getvServidor().getLblIntentosJug2().setVisible(true);
//                getvServidor().getBtnJug2().setText("Pedir coords " + nombre);
//                vServidor.getBtnAumentarIntento().setVisible(true);
//                vServidor.getBtnEnviarResultados().setVisible(true);
////                vServidor.getBtnIniciarJuego().setVisible(true);
////                vServidor.getBtnIniciarJuego().setEnabled(true);
//                break;
//            case 3:
//                getvServidor().getBtnJug3().setVisible(true);
//                getvServidor().getLblAciertosJug3().setVisible(true);
//                getvServidor().getLblIntentosJug3().setVisible(true);
//                getvServidor().getBtnJug3().setText("Pedir coords " + nombre);
//                vServidor.getBtnAumentarIntento().setVisible(true);
//                vServidor.getBtnEnviarResultados().setVisible(true);
////                vServidor.getBtnIniciarJuego().setVisible(true);
////                vServidor.getBtnIniciarJuego().setEnabled(true);
//                break;
//            case 4:
//                getvServidor().getBtnJug4().setVisible(true);
//                getvServidor().getLblAciertosJug4().setVisible(true);
//                getvServidor().getLblIntentosJug4().setVisible(true);
//                getvServidor().getBtnJug4().setText("Pedir coords " + nombre);
//                vServidor.getBtnAumentarIntento().setVisible(true);
//                vServidor.getBtnEnviarResultados().setVisible(true);
////                vServidor.getBtnIniciarJuego().setVisible(true);
////                vServidor.getBtnIniciarJuego().setEnabled(true);
//                break;
//        }
//
//    }

        /**
     * Habilita y configura los botones y etiquetas correspondientes al jugador que acaba de unirse,
     * dependiendo del número de jugadores actualmente conectados.
     * <p>
     * Este método:
     * <ul>
     *   <li>Hace visibles los botones y etiquetas de aciertos/intentados del jugador.</li>
     *   <li>Personaliza el texto del botón para mostrar el nombre del jugador.</li>
     *   <li>Activa los botones de control del juego (iniciar, aumentar intento, enviar resultados) a partir de 2 jugadores.</li>
     * </ul>
     * </p>
     *
     * @param nombre Nombre del jugador que se acaba de conectar, usado para personalizar el texto del botón.
     */
    public void habilitarBotonesAlIniciar(String nombre) {
        if (ControlServidor.clientesActivos.size() >= 1 && ControlServidor.clientesActivos.size() < 2) {
            getvServidor().getBtnJug1().setVisible(true);
            getvServidor().getLblAciertosJug1().setVisible(true);
            getvServidor().getLblIntentosJug1().setVisible(true);
            getvServidor().getBtnJug1().setText("Pedir coords " + nombre);
        }
        if (ControlServidor.clientesActivos.size() >= 2 && ControlServidor.clientesActivos.size() < 3) {
            getvServidor().getBtnJug2().setVisible(true);
            getvServidor().getLblAciertosJug2().setVisible(true);
            getvServidor().getLblIntentosJug2().setVisible(true);
            getvServidor().getBtnJug2().setText("Pedir coords " + nombre);
            vServidor.getBtnAumentarIntento().setVisible(true);
            vServidor.getBtnEnviarResultados().setVisible(true);
            vServidor.getBtnIniciarJuego().setVisible(true);
            vServidor.getBtnIniciarJuego().setEnabled(true);
        }

        if (ControlServidor.clientesActivos.size() >= 3 && ControlServidor.clientesActivos.size() < 4) {
            getvServidor().getBtnJug3().setVisible(true);
            getvServidor().getLblAciertosJug3().setVisible(true);
            getvServidor().getLblIntentosJug3().setVisible(true);
            getvServidor().getBtnJug3().setText("Pedir coords " + nombre);
            vServidor.getBtnAumentarIntento().setVisible(true);
            vServidor.getBtnEnviarResultados().setVisible(true);
            vServidor.getBtnIniciarJuego().setVisible(true);
            vServidor.getBtnIniciarJuego().setEnabled(true);
        }
        if (ControlServidor.clientesActivos.size() >= 4) {
            getvServidor().getBtnJug4().setVisible(true);
            getvServidor().getLblAciertosJug4().setVisible(true);
            getvServidor().getLblIntentosJug4().setVisible(true);
            getvServidor().getBtnJug4().setText("Pedir coords " + nombre);
            vServidor.getBtnAumentarIntento().setVisible(true);
            vServidor.getBtnEnviarResultados().setVisible(true);
            vServidor.getBtnIniciarJuego().setVisible(true);
            vServidor.getBtnIniciarJuego().setEnabled(true);
        }
    }

        /**
     * Activa todos los botones del tablero y habilita el botón de aumentar intento.
     * <p>
     * Este método permite que los jugadores puedan comenzar a seleccionar cartas,
     * activando los 40 botones correspondientes a las cartas del juego y el botón
     * de control para aumentar los intentos desde la interfaz del servidor.
     * </p>
     */
    public void activarBotonesCartas() {
        for (int i = 1; i <= 40; i++) {
            JButton boton = obtenerBotonCarta(i);
            if (boton != null) {
                boton.setEnabled(true);
            }
        }
        vServidor.getBtnAumentarIntento().setEnabled(true);
    }

        /**
     * Actualiza en la vista del servidor los valores de aciertos e intentos del jugador actual.
     * <p>
     * Según el número de turno, se actualizan las etiquetas correspondientes del jugador con
     * el número de aciertos e intentos que ha acumulado hasta el momento.
     * </p>
     *
     * @param i Número de aciertos del jugador.
     * @param i2 Número de intentos del jugador.
     * @param turnoActual Número del jugador (1 a 4) cuyo puntaje debe ser actualizado.
     */
    public void aumentarAciertoEnVista(int i, int i2, int turnoActual) {
        switch (turnoActual) {
            case 1:
                vServidor.getLblAciertosJug1().setText("Aciertos: " + i);
                vServidor.getLblIntentosJug1().setText("Intentos: " + i2);
                break;
            case 2:
                vServidor.getLblAciertosJug2().setText("Aciertos: " + i);
                vServidor.getLblIntentosJug2().setText("Intentos: " + i2);
                break;
            case 3:
                vServidor.getLblAciertosJug3().setText("Aciertos: " + i);
                vServidor.getLblIntentosJug3().setText("Intentos: " + i2);
                break;
            case 4:
                vServidor.getLblAciertosJug4().setText("Aciertos: " + i);
                vServidor.getLblIntentosJug4().setText("Intentos: " + i2);
                break;
        }
    }

        /**
     * Actualiza el número de intentos del jugador actual en la vista y desactiva su botón de turno.
     * <p>
     * Según el número de turno recibido, se actualiza la etiqueta correspondiente con el número de intentos
     * y se desactiva el botón asociado al jugador, indicando que ya realizó su jugada.
     * </p>
     *
     * @param i Número de intentos realizados por el jugador.
     * @param turnoActual Número del jugador actual (de 1 a 4).
     */
    public void aumentarIntentosEnVista(int i, int turnoActual) {
        switch (turnoActual) {
            case 1:
                vServidor.getLblIntentosJug1().setText("Intentos: " + i);
                vServidor.getBtnJug1().setEnabled(false);
                break;
            case 2:
                vServidor.getLblIntentosJug2().setText("Intentos: " + i);
                vServidor.getBtnJug2().setEnabled(false);
                break;
            case 3:
                vServidor.getLblIntentosJug3().setText("Intentos: " + i);
                vServidor.getBtnJug3().setEnabled(false);
                break;
            case 4:
                vServidor.getLblIntentosJug4().setText("Intentos: " + i);
                vServidor.getBtnJug4().setEnabled(false);
                break;
        }
    }

       /**
     * Inhabilita todos los botones de la partida en la interfaz del servidor.
     * <p>
     * Este método se ejecuta cuando finaliza el juego. Desactiva los botones de turno de los jugadores,
     * el botón para aumentar intentos, los 40 botones del tablero y el botón de iniciar juego.
     * Finalmente, activa el botón para enviar los resultados finales.
     * </p>
     */
    public void inhabilitarBotonesPartida() {
        vServidor.getBtnJug1().setEnabled(false);
        vServidor.getBtnJug2().setEnabled(false);
        vServidor.getBtnJug3().setEnabled(false);
        vServidor.getBtnJug4().setEnabled(false);
        vServidor.getBtnAumentarIntento().setEnabled(false);
        for (int i = 1; i <= 40; i++) {
            JButton boton = obtenerBotonCarta(i);
            if (boton != null) {
                boton.setEnabled(false);
            }
        }
        vServidor.getBtnIniciarJuego().setEnabled(false);
        vServidor.getBtnEnviarResultados().setEnabled(true);
    }

        /**
     * Obtiene el índice del primer botón seleccionado por el jugador durante el turno.
     *
     * @return Número del primer botón seleccionado (1 a 40). Por defecto puede ser 10000 si aún no se ha seleccionado.
     */
    public int getPrimerBoton() {
        return primerBoton;
    }

    /**
     * Establece el índice del primer botón seleccionado por el jugador.
     *
     * @param primerBoton Número del botón seleccionado como primer clic.
     */
    public void setPrimerBoton(int primerBoton) {
        this.primerBoton = primerBoton;
    }

    /**
     * Indica si se está esperando que el jugador seleccione la segunda carta del turno.
     *
     * @return {@code true} si se espera la segunda selección; {@code false} en caso contrario.
     */
    public boolean isEsperandoSegundo() {
        return esperandoSegundo;
    }

    /**
     * Establece el estado de espera de la segunda carta en un turno.
     *
     * @param esperandoSegundo {@code true} si se debe esperar otra selección; {@code false} si ya se completó el par.
     */
    public void setEsperandoSegundo(boolean esperandoSegundo) {
        this.esperandoSegundo = esperandoSegundo;
    }

    /**
     * Obtiene la lista de datos cargados desde un archivo de propiedades.
     *
     * @return Lista con cadenas que representan datos como puertos, usuarios o claves.
     */
    public ArrayList<String> getDatosPasar() {
        return datosPasar;
    }

    /**
     * Establece la lista de datos cargados desde un archivo de propiedades.
     *
     * @param datosPasar Lista con valores como puertos, usuarios o contraseñas.
     */
    public void setDatosPasar(ArrayList<String> datosPasar) {
        this.datosPasar = datosPasar;
    }
  
    

}
