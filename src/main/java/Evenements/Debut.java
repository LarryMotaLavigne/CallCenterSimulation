package Evenements;

import Generateur_Aleatoire.Generateur;
import Simulation.Simulation;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */

public class Debut extends Event{

    @Override
    public void run() {
        System.out.println("-> " + this.getClass().getName());


        // Génération de l'événement de FIN
        Ordonnanceur.addNewEvenement(Simulation.dateFinSimu, new Fin());


        // Génération des événements pour le courriel de nuit
        int nCourrierNuitToGenerate = Generateur.loi_uniforme(20, 80);
        for (int i = 0; i < nCourrierNuitToGenerate; i++) {
            Ordonnanceur.addNewEvenement(Simulation.date_simu, new ArriveeCourrielNuit());
        }

        // G�n�ration de l'�v�nement al�atoire d'arriv�e d'un appel t�l�phonique
        float t_at = Generateur.loi_exponentielle_appelTelephonique(Simulation.date_simu);
        Ordonnanceur.addNewEvenement(Simulation.date_simu + t_at, new ArriveeAppelTelephonique());

        // G�n�ration de l'�v�nement al�atoire de d'arriv�e de courriel
        float t_c = Generateur.loi_exponentielle_courriel(Simulation.date_simu);
        Ordonnanceur.addNewEvenement(Simulation.date_simu + t_c, new ArriveeCourriel());

    }
}
