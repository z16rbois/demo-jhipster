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
Customer{order(billNumber)} to Order
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

Ouvrir postman et importer le fichier : jhipster.postman_collection.json qui se trouve a la racine du projet.
Lancer le POST "Get Token local"
Copier la valuer du token
Lancer les GET "Customers" "Customers Paginated" et "Customer Filtered"

### Customisation du back

Mettre une date de commande par defaut a la date du jour si la date est a null.

Dans la classe OrderService

Methode : public Order save(Order order)

Ajouter :

```java
if (order.getDate() == null){
   order.setDate(LocalDate.now());
}

```

Ainsi que l'import :

```java
import java.time.LocalDate;
```

### Customisation du front

Dans le fichier order.component.html

Ligne 3, changer en
`<span>Sport Orders</span>`

Retourner sur la page des orders puis créé une Commande sans date.
