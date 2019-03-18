# Demo JHipster

## Step2

### Ajout d'une nouvelle colonne

Dans le fichier entities.jdl, relation Order :
Ajouter le status :

```
entity Order {
 date LocalDate,
 billNumber String,
 amount Double,
 status String
}
```

Puis : `jhipster import-jdl entities.jdl`

Relancer l’application et regarder les logs du démarrage du back

Faire un revert des changements fait sur le fichier :
20190319******_added_entity_Order.xml

Créer le fichier : 20190703000000_add_status.xml
a l'emplacement : demo-jhipster/src/main/resources/config/liquibase/changelog

(fichier complet présent dans le dossier hand's on/src/)

Contenu :

```java
<?xml version="1.0" encoding="utf-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.5.xsd
                        http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">



    <changeSet author="rboisb50" id="add_status">
        <addColumn tableName="jhi_order">
            <column name="status" type="varchar(255)" />
        </addColumn>
    </changeSet>

</databaseChangeLog>
```

Dans le fichier :

demo-jhipster/src/main/resources/config/liquibase/master.xml

Ajouter la ligne :

`<include file="config/liquibase/changelog/20190703000000_add_status.xml" relativeToChangelogFile="false"/>`

Relancer l'application

Aller sur la page des orders

Créer une commande sans date
