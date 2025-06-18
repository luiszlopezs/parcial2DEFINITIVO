/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.progavud.parcial2pa.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Clase encargada de manejar el acceso a la base de datos para operaciones relacionadas con los jugadores.
 * Proporciona métodos para conectarse, consultar y manipular los datos de jugadores.
 * 
 * Atributos:
 * - con: Objeto para establecer y manejar la conexión con la base de datos.
 * - st: Objeto para ejecutar sentencias SQL.
 * - rs: Objeto para almacenar los resultados de las consultas SQL.
 * 
 * @author sangr
 */
public class JugadorDAO {

    private Connection con; // Conexión a la base de datos
    private Statement st;   // Objeto para ejecutar sentencias SQL
    private ResultSet rs;   // Resultado de las consultas SQL


    /**
     * Constructor de la clase JugadorDAO.
     *
     * Inicializa los atributos {@code con}, {@code st} y {@code rs} en
     * {@code null}. Estos se usarán para la conexión a la base de datos,
     * ejecución de sentencias y manejo de resultados respectivamente.
     */
    public JugadorDAO() {
        con = null;
        st = null;
        rs = null;
    }

    /**
     * Inicializa los parámetros de conexión a la base de datos utilizando las
     * propiedades recibidas.
     *
     * @param props Propiedades con la URL, usuario y contraseña de la BD.
     */
    public void inicializarBD(Properties props) {
        ConexionBD.inicializarBD(props);
    }

    /**
 * Inserta un nuevo jugador en la base de datos con el nombre de usuario y la clave proporcionados.
 * 
 * @param usuario Nombre de usuario del jugador.
 * @param clave Clave asociada al jugador.
 * @throws SQLException Si ocurre un error al ejecutar la inserción en la base de datos.
 */
    public void insertarJugador(String usuario, String clave) throws SQLException {

        con = ConexionBD.getConexion();
        st = con.createStatement();
        String insercion = "INSERT INTO JugadoresTabla (Usuario,Clave) VALUES('" + usuario + "','" + clave + "')" ;
        st.executeUpdate(insercion);
        st.close();
        ConexionBD.desconectar();

    }
//    

    /**
 * Consulta en la base de datos si existe un jugador con el nombre de usuario y clave especificados.
 * 
 * @param nombre Nombre de usuario del jugador.
 * @param clave Clave asociada al jugador.
 * @return Un objeto JugadorVO si el jugador existe; de lo contrario, retorna null.
 * @throws SQLException Si ocurre un error al ejecutar la consulta en la base de datos.
 */
    public JugadorVO consultarJugador(String nombre, String clave) throws SQLException {
        JugadorVO JugadorVO = null;
        String consulta = "SELECT * FROM JugadoresTabla WHERE Usuario= '" + nombre + "' AND Clave= '" + clave + "'";

        con = (Connection) ConexionBD.getConexion();
        st = con.createStatement();
        rs = st.executeQuery(consulta);
        if (rs.next()) {
            JugadorVO = new JugadorVO(nombre, clave);
        }
        st.close();
        ConexionBD.desconectar();

        return JugadorVO;
    }
}
