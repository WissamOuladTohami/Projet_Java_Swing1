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
public class Agriculteur {

    private int id;
    private String nom;
    private String commune;
    private String contact;

    public Agriculteur() {
    }

    public Agriculteur(int id, String nom, String commune, String contact) {
        this.id = id;
        this.nom = nom;
        this.commune = commune;
        this.contact = contact;
    }

    public Agriculteur(String nom, String commune, String contact) {
        this(0, nom, commune, contact);
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

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Agriculteur{" + "id=" + id + ", nom='" + nom + '\'' + ", commune='" + commune + '\'' + ", contact='" + contact + '\'' + '}';
    }

}
