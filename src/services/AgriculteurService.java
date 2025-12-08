/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import dao.IDao;
import entities.Agriculteur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cli
 */
public class AgriculteurService implements IDao < Agriculteur > {
    
     @Override
    public boolean create(Agriculteur o) {
        try {
            String req = "INSERT INTO agriculteur (nom, commune, contact) VALUES (?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getCommune());
            ps.setString(3, o.getContact());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Agriculteur o) {
        try {
            String req = "UPDATE agriculteur SET nom = ?, commune = ?, contact = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getCommune());
            ps.setString(3, o.getContact());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Agriculteur o) {
        try {
            String req = "DELETE FROM agriculteur WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Agriculteur findById(int id) {
        try {
            String req = "SELECT * FROM agriculteur WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Agriculteur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("commune"),
                        rs.getString("contact")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Agriculteur> findAll() {
        List<Agriculteur> agriculteurs = new ArrayList<>();
        try {
            String req = "SELECT * FROM agriculteur";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                agriculteurs.add(new Agriculteur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("commune"),
                        rs.getString("contact")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agriculteurs;
    }
    
    public List<Agriculteur> findByCommune(String commune) {
        List<Agriculteur> agriculteurs = new ArrayList<>();
        try {
            String req = "SELECT * FROM agriculteur WHERE commune = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, commune);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                agriculteurs.add(new Agriculteur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("commune"),
                        rs.getString("contact")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agriculteurs;
    }
    
    public List<String> findAllCommunes() {
    List<String> communes = new ArrayList<>();
    try {
        String req = "SELECT DISTINCT commune FROM agriculteur ORDER BY commune";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            communes.add(rs.getString("commune"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(AgriculteurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return communes;
    
   
}
}
