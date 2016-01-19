package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;


public class ArriveeAppelTelephonique extends Event {
    @Override
    public void run() {
        /*****************************************************************************/
        /*******************       RECEPTION d'un APPEL       *********************/
        /*****************************************************************************/
        Entite newAppel = new Entite();
        newAppel.setHeure_arrivee(Simulation.date_simu);
        Bureau.appelTelephonique_enAttente.addLast(newAppel); // Insertion à la liste des appels en attente
        Statistique.appel_arrives++;

        /*****************************************************************************/
        /*******************       NOUVEL EVENEMENT ARRIVEE      *********************/
        /*****************************************************************************/
        float t_c = Generateur.loi_exponentielle_appelTelephonique(Simulation.date_simu);
        Ordonnanceur.addNewEvenement(Simulation.date_simu + t_c, new ArriveeAppelTelephonique());


        /*****************************************************************************/
        /*******************              TRAITEMENT             *********************/
        /*****************************************************************************/

        // Si plus personne ne travaille, on affecte une personne pour cet appel
        if (Bureau.nAffecteAppel == 0 && Bureau.nConseillerOccupeCourriel == 0) {
            newAppel = Bureau.appelTelephonique_enAttente.pollLast();
            newAppel.setHeure_debut_traitement(Simulation.date_simu);

            int idTeleconseiller = Bureau.affecteFreeConseillerCourrielToConseillerAppel();

            int idPosteAppel = Bureau.getFreePosteAppel();
            Bureau.setOccupePosteAppel(idPosteAppel);

            float t = Generateur.loi_uniforme(5, 15);
            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartAppelTelephonique(newAppel, idTeleconseiller, idPosteAppel));
        } else if (Bureau.nConseillerOccupeAppel < Bureau.nAffecteAppel) {
            // Génération d'un nouveau départ d'appel
            newAppel = Bureau.appelTelephonique_enAttente.pollLast();
            newAppel.setHeure_debut_traitement(Simulation.date_simu);

            float t = Generateur.loi_uniforme(5, 15);
            int idTeleconseiller = Bureau.getFreeTeleconseillerAppel();

            int idPosteAppel = Bureau.getFreePosteAppel();
            Bureau.setOccupePosteAppel(idPosteAppel);

            Bureau.setOccupeConseillerAppel(idTeleconseiller);
            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartAppelTelephonique(newAppel, idTeleconseiller, idPosteAppel));
        }
    }
}
