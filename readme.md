# Simulation d'un centre d'appel

L'objectif de cet exercice est de simuler le processus réalisé en centre d'appel afin d'estimer le temps moyen de réponse.

Il convient de préciser que cette simulation prends en compte une unique journée pour le traitement des courriers/appels.
La journée commence à 8h et se termine à 17h.


## Introduction
Imaginez que vous gérer une plateforme de centre d'appels.

Vous devez traiter 2 types de réponses : 
* Les appels téléphoniques
* Les courriels (ou mail)

Pour cela, vous disposez d'un personnel compétent à votre entière disposition. Chaque individu (téléconseiller) peut s'occuper soit des appels téléphoniques, soit des courriels. 
Toutefois, il existe une limite de postes pour les appels téléphoniques car il n'y a pas suffisamment de postes de disponible.

## Les objets
### Appels Téléphoniques
**De 0 à 60 jours**, l'arrivée d'appels téléphoniques suit une loi exponentielle de paramètre **lambda=0.2**.

**De 60 à 180 jours**, l'arrivée d'appels téléphoniques suit une loi exponentielle de paramètre **lambda=1**.

**Après 180 jours**, l'arrivée d'appels téléphoniques suit une loi exponentielle de paramètre **lambda=0.1**.


### Courriels
**De 0 à 60 jours**, l'arrivée des courriels suit une loi exponentielle de paramètre **lambda=2**.

**Après 60 jours**, le paramètre de la loi change et passe à **lambda=0.2**.


### Main
La classe `main` définit un ensemble de paramètres :
* un nombre d'itérations
* un nombre de téléconseillers disponible pour les courriels
* un nombre de téléconseillers disponible pour les appels téléphoniques
* un nombre de postes disponible pour les appes téléphoniques

Des statistiques sont générées à la fin de la simulation et permettent de se rendre compte des divers problèmes rencontrés par les équipes.

# À propos

Cet exercice a été réalisé dans un cadre scolaire lors de la 5e année d'<a href="http://polytech.univ-tours.fr/">Ecole d'Ingénieur de Polytech Tours</a>.
Ce sujet, proposé par <b>Christophe Lenté</b>, a permis d'aborder des notions de statistiques via un travail ludique.