---

 ## Projet : Application de Gestion Agroalimentaire

---

## 1. Objectif du projet

L’objectif de ce projet est de développer une application complète de gestion agroalimentaire permettant de suivre :

- **Les agriculteurs** :

   -*Les produits agricoles*

   -*Les ventes*

   -*Les communes*

   -*Les types de produits*

   -*Les statistiques sur les ventes*

###  L’application facilite :

- **la traçabilité**

- **la gestion quotidienne**

- **visualisation graphique des données**

## 2. Fonctionnalités du projet

 #### Gestion des utilisateurs

- *Authentification via login / mot de passe*

- *Inscription des nouveaux Utilisateurs*

- *Géneration du nouveau mot de passe pour ceux qui l'ont oublié*

- *Vérification des identifiants en base de données*

 #### Gestion des agriculteurs

- *Ajouter, modifier, supprimer un agriculteur*

- *Liste complète sous forme de tableau*

#### Gestion des produits agricoles

- *Ajouter un produit*

- *Associer un type de produit*

- *Modifier / supprimer*

 - *Liste complète*

#### Gestion des ventes

- *Ajouter une vente*
  
- *Associer un produit + agriculteur*

- *Modification / suppression*

- *Conversion des dates*

- *Gestion des quantités*

#### Gestion des communes & types

- *Liste complète et filtrée*

#### Statistiques

- *Courbes et graphiques via QuantiteGraphe.java*

- *Analyse des quantités vendues*

---

## 3. Architecture du Projet :

src/
 ├── connexion/
 │    └── Connexion.java
 │
 │
 ├── dao/
 │    ├── IDao.java
 │
 │
 ├── entities/
 │    ├── Agriculteur.java
 │    ├── ProduitAgro.java
 │    ├── VenteAgro.java
 │    ├── Utilisateur.java
 │
 │── services/
 │    ├── AgriculteurService.java
 │    ├── ProduitAgroService.java
 │    ├── EmailService.java
 │    ├── UtilisateurService.java
 │    ├── VenteAgroService.java
 │    └── 
 │
 ├── ui/
 │    ├── Main.java
 │    ├── LoginForm.java
 │    ├── AgriculteurForm.java
 │    ├── ProduitForm.java
 │    ├── VenteForm.java
 │    └── 
 │
 ├── test/
 │    ├── TestAgriculteur.java
 │    └── TestProduitAgro.java
 │    └── TestVenteAgro.java


---

## 4. MCD du Projet :

<img width="953" height="638" alt="MCD" src="https://github.com/user-attachments/assets/aa6894dd-33c9-4927-8a6b-12628e41c0b5" />


---

## 5. Video Représentatif :






https://github.com/user-attachments/assets/2aeb8fc4-3b59-4a0a-9149-0b9c9c6a0ba0








-
