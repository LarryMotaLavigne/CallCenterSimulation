package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */
public class DepartAppelTelephonique extends Event {
    private Entite appel = null;
    private int idTeleconseiller = 0;

    public DepartAppelTelephonique(Entite appel, int idTeleconseiller) {
        super();
        this.appel = appel;
        this.idTeleconseiller = idTeleconseiller;
    }

    @Override
    public void run() {
        /*****************************************************************************/
        /*******************          REPONSE A UN APPEL         *********************/
        /*****************************************************************************/
        appel.setHeure_fin_traitement(Statistique.dateFinSimu);
        Bureau.appelTelephonique_traite.add(appel);
        Statistique.appel_traites++;
        System.out.println("=========================== " + Statistique.appel_traites);
        /*****************************************************************************/
        /*******************   FIN DE TACHE DU TELECONSEILLER    *********************/
        /*****************************************************************************/
        Bureau.liste_teleconseiller.get(idTeleconseiller).tempsDeTravail_AppelTelephonique += Statistique.date_simu - appel.getHeure_debut_traitement();
        Bureau.liste_teleconseiller.get(idTeleconseiller).tempsDeTravail += Statistique.date_simu - appel.getHeure_debut_traitement();


        /*****************************************************************************/
        /*******************            TACHE SUIVANTE           *********************/
        /*****************************************************************************/

        // S'il y a un appel, il est prioritaire
        if (!Bureau.appelTelephonique_enAttente.isEmpty()) {
            Entite waitingAppel = Bureau.appelTelephonique_enAttente.pollFirst();
            waitingAppel.setHeure_debut_traitement(Statistique.date_simu);
            float t = Generateur.loi_uniforme(5, 15);

            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique(waitingAppel, this.idTeleconseiller));
        }
        else if (!Bureau.courriel_enAttente.isEmpty()){
            Bureau.liste_teleconseiller.get(idTeleconseiller).isAffecteCourriel = true;
            Bureau.nConseillerOccupeAppel--;
            Bureau.nAffecteAppel--;
            Bureau.nAffecteCourriel++;
            Bureau.nConseillerOccupeCourriel++;

            float t = Generateur.loi_uniforme(3, 7);

            Entite waitingCourriel = Bureau.courriel_enAttente.pollFirst();
            waitingCourriel.setHeure_debut_traitement(Statistique.date_simu);

            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartCourriel(waitingCourriel, this.idTeleconseiller));
        }
        else
        {
            Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = false;
            Bureau.nConseillerOccupeAppel--;
        }

    }
}
