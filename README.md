##  Mettre en place l'application
   
####  1) Créer la base de données :

##### Pour lancer la base de données, deux solutions sont disponible.
##### Une première avec Docker qui permet d'avoir une base de données neuve sans démo.
##### Une autre avec un backup de la base de données contenant une démo.

### Docker :

    1. Ce rendre dans le fichier docker/dev et execturer la commande suivante : 
    docker-compose up

### Backup :

    1. Entrer dans pgAdmin
    2. Faire un clique droit sur "databases"
   
   ![automated like clockwork](doc/image/image_pgAdmin_add.png)
    
    3. Nom de la base de données : alegia
    4. Sélectionner 'postgres' comme owner
   
   ![automated like clockwork](doc/image/create_db_pgAdmin.png)

    5. Faire un clique droit sur la base de données créé et faire un 'backup'
   
   ![automated like clockwork](doc/image/image_pgAdmin_backup.png)
   
    6. Définir l'encoding en 'UTF-8'
    7. Définir le role name avec 'postgres'
    8. Faire un backup de la base de données avec le fichier bdd/backup.
    

#   Mettre en place les microservices :

  #####Ouvrir le dossier Microservice via son IDE
  
  ##  Mettre en place eureka avant les microservices, celui-ci s'implèmente de la même façon que les microservices.
    
   ####Action demandé pour chaque microservice : 
   
   Via Maven sélectionner le pom.xml du microservice via "Add Maven project" 
   
   ![automated like clockwork](doc/image/image4.png)
   
   <br/>
    Dans le Project Structure / Modules
        définir dans src/main le dossier java comme Source
        et le resources comme Resources
 
   ![automated like clockwork](doc/image/image1.png)
   
   
   Via Maven effectuer un download des sources pour vérifier que vous possèdez bien toutes les dépendances.
   
   ![automated like clockwork](doc/image/image2.png)
   
   Via Maven faire un install du microservice
   
   ![automated like clockwork](doc/image/image3.png)
   
   
   Pour faire fonctionner les microservices, vous devez lancer eureka dans un premier temps.
   <br/>
   Une fois les eureka et les microservices lancés vous pouvez lancer Zuul
   
   
   # Mettre en place la partie client "alegia"
   
   
   1. Effectuer les mêmes actions que pour un microservice.
   2. Les microservices doivent être lancés pour consulter le site.
   3. Modifier l'url des interfaces pour les microservices qui fonctionne avec l'ip d'un autre ordinateur,
   les remplacer par localhost.
   4. Modifier la variable filePath dans la classe GestionSiteAction, remplacer par le chemin de votre ordinateur pour aller au projet
   
   
   
   # Informations supplémentaire
   
   Mot de passe du certificat de SSL : Yocorps17  <br/>
   <br/>
   Si vous avez effectué un backup de la base de données <br/>
   Compte administrateur : alegia.shop@gmail.com  <br/>
   Mot de passe : Administrateur1  <br/>
   
  
  # Test d'intégrations
  Pour lancer les tests des microservices sur votre machine suivre ces étapes :
  - Installer Node.js 
  - Installer newman avec la commande suivante : npm install -g newman
  - Se placer dans le fichier principal du projet.
  - Lancer la commande suivante : newman run test/alegia.postman_collection.json
