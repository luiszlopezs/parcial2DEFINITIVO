package edu.progavud.parcial2pa.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VistaAyuda extends JPanel {

    public VistaAyuda() {
        // Colores estilo Cartoon Network oscuro
        Color fondoPrincipal = new Color(20, 20, 20);          // Gris muy oscuro
        Color acentoRosa = new Color(255, 77, 210);            // Rosa vibrante Cartoon
        Color acentoAzul = new Color(0, 191, 255);             // Azul cielo brillante
        Color textoBlanco = new Color(245, 245, 245);          // Blanco suave
        Color bordeContraste = new Color(255, 255, 255);       // Borde blanco

        setLayout(new BorderLayout());
        setBackground(fondoPrincipal);
        setBorder(BorderFactory.createLineBorder(bordeContraste, 3));

        // Header sin icono
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHeader.setBackground(acentoAzul);
        JLabel titulo = new JLabel("CN Chat - AYUDA");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        titulo.setForeground(Color.BLACK);
        panelHeader.add(titulo);

        // Área de texto
        JTextArea textoAyuda = new JTextArea();
        textoAyuda.setText(
                "¡Bienvenido a *CN Chat*, un sistema de mensajería con todo el estilo Cartoon Network!\n\n"
                + "  CHAT GENERAL\n"
                + " - Escribe coordenadas claras para ganaaar.\n"
                + " - ¡Los mensajes aparecen mágicamente al instante!\n\n"
                + "  USUARIOS CONECTADOS\n"
                + " - Te puedes conectar a la partida solo ANTES de que el servidor la inicie\n"
                + "  CONSEJOS CARTOON\n"
                + " - Sé respetuoso como Steven, divertido como Gumball, y directo como Raven.\n"
                + "¿Problemas?\n"
                + " - Escribe al soporte: cartoonhelp@venecolombia.com\n"
                + " - O contacta al maestro supremo del servidor \n\n"
                + "Gracias por usar *CN Chat*! \n\n"
                + "****** CREADORES ***********\n"
                + " Luis Fernando Lopez Pardo\n"
                + "️ Carlos Enrique Sangronis Ricardo\n"
                + " Carlos Julián Rojas"
        );
        textoAyuda.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        textoAyuda.setEditable(false);
        textoAyuda.setLineWrap(true);
        textoAyuda.setWrapStyleWord(true);
        textoAyuda.setBackground(fondoPrincipal);
        textoAyuda.setForeground(textoBlanco);
        textoAyuda.setCaretPosition(0);
        textoAyuda.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JScrollPane scrollPane = new JScrollPane(textoAyuda);
        scrollPane.setBorder(BorderFactory.createLineBorder(acentoRosa, 2));
        scrollPane.getViewport().setBackground(fondoPrincipal); // Fondo scroll oscuro

        // Pie de página
        JLabel piePagina = new JLabel("Versión ¡encuentranosCN! 1.0 - VeneColombia Cartoon © 2025", SwingConstants.CENTER);
        piePagina.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
        piePagina.setForeground(acentoRosa);
        piePagina.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Añadir componentes
        add(panelHeader, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(piePagina, BorderLayout.SOUTH);
    }
}
