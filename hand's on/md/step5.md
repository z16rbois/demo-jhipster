# Demo JHipster

## Step5

### Customisation avec héritage

Modifier le fichier : 
src/main/resources/config/application-prod.yml


```java
datasource:
...
   url: jdbc:mysql://35.188.27.117:3306/dockerdb?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false
   username: rboisb50
   password: 321321

```

Lancer les commandes : 
```./mvnw package -Pprod -DskipTests verify jib:dockerBuild```


```docker run -p 8888:8080 demojhipster```

Aller sur http://localhsot:8888


