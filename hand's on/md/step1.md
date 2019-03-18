# Demo JHipster

## Step1

### Creation des tables

A la racine du projet, créer un fichier entities.jdl

```java
entity Customer {
  firstName String,
  lastName String
}

entity Order {
  date LocalDate,
  billNumber String,
  amount Double
}

relationship OneToMany {
Customer to Order{customer(firstName)}
}

filter Customer, Order

paginate Customer, Order with infinite-scroll

```

Puis lancer la commande :
`jhipster import-jdl entities.jdl`

###Test du front
Redemarrer l'application.
Aller dans le menu entité -> Customer : ajouter des clients
Aller dans le menu entité -> Order : ajouter des commandes

### Test des API

Ouvrir postman et importer le fichier : jhipster.postman_collection.json qui se trouve dans le dossier "hand's on"
Lancer le POST "Get Token local"
Copier la value du token

Lancer les GET "Customers" "Customers Paginated" et "Customer Filtered"
avec dans l'onglet Authorization - type : Bearer Token ; token : valeur du token copiée

