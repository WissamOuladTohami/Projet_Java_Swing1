/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Agriculteur;
import java.util.List;
import services.AgriculteurService;

/**
 *
 * @author cli
 */
public class TestAgriculteur {

    public static void main(String[] args) {
        AgriculteurService as = new AgriculteurService();

        Agriculteur a1 = new Agriculteur("Abelhadi ElMezouari", "Commune A", "0611111111");
        Agriculteur a2 = new Agriculteur("Hatim Ben Ali", "Commune B", "0622222222");
        Agriculteur a3 = new Agriculteur("Amina Chair", "Commune A", "0633333333");

        as.create(a1);
        as.create(a2);
        as.create(a3);

        System.out.println("Agriculteurs ajoutés.");

        System.out.println("\nListe des agriculteurs :");
        List<Agriculteur> agriculteurs = as.findAll();
        for (Agriculteur a : agriculteurs) {
            System.out.println(a);
        }

        System.out.println("\nAgriculteurs de la 'Commune A' :");
        List<Agriculteur> communeA = as.findByCommune("Commune A");
        for (Agriculteur a : communeA) {
            System.out.println(a);
        }

        if (!agriculteurs.isEmpty()) {
            Agriculteur a = agriculteurs.get(0);
            System.out.println("\nAvant modification : " + a);
            a.setContact("0700000000");
            as.update(a);
            System.out.println("Après modification : " + as.findById(a.getId()));
        }

        if (!agriculteurs.isEmpty()) {
            Agriculteur a = agriculteurs.get(agriculteurs.size() - 1);
            System.out.println("\nSuppression de : " + a);
            as.delete(a);
        }

        System.out.println("\nListe finale des agriculteurs :");
        agriculteurs = as.findAll();
        for (Agriculteur a : agriculteurs) {
            System.out.println(a);
        }
    }
}
