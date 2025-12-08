/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connexion.Connexion;
import dao.IDao;
import entities.Agriculteur;
import entities.ProduitAgro;
import entities.VenteAgro;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cli
 */
public class VenteAgroService implements IDao < VenteAgro > {

    private ProduitAgroService ps;
    private AgriculteurService as;

    public VenteAgroService() {
        ps = new ProduitAgroService();
        as = new AgriculteurService();
    }

    @Override
    public boolean create(VenteAgro o) {
        try {
            String req = "INSERT INTO vente_agro (date_vente, quantite, produit_id, agriculteur_id) VALUES (?, ?, ?, ?)";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            psmt.setDate(1, Date.valueOf(o.getDateVente()));
            psmt.setDouble(2, o.getQuantite());
            psmt.setInt(3, o.getProduit().getId());
            psmt.setInt(4, o.getAgriculteur().getId());
            psmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
        if (ex.getMessage().contains("unique_agri_produit")) {
            System.out.println("Doublon (agriculteur, produit) détecté.");
        }
        ex.printStackTrace();
        return false;
    }
    }

    @Override
    public boolean update(VenteAgro o) {
        try {
            String req = "UPDATE vente_agro SET date_vente = ?, quantite = ?, produit_id = ?, agriculteur_id = ? WHERE id = ?";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            psmt.setDate(1, Date.valueOf(o.getDateVente()));
            psmt.setDouble(2, o.getQuantite());
            psmt.setInt(3, o.getProduit().getId());
            psmt.setInt(4, o.getAgriculteur().getId());
            psmt.setInt(5, o.getId());
            psmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(VenteAgro o) {
        try {
            String req = "DELETE FROM vente_agro WHERE id = ?";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            psmt.setInt(1, o.getId());
            psmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public VenteAgro findById(int id) {
        try {
            String req = "SELECT * FROM vente_agro WHERE id = ?";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                ProduitAgro p = ps.findById(rs.getInt("produit_id"));
                Agriculteur a = as.findById(rs.getInt("agriculteur_id"));
                LocalDate date = rs.getDate("date_vente").toLocalDate();

                return new VenteAgro(
                        rs.getInt("id"),
                        date,
                        rs.getDouble("quantite"),
                        p,
                        a
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<VenteAgro> findAll() {
        List<VenteAgro> ventes = new ArrayList<>();
        try {
            String req = "SELECT * FROM vente_agro";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ProduitAgro p = ps.findById(rs.getInt("produit_id"));
                Agriculteur a = as.findById(rs.getInt("agriculteur_id"));
                LocalDate date = rs.getDate("date_vente").toLocalDate();

                ventes.add(new VenteAgro(
                        rs.getInt("id"),
                        date,
                        rs.getDouble("quantite"),
                        p,
                        a
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventes;
    }

    public List<VenteAgro> findByProduit(int produitId) {
        List<VenteAgro> ventes = new ArrayList<>();
        try {
            String req = "SELECT * FROM vente_agro WHERE produit_id = ?";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            psmt.setInt(1, produitId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ProduitAgro p = ps.findById(rs.getInt("produit_id"));
                Agriculteur a = as.findById(rs.getInt("agriculteur_id"));
                LocalDate date = rs.getDate("date_vente").toLocalDate();

                ventes.add(new VenteAgro(
                        rs.getInt("id"),
                        date,
                        rs.getDouble("quantite"),
                        p,
                        a
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventes;
    }

    public List<VenteAgro> findByAgriculteur(int agriculteurId) {
        List<VenteAgro> ventes = new ArrayList<>();
        try {
            String req = "SELECT * FROM vente_agro WHERE agriculteur_id = ?";
            PreparedStatement psmt = Connexion.getConnection().prepareStatement(req);
            psmt.setInt(1, agriculteurId);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ProduitAgro p = ps.findById(rs.getInt("produit_id"));
                Agriculteur a = as.findById(rs.getInt("agriculteur_id"));
                LocalDate date = rs.getDate("date_vente").toLocalDate();

                ventes.add(new VenteAgro(
                        rs.getInt("id"),
                        date,
                        rs.getDouble("quantite"),
                        p,
                        a
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventes;
    }
    
    public List<VenteAgro> findByCommune(String commune) {
    List<VenteAgro> ventes = new ArrayList<>();
    try {
        String req = "SELECT v.id, v.dateVente, v.quantite, "
                   + "p.id AS pid, p.nom AS pnom, p.type, p.prixKg, "
                   + "a.id AS aid, a.nom AS anom, a.commune, a.contact "
                   + "FROM venteagro v "
                   + "JOIN produitagro p ON v.produit_id = p.id "
                   + "JOIN agriculteur a ON v.agriculteur_id = a.id "
                   + "WHERE a.commune = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, commune);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ProduitAgro p = new ProduitAgro(
                    rs.getInt("pid"),
                    rs.getString("pnom"),
                    rs.getString("type"),
                    rs.getDouble("prixKg")
            );
            Agriculteur a = new Agriculteur(
                    rs.getInt("aid"),
                    rs.getString("anom"),
                    rs.getString("commune"),
                    rs.getString("contact")
            );
            LocalDate date = rs.getDate("dateVente").toLocalDate();
            double qte = rs.getDouble("quantite");

            ventes.add(new VenteAgro(rs.getInt("id"), date, qte, p, a));
        }
    } catch (SQLException ex) {
        Logger.getLogger(VenteAgroService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ventes;
}
    public List<VenteAgro> findAllTypes(String type) {
    List<VenteAgro> ventes = new ArrayList<>();
    String sql = "SELECT * FROM venteagro v "
               + "JOIN produit p ON v.produit_id = p.id "
               + "JOIN agriculteur a ON v.agriculteur_id = a.id "
               + "WHERE p.type = ?";

    try {
        PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            
            VenteAgro v = new VenteAgro();
            v.setId(rs.getInt("v.id"));

            
            ProduitAgro pr = new ProduitAgro();
            pr.setId(rs.getInt("p.id"));
            pr.setNom(rs.getString("p.nom"));
            pr.setType(rs.getString("p.type"));
            v.setProduit(pr);

            
            Agriculteur ag = new Agriculteur();
            ag.setId(rs.getInt("a.id"));
            ag.setNom(rs.getString("a.nom"));
            ag.setCommune(rs.getString("a.commune"));
            v.setAgriculteur(ag);

            
  

            ventes.add(v);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ventes;
    
    
    }
    public boolean existsByAgriculteurAndProduit(int agriculteurId, int produitId) {
    try {
        String sql = "SELECT COUNT(*) FROM vente_agro WHERE agriculteur_id = ? AND produit_id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
        ps.setInt(1, agriculteurId);
        ps.setInt(2, produitId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;

}}
