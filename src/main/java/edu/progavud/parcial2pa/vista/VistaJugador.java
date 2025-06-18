package edu.progavud.parcial2pa.Vista;

import edu.progavud.parcial2pa.control.ControlVentanaJugador;
import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * Ventana principal de la interfaz gráfica del cliente. Permite al usuario
 * enviar mensajes públicos y acceder a la opción de ayuda.
 *
 * @author Julian
 */
public class VistaJugador extends JFrame {

    // Controlador que maneja la lógica asociada a esta ventana
    private ControlVentanaJugador cVentana;

    // Área de texto donde se muestran los mensajes recibidos o enviados
    private JTextArea panMostrar;

    // Campo de texto donde el usuario escribe su mensaje
    private JTextField txtMensage;

    // Botón para enviar el mensaje escrito en el campo de texto
    private JButton butEnviar;

    // Opción del menú que muestra la ayuda del juego
    private JMenuItem itemAyuda;

    // Etiqueta que muestra advertencias o mensajes importantes al usuario
    private JLabel lblAdvertencia;

    // Etiqueta que muestra el nombre del usuario conectado
    private JLabel lblNombreUsuario;

    /**
     * Constructor de la clase VistaJugador.
     *
     * Inicializa la ventana gráfica del jugador, asociando su controlador y
     * configurando sus componentes visuales.
     *
     * @param cVentana instancia del controlador asociado a esta vista.
     */
    public VistaJugador(ControlVentanaJugador cVentana) {
        super("Jugador - Vista"); // Título de la ventana
        this.cVentana = cVentana;

        // Inicializa los componentes gráficos de la ventana
        initializeComponents();

        // Organiza los componentes en el layout de la ventana
        setupLayout();

        // Muestra la ventana
        this.setVisible(true);
    }

