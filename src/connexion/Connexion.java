/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cli
 */
public class Connexion {

    private static Connection connection;

    static {
        try {
            String url = "jdbc:mysql://localhost:3306/cooperative_agricole";
            String user = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);

            JOptionPane.showMessageDialog(
                    null,
                    "Connexion MySQL OK sur la base cooperative_agricole",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur de connexion MySQL : " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println(ex.getMessage());
            System.out.println("Impossible d'établir la connexion");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
