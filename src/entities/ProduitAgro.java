/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author cli
 */
public class ProduitAgro {

    private int id;
    private String nom;
    private String type;
    private double prixKg;

    public ProduitAgro() {
    }

    public ProduitAgro(int id, String nom, String type, double prixKg) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prixKg = prixKg;
    }

    public ProduitAgro(String nom, String type, double prixKg) {
        this(0, nom, type, prixKg);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrixKg() {
        return prixKg;
    }

    public void setPrixKg(double prixKg) {
        this.prixKg = prixKg;
    }

    @Override
    public String toString() {
        return "ProduitAgro{" + "id=" + id + ", nom='" + nom + '\'' + ", type='" + type + '\'' + ", prixKg=" + prixKg + '}';
    }

}