    /**
     * Inicializa y configura los componentes gráficos de la vista del jugador.
     *
     * - Configura el área de texto donde se muestran los mensajes recibidos. -
     * Configura el campo de texto donde el usuario puede escribir sus mensajes.
     * - Configura el botón de enviar con efectos visuales al pasar el cursor. -
     * Aplica estilos personalizados (colores, fuentes, bordes) para una
     * apariencia tipo consola.
     */
    private void initializeComponents() {
        txtMensage = new JTextField(30);
        butEnviar = new JButton("Enviar");
        panMostrar = new JTextArea();

        // Configuración del área de mensajes
        panMostrar.setEditable(false);
        panMostrar.setOpaque(true);
        panMostrar.setBackground(new Color(30, 30, 30));
        panMostrar.setForeground(Color.WHITE);
        panMostrar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        panMostrar.setLineWrap(true);
        panMostrar.setWrapStyleWord(true);

        // Configuración del botón de envío
        butEnviar.setBackground(Color.WHITE);
        butEnviar.setForeground(Color.BLACK);
        butEnviar.setFocusPainted(false);
        butEnviar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        butEnviar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Efecto visual al pasar el mouse sobre el botón
        butEnviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                butEnviar.setBackground(Color.BLACK);
                butEnviar.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                butEnviar.setBackground(Color.WHITE);
                butEnviar.setForeground(Color.BLACK);
            }
        });

        // Configuración del campo de texto para escribir mensajes
        txtMensage.setBackground(new Color(40, 40, 40));
        txtMensage.setForeground(new Color(0, 255, 127));
        txtMensage.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
    }

    /**
     * Organiza y configura el diseño gráfico (layout) de los componentes en la
     * interfaz del jugador.
     *
     * - Crea y configura el panel inferior con instrucciones, campo de texto
     * para el mensaje, botón de envío y etiqueta de advertencias. - Crea el
     * panel de nombre del jugador con estilo visual. - Configura el área de
     * visualización de mensajes dentro de un JScrollPane. - Estructura los
     * paneles principales en un BorderLayout. - Añade un menú de ayuda a la
     * ventana. - Define el tamaño, fondo y comportamiento de cierre de la
     * ventana.
     */
    private void setupLayout() {
        JPanel panAbajo = new JPanel(new BorderLayout());
        panAbajo.setBackground(new Color(25, 25, 25));
        JLabel lblInstruccion = new JLabel("  Ingrese 2 coordenadas para elegir la pareja de cartas:");
        lblInstruccion.setForeground(new Color(173, 216, 230)); // Azul claro cartoon
        lblInstruccion.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        panAbajo.add(lblInstruccion, BorderLayout.NORTH);

        panAbajo.add(txtMensage, BorderLayout.CENTER);
        panAbajo.add(butEnviar, BorderLayout.EAST);

        lblAdvertencia = new JLabel("");
        lblAdvertencia.setForeground(Color.RED);
        lblAdvertencia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panAbajo.add(lblAdvertencia, BorderLayout.SOUTH);

        lblNombreUsuario = new JLabel("Jugador: ", SwingConstants.LEFT);
        lblNombreUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNombreUsuario.setForeground(new Color(0, 191, 255)); // Azul eléctrico 

        // Eliminado el fondo con imagen
        JPanel fondoChat = new JPanel(new BorderLayout());
        fondoChat.setBackground(new Color(30, 30, 30)); // Fondo azul claro
        fondoChat.add(panMostrar);

        JScrollPane scrollChat = new JScrollPane(fondoChat);
        scrollChat.getViewport().setBackground(new Color(30, 30, 30));
        scrollChat.setBorder(BorderFactory.createEmptyBorder());

        JPanel panMain = new JPanel(new BorderLayout());
        panMain.add(lblNombreUsuario, BorderLayout.NORTH);
        panMain.add(scrollChat, BorderLayout.CENTER);
        panMain.add(panAbajo, BorderLayout.SOUTH);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuAyuda = new JMenu("Ayuda");
        this.itemAyuda = new JMenuItem("Ayuda");
        itemAyuda.setActionCommand("AYUDA");
        menuAyuda.add(itemAyuda);
        barraMenu.add(menuAyuda);

        setLayout(new BorderLayout());
        add(barraMenu, BorderLayout.NORTH);
        add(panMain, BorderLayout.CENTER);
        setSize(600, 400);
        setMinimumSize(new Dimension(500, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(18, 18, 18)); // Fondo oscuro

    }

    /**
     * Solicita al usuario que ingrese su nombre mediante un cuadro de diálogo.
     *
     * @return Nombre ingresado por el usuario.
     */
    public String nombreJugador() {
        return JOptionPane.showInputDialog("Introducir Nombre :");
    }

    /**
     * Solicita al usuario que ingrese su clave mediante un cuadro de diálogo.
     *
     * @return Clave ingresada por el usuario.
     */
    public String claveJugador() {
        return JOptionPane.showInputDialog("Introducir Clave :");
    }

    /**
     * Solicita al usuario que ingrese la dirección IP del servidor.
     *
     * @return Dirección IP ingresada o "localhost" si no se cambia.
     */
    public String numeroIP() {
        return JOptionPane.showInputDialog("Introducir IP_SERVER :", "localhost");
    }

    /**
     * Muestra un mensaje indicando que el usuario fue desconectado por
     * credenciales incorrectas.
     */
    public void mostrarMensajeDesconectado() {
        JOptionPane.showMessageDialog(null, "El usuario y/o la clave son incorrectas por lo que ha sido desconectado");
    }

    /**
     * Muestra un mensaje indicando que ya se alcanzó el máximo de jugadores.
     */
    public void mostrarMensajeDesconectado2() {
        JOptionPane.showMessageDialog(null, "No puede ingresar, max de jugadores en el juego");
    }

    /**
     * Muestra un mensaje indicando que la partida ya fue iniciada y no se puede
     * ingresar.
     */
    public void mostrarMensajeIngresoProhibido() {
        JOptionPane.showMessageDialog(null, "La partida ya inicio, no puede ingresar");
    }

    /**
     * Retorna el área de texto donde se muestran los mensajes.
     *
     * @return JTextArea utilizada para mostrar mensajes.
     */
    public JTextArea getPanMostrar() {
        return panMostrar;
    }

    /**
     * Establece el área de texto donde se mostrarán los mensajes.
     *
     * @param panMostrar JTextArea a utilizar.
     */
    public void setPanMostrar(JTextArea panMostrar) {
        this.panMostrar = panMostrar;
    }

    /**
     * Retorna el campo de texto donde el jugador escribe su mensaje.
     *
     * @return JTextField utilizado para escribir mensajes.
     */
    public JTextField getTxtMensage() {
        return txtMensage;
    }

    /**
     * Establece el campo de texto donde el jugador escribe su mensaje.
     *
     * @param txtMensage JTextField a utilizar.
     */
    public void setTxtMensage(JTextField txtMensage) {
        this.txtMensage = txtMensage;
    }

    /**
     * Retorna el botón utilizado para enviar mensajes.
     *
     * @return JButton de envío de mensajes.
     */
    public JButton getBtnEnviar() {
        return butEnviar;
    }

    /**
     * Establece el botón utilizado para enviar mensajes.
     *
     * @param butEnviar JButton a utilizar.
     */
    public void setBtnEnviar(JButton butEnviar) {
        this.butEnviar = butEnviar;
    }

    /**
     * Establece el nombre del jugador en la etiqueta correspondiente.
     *
     * @param nombre Nombre del jugador que se mostrará en la interfaz.
     */
    public void setNombreJugador(String nombre) {
        lblNombreUsuario.setText("Jugador: " + nombre);
    }

    /**
     * Muestra un mensaje en el área de texto del chat.
     *
     * @param msg Mensaje que se desea mostrar.
     */
    public void mostrarMsg(String msg) {
        this.panMostrar.append(msg + "\n");
    }

    /**
     * Retorna el mensaje escrito por el usuario en el campo de texto,
     * eliminando espacios al inicio y al final.
     *
     * @return Texto del mensaje ingresado.
     */
    public String getMensaje() {
        return txtMensage.getText().trim();
    }

    /**
     * Retorna el ítem del menú "Ayuda".
     *
     * @return JMenuItem correspondiente a la opción de ayuda.
     */
    public JMenuItem getItemAyuda() {
        return itemAyuda;
    }

    /**
     * Retorna la etiqueta de advertencia utilizada para mostrar mensajes
     * importantes al usuario.
     *
     * @return JLabel de advertencia.
     */
    public JLabel getLblAdvertencia() {
        return lblAdvertencia;
    }

    /**
     * Abre un cuadro de diálogo para que el usuario seleccione un archivo de
     * propiedades que contenga los puertos de conexión del servidor o cliente.
     *
     * @return Objeto File que representa el archivo seleccionado por el
     * usuario.
     */
    public File rutaJfileChooserPorts() {
        File f;
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        fc.showDialog(null, "Seleccionar Archivo Properties de los Ports");

        f = fc.getSelectedFile();
        return f;
    }

    /**
     * Muestra una ventana modal con la información de ayuda para el usuario.
     *
     * <p>
     * La ventana se crea como un {@link JDialog} no redimensionable, centrado
     * respecto a la ventana principal, y contiene una instancia de
     * {@link VistaAyuda}.</p>
     */
    public void mostrarVentanaAyuda() {
        JDialog dialogoAyuda = new JDialog(this, "Ayuda", true);
        dialogoAyuda.setContentPane(new VistaAyuda());
        dialogoAyuda.setSize(520, 500);
        dialogoAyuda.setResizable(false);
        dialogoAyuda.setLocationRelativeTo(this);
        dialogoAyuda.setVisible(true);
    }
}
