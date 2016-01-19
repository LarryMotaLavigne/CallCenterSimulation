package Evenements;

import Entites.Entite;
import Generateur_Aleatoire.Generateur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;

public class DepartAppelTelephonique extends Event {
    private Entite appel = null;
    private int idTeleconseiller = 0;
    private int idPosteAppel = 0;

    public DepartAppelTelephonique(Entite appel, int idTeleconseiller, int idPosteAppel) {
        super();
        this.appel = appel;
        this.idTeleconseiller = idTeleconseiller;
        this.idPosteAppel = idPosteAppel;
    }

    @Override
    public void run() {
        /*****************************************************************************/
        /*******************          REPONSE A UN APPEL         *********************/
        /*****************************************************************************/
        appel.setHeure_fin_traitement(Simulation.dateFinSimu);
        Bureau.appelTelephonique_traite.add(appel);
        Statistique.appel_traites++;

        /*****************************************************************************/
        /*******************   FIN DE TACHE DU TELECONSEILLER    *********************/
        /*****************************************************************************/
        Bureau.liste_teleconseiller.get(idTeleconseiller).tempsDeTravail_AppelTelephonique += Simulation.date_simu - appel.getHeure_debut_traitement();
        Bureau.liste_teleconseiller.get(idTeleconseiller).tempsDeTravail += Simulation.date_simu - appel.getHeure_debut_traitement();
        Bureau.liste_poste_appel.get(idPosteAppel).addTempsUtilisation(Simulation.date_simu - appel.getHeure_debut_traitement());


        /*****************************************************************************/
        /*******************            TACHE SUIVANTE           *********************/
        /*****************************************************************************/
        // S'il y a un appel, il est prioritaire
        if (!Bureau.appelTelephonique_enAttente.isEmpty()) {
            Entite waitingAppel = Bureau.appelTelephonique_enAttente.pollFirst();
            waitingAppel.setHeure_debut_traitement(Simulation.date_simu);
            float t = Generateur.loi_uniforme(5, 15);

            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartAppelTelephonique(waitingAppel, this.idTeleconseiller, this.idPosteAppel));
        }
        else if (!Bureau.courriel_enAttente.isEmpty()){
            Bureau.liste_teleconseiller.get(idTeleconseiller).isAffecteCourriel = true;
            Bureau.nConseillerOccupeAppel--;
            Bureau.nAffecteAppel--;
            Bureau.nAffecteCourriel++;
            Bureau.nConseillerOccupeCourriel++;

            Bureau.setFreePosteAppel(idPosteAppel);
            int idPosteCourriel = Bureau.getFreePosteCourriel();
            Bureau.setOccupePosteCourriel(idPosteCourriel);

            float t = Generateur.loi_uniforme(3, 7);

            Entite waitingCourriel = Bureau.courriel_enAttente.pollFirst();
            waitingCourriel.setHeure_debut_traitement(Simulation.date_simu);

            Ordonnanceur.addNewEvenement(Simulation.date_simu + t, new DepartCourriel(waitingCourriel, this.idTeleconseiller, idPosteCourriel));
        }
        else
        {
            Bureau.setFreeConseillerAppel(this.idTeleconseiller);
            Bureau.setFreePosteAppel(this.idPosteAppel);
        }

    }
}
