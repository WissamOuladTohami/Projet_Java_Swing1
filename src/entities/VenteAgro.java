/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author cli
 */
public class VenteAgro {

    private int id;
    private LocalDate dateVente;
    private double quantite;
    private ProduitAgro produit;
    private Agriculteur agriculteur;

    public VenteAgro() {
    }

    public VenteAgro(int id, LocalDate dateVente, double quantite, ProduitAgro produit, Agriculteur agriculteur) {
        this.id = id;
        this.dateVente = dateVente;
        this.quantite = quantite;
        this.produit = produit;
        this.agriculteur = agriculteur;
    }

    public VenteAgro(LocalDate dateVente, double quantite, ProduitAgro produit, Agriculteur agriculteur) {
        this(0, dateVente, quantite, produit, agriculteur);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateVente() {
        return dateVente;
    }

    public void setDateVente(LocalDate dateVente) {
        this.dateVente = dateVente;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public ProduitAgro getProduit() {
        return produit;
    }

    public void setProduit(ProduitAgro produit) {
        this.produit = produit;
    }

    public Agriculteur getAgriculteur() {
        return agriculteur;
    }

    public void setAgriculteur(Agriculteur agriculteur) {
        this.agriculteur = agriculteur;
    }

    @Override
    public String toString() {
        return "VenteAgro{" + "id=" + id + ", dateVente=" + dateVente + ", quantite=" + quantite + ", produit=" + (produit != null ? produit.getNom() : null) + ", agriculteur=" + (agriculteur != null ? agriculteur.getNom() : null) + '}';
    }

}
