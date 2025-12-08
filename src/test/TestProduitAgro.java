/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.ProduitAgro;
import java.util.List;
import services.ProduitAgroService;

/**
 *
 * @author cli
 */
public class TestProduitAgro {

    public static void main(String[] args) {
        ProduitAgroService ps = new ProduitAgroService();

        ProduitAgro p1 = new ProduitAgro("Blé dur", "Céréale", 4.20);
        ProduitAgro p2 = new ProduitAgro("Tomate cerise", "Légume", 3.10);

        ps.create(p1);
        ps.create(p2);

        System.out.println("Produits ajoutés.");

        System.out.println("\nListe des produits :");
        List<ProduitAgro> produits = ps.findAll();
        for (ProduitAgro p : produits) {
            System.out.println(p);
        }

        System.out.println("\nProduits de type 'Céréale' :");
        List<ProduitAgro> cereales = ps.findByType("Céréale");
        for (ProduitAgro p : cereales) {
            System.out.println(p);
        }

        if (!produits.isEmpty()) {
            ProduitAgro p = produits.get(0);
            System.out.println("\nAvant modification : " + p);
            p.setPrixKg(p.getPrixKg() + 1.0);
            ps.update(p);
            System.out.println("Après modification : " + ps.findById(p.getId()));
        }

        if (!produits.isEmpty()) {
            ProduitAgro p = produits.get(produits.size() - 1);
            System.out.println("\nSuppression de : " + p);
            ps.delete(p);
        }

        System.out.println("\nListe finale des produits :");
        produits = ps.findAll();
        for (ProduitAgro p : produits) {
            System.out.println(p);
        }
    }
}
