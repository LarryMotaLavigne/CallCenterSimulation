package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;


public class ArriveeCourriel extends Event {
    @Override
    public void run() {
        /*****************************************************************************/
        /*******************       RECEPTION d'un COURRIEL       *********************/
        /*****************************************************************************/
        Entite newCourriel = new Entite();
        newCourriel.setHeure_arrivee(Simulation.date_simu);
        Bureau.courriel_enAttente.addLast(newCourriel); // Insertion à la liste des courriels en attente
        Statistique.courriel_arrives++;


        /*****************************************************************************/
        /*******************       NOUVEL EVENEMENT ARRIVEE      *********************/
        /*****************************************************************************/
        float t_c = Generateur.loi_exponentielle_courriel(Simulation.date_simu);
        Ordonnanceur.addNewEvenement(Simulation.date_simu + t_c, new ArriveeCourriel());


        /*****************************************************************************/
        /*******************              TRAITEMENT             *********************/
        /*****************************************************************************/

        // Si plus personne ne travaille, on affecte une personne pour ce courrier
        if(Bureau.nAffecteCourriel == 0 && Bureau.nConseillerOccupeAppel == 0){

            // Récupération du courrier depuis la queue
            newCourriel = Bureau.courriel_enAttente.pollLast();
            newCourriel.setHeure_debut_traitement(Simulation.date_simu);


            int idTeleconseiller = Bureau.affecteFreeConseillerAppelToConseillerCourriel();
            int idPoste = Bureau.getFreePosteCourriel();
            Bureau.setOccupePosteCourriel(idPoste);

            float t = Generateur.loi_uniforme(3, 7);
            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartCourriel(newCourriel, idTeleconseiller, idPoste));
        }
        else if(Bureau.nConseillerOccupeCourriel < Bureau.nAffecteCourriel && !Bureau.courriel_enAttente.isEmpty())
        {
            newCourriel = Bureau.courriel_enAttente.pollLast();
            newCourriel.setHeure_debut_traitement(Simulation.date_simu);

            int idTeleconseiller = Bureau.getFreeTeleconseillerCourriel();
            Bureau.setOccupeConseillerCourriel(idTeleconseiller);
            int idPoste = Bureau.getFreePosteCourriel();
            Bureau.setOccupePosteCourriel(idPoste);

            float t = Generateur.loi_uniforme(3, 7);

            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartCourriel(newCourriel, idTeleconseiller, idPoste));
        }
    }
}
