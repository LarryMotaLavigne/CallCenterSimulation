package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Ressources.Teleconseiller;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeAppelTelephonique extends Event {
    @Override
    public void run() {
        Entite newAppel = new Entite();
        newAppel.setHeure_arrivee(Statistique.date_simu);
        Bureau.appelTelephonique_enAttente.addLast(newAppel); // Insertion à la fin
        Statistique.appel_arrives++;
        System.out.println(Statistique.appel_arrives);

        // Génération d'une nouvelle arrivée d'appel
        float t_c = Generateur.loi_exponentielle_appelTelephonique(Statistique.date_simu);
        Ordonnanceur.addNewEvenement(Statistique.date_simu + t_c, new ArriveeAppelTelephonique());


        if(Bureau.nAffecteAppel == 0 && Bureau.nConseillerOccupeCourriel == 0){
            newAppel = Bureau.appelTelephonique_enAttente.pollLast();
            newAppel.setHeure_debut_traitement(Statistique.date_simu);

            int idTeleconseiller = Bureau.affecteFreeConseillerCourrielToConseillerAppel();

            float t = Generateur.loi_uniforme(5, 15);
            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique(newAppel, idTeleconseiller));
        }
        else if(Bureau.nConseillerOccupeAppel < Bureau.nAffecteAppel) {
            // Génération d'un nouveau départ d'appel
            newAppel = Bureau.appelTelephonique_enAttente.pollLast();
            newAppel.setHeure_debut_traitement(Statistique.date_simu);

            float t = Generateur.loi_uniforme(5, 15);
            int idTeleconseiller = Bureau.getFreeTeleconseillerAppel();
            Bureau.setOccupeConseillerAppel(idTeleconseiller);
            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique(newAppel, idTeleconseiller));
        }
    }
}
