/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import dao.IDao;
import entities.ProduitAgro;
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
public class ProduitAgroService implements IDao< ProduitAgro> {

    @Override
    public boolean create(ProduitAgro o) {
        try {
            String req = "INSERT INTO produit_agro (nom, type, prixKg) VALUES (?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getType());
            ps.setDouble(3, o.getPrixKg());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitAgroService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(ProduitAgro o) {
        try {
            String req = "UPDATE produit_agro SET nom = ?, type = ?, prixKg = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getType());
            ps.setDouble(3, o.getPrixKg());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitAgroService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(ProduitAgro o) {
        try {
            String req = "DELETE FROM produit_agro WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitAgroService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ProduitAgro findById(int id) {
        try {
            String req = "SELECT * FROM produit_agro WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProduitAgro(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getDouble("prixKg")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ProduitAgro> findAll() {
        List<ProduitAgro> produits = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit_agro";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                produits.add(new ProduitAgro(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getDouble("prixKg")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

    public List<ProduitAgro> findByType(String type) {
        List<ProduitAgro> produits = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit_agro WHERE type = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                produits.add(new ProduitAgro(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        rs.getDouble("prixKg")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }
    
   public List<String> findAllTypes() {
    List<String> types = new ArrayList<>();
    String sql = "SELECT DISTINCT type FROM produit_agro";

    try {
        PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String type = rs.getString("type");
            if (type != null && !type.trim().isEmpty()) {
                types.add(type);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return types;

    }
}