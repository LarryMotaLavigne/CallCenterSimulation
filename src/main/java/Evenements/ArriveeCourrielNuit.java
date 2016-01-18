package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourrielNuit extends Event {
    @Override
    public void run() {
        Entite newCourriel = new Entite();
        newCourriel.setHeure_arrivee(Simulation.date_simu);
        Bureau.courriel_enAttente.addLast(newCourriel); // Insertion � la fin
        Statistique.n_courrielNuit++;
        Statistique.courriel_arrives++;

        /*****************************************************************************/
        /*******************            DEPART COURRIEL          *********************/
        /*****************************************************************************/
        // Génération d'un nouveau départ de Courriel

        if(Bureau.nConseillerOccupeCourriel < Bureau.nAffecteCourriel) {
            newCourriel = Bureau.courriel_enAttente.pollLast();
            newCourriel.setHeure_debut_traitement(Simulation.date_simu);

            int idTeleconseiller = Bureau.getFreeTeleconseillerCourriel();
            Bureau.setOccupeConseillerCourriel(idTeleconseiller);

            int idPosteCourriel = Bureau.getFreePosteCourriel();
            Bureau.setOccupePosteCourriel(idPosteCourriel);

            float t = Generateur.loi_uniforme(3, 7);

            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartCourriel(newCourriel, idTeleconseiller, idPosteCourriel));
        }
    }
}
