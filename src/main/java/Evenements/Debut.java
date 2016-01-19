package Evenements;

import Generateur_Aleatoire.Generateur;
import Simulation.Simulation;
import Statistiques.Statistique;

public class Debut extends Event{

    @Override
    public void run() {

        // Génération de l'événement de FIN
        Ordonnanceur.addNewEvenement(Simulation.dateFinSimu, new Fin());


        // Génération des événements pour le courriel de nuit
        int nCourrierNuitToGenerate = Generateur.loi_uniforme(20, 80);
        for (int i = 0; i < nCourrierNuitToGenerate; i++) {
            Ordonnanceur.addNewEvenement(Simulation.date_simu, new ArriveeCourrielNuit());
        }

        // Génération d'un événement appel téléphonique
        float t_at = Generateur.loi_exponentielle_appelTelephonique(Simulation.date_simu);
        Ordonnanceur.addNewEvenement(Simulation.date_simu + t_at, new ArriveeAppelTelephonique());

        // Génération d'un événement courriel
        float t_c = Generateur.loi_exponentielle_courriel(Simulation.date_simu);
        Ordonnanceur.addNewEvenement(Simulation.date_simu + t_c, new ArriveeCourriel());

    }
}
