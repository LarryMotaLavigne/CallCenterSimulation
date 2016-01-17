package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourriel extends Event {
    @Override
    public void run() {
        /*****************************************************************************/
        /*******************       RECEPTION d'un COURRIEL       *********************/
        /*****************************************************************************/
        Entite newCourriel = new Entite();
        newCourriel.setHeure_arrivee(Statistique.date_simu);
        Bureau.courriel_enAttente.addLast(newCourriel); // Insertion à la fin
        Statistique.courriel_arrives++;


        /*****************************************************************************/
        /*******************       NOUVEL EVENEMENT ARRIVEE      *********************/
        /*****************************************************************************/
        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Ordonnanceur.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());


        /*****************************************************************************/
        /*******************            DEPART COURRIEL          *********************/
        /*****************************************************************************/

        // Si plus personne ne travaille, on affecte une personne pour ce courrier
        if(Bureau.nAffecteCourriel == 0 && Bureau.nConseillerOccupeAppel == 0){

            newCourriel = Bureau.courriel_enAttente.pollLast();
            newCourriel.setHeure_debut_traitement(Statistique.date_simu);


            int idTeleconseiller = Bureau.affecteFreeConseillerAppelToConseillerCourriel();
            float t = Generateur.loi_uniforme(3, 7);
            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartCourriel(newCourriel, idTeleconseiller));
        }
        else if(Bureau.nConseillerOccupeCourriel < Bureau.nAffecteCourriel && !Bureau.courriel_enAttente.isEmpty())
        {
            newCourriel = Bureau.courriel_enAttente.pollLast();
            newCourriel.setHeure_debut_traitement(Statistique.date_simu);

            int idTeleconseiller = Bureau.getFreeTeleconseillerCourriel();
            Bureau.setOccupeConseillerCourriel(idTeleconseiller);

            float t = Generateur.loi_uniforme(3, 7);

            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartCourriel(newCourriel, idTeleconseiller));
        }
    }
}
