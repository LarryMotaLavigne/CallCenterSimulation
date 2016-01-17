package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Ressources.Teleconseiller;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */
public class DepartCourriel extends Event {
    private Entite courriel = null;
    private int idTeleconseiller = 0;

    public DepartCourriel(Entite courriel, int idTeleconseiller) {
        super();
        this.courriel = courriel;
        this.idTeleconseiller = idTeleconseiller;
    }

    @Override
    public void run() {
        /*****************************************************************************/
        /*******************          ENVOI DU COURRIEL          *********************/
        /*****************************************************************************/
        courriel.setHeure_fin_traitement(Statistique.date_simu);
        Bureau.courriel_traite.add(courriel);
        Statistique.courriel_traites++;

        /*****************************************************************************/
        /*******************   FIN DE TACHE DU TELECONSEILLER    *********************/
        /*****************************************************************************/
        Bureau.liste_teleconseiller.get(idTeleconseiller).tempsDeTravail += Statistique.date_simu - courriel.getHeure_debut_traitement();
        Bureau.liste_teleconseiller.get(idTeleconseiller).tempsDeTravail_Courriel += Statistique.date_simu - courriel.getHeure_debut_traitement();

        /*****************************************************************************/
        /*******************            TACHE SUIVANTE           *********************/
        /*****************************************************************************/

        // S'il y a un appel, il est prioritaire
        if(!Bureau.appelTelephonique_enAttente.isEmpty() && Bureau.appelTelephonique_enAttente.size() >= Bureau.nConseillerOccupeAppel &&
                Bureau.nAffecteAppel < Bureau.nPosteAppel) {

            Bureau.liste_teleconseiller.get(idTeleconseiller).isAffecteCourriel = false;
            Bureau.nConseillerOccupeCourriel--;
            Bureau.nAffecteCourriel--;
            Bureau.nAffecteAppel++;
            Bureau.nConseillerOccupeAppel++;

            float t = Generateur.loi_uniforme(5, 15);

            Entite waitingAppel = Bureau.appelTelephonique_enAttente.pollFirst();
            waitingAppel.setHeure_debut_traitement(Statistique.date_simu);

            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique(waitingAppel, idTeleconseiller));
        }
        // Sinon, on s'occupe des courriels
        else if(!Bureau.courriel_enAttente.isEmpty()) {
            Entite waitingCourriel = Bureau.courriel_enAttente.pollFirst();
            waitingCourriel.setHeure_debut_traitement(Statistique.date_simu);
            float t = Generateur.loi_uniforme(3, 7);

            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartCourriel(waitingCourriel, idTeleconseiller));
        }
        else if (!Bureau.appelTelephonique_enAttente.isEmpty() && Bureau.nAffecteAppel < Bureau.nPosteAppel){
            Bureau.liste_teleconseiller.get(idTeleconseiller).isAffecteCourriel = false;
            Bureau.nConseillerOccupeCourriel--;
            Bureau.nAffecteCourriel--;
            Bureau.nAffecteAppel++;
            Bureau.nConseillerOccupeAppel++;

            float t = Generateur.loi_uniforme(5, 15);

            Entite waitingAppel = Bureau.appelTelephonique_enAttente.pollFirst();
            waitingAppel.setHeure_debut_traitement(Statistique.date_simu);

            Ordonnanceur.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique(waitingAppel, idTeleconseiller));
        }
        else
        {
            Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = false;
            Bureau.nConseillerOccupeCourriel--;
        }
    }
}
