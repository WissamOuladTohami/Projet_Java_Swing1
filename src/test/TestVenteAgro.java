/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Agriculteur;
import entities.ProduitAgro;
import entities.VenteAgro;
import java.time.LocalDate;
import java.util.List;
import services.AgriculteurService;
import services.ProduitAgroService;
import services.VenteAgroService;

/**
 *
 * @author cli
 */
public class TestVenteAgro {

    public static void main(String[] args) {
        ProduitAgroService ps = new ProduitAgroService();
        AgriculteurService as = new AgriculteurService();
        VenteAgroService vs = new VenteAgroService();

        List<ProduitAgro> produits = ps.findAll();
        List<Agriculteur> agriculteurs = as.findAll();

        if (produits.isEmpty() || agriculteurs.isEmpty()) {
            System.out.println("Ajoute d'abord des produits et agriculteurs.");
            return;
        }

        ProduitAgro p1 = produits.get(0);
        Agriculteur a1 = agriculteurs.get(0);

        VenteAgro v1 = new VenteAgro(LocalDate.now(), 150.0, p1, a1);

        vs.create(v1);
        System.out.println("Vente créée.");

        System.out.println("\nListe des ventes :");
        List<VenteAgro> ventes = vs.findAll();
        for (VenteAgro v : ventes) {
            System.out.println(v);
        }

        System.out.println("\nVentes pour le produit : " + p1.getNom());
        List<VenteAgro> ventesProduit = vs.findByProduit(p1.getId());
        for (VenteAgro v : ventesProduit) {
            System.out.println(v);
        }

        System.out.println("\nVentes pour l'agriculteur : " + a1.getNom());
        List<VenteAgro> ventesAgriculteur = vs.findByAgriculteur(a1.getId());
        for (VenteAgro v : ventesAgriculteur) {
            System.out.println(v);
        }
    }
}
