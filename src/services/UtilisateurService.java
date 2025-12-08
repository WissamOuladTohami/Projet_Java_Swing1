/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import entities.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UtilisateurService {

    public UtilisateurService() {
        
    }

    
    public boolean checkLogin(String login, String password) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean resetPassword(String email) throws Exception {
        Utilisateur u = findByEmail(email);
        if (u == null) {
            return false;
        }

        String tempPassword = generateTempPassword(8);

        String sql = "UPDATE utilisateur SET password = ? WHERE email = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
        ps.setString(1, tempPassword);
        ps.setString(2, email);
        ps.executeUpdate();

        EmailService.sendPasswordResetEmail(email, tempPassword);

        return true;
    }

    
    public boolean register(String login, String email, String password) {
        try {
            String checkSql = "SELECT * FROM utilisateur WHERE login = ? OR email = ?";
            PreparedStatement checkPs = Connexion.getConnection().prepareStatement(checkSql);
            checkPs.setString(1, login);
            checkPs.setString(2, email);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        try {
            String insertSql = "INSERT INTO utilisateur(login, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(insertSql);
            ps.setString(1, login);
            ps.setString(2, email);
            ps.setString(3, password);

            int res = ps.executeUpdate();
            System.out.println("Résultat insertion utilisateur = " + res);
            return res == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Utilisateur findByEmail(String email) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String login = rs.getString("login");
                String pwd   = rs.getString("password");
                return new Utilisateur(login, email, pwd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String generateTempPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(r.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
