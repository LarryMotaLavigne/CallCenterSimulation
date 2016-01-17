package Evenements;

import Generateur_Aleatoire.Generateur;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */

public class Depart extends Event{

    @Override
    public void run() {
        System.out.println("-> " + this.getClass().getName());


        // Génération de l'événement de FIN
        Ordonnanceur.addNewEvenement(Statistique.dateFinSimu, new Fin());


        // Génération des événements pour le courriel de nuit
        int nCourrierNuitToGenerate = Generateur.loi_uniforme(20, 80);
        for (int i = 0; i < nCourrierNuitToGenerate; i++) {
            Ordonnanceur.addNewEvenement(Statistique.date_simu, new ArriveeCourrielNuit());
        }

        // G�n�ration de l'�v�nement al�atoire d'arriv�e d'un appel t�l�phonique
        float t_at = Generateur.loi_exponentielle_appelTelephonique(Statistique.date_simu);
        Ordonnanceur.addNewEvenement(Statistique.date_simu + t_at, new ArriveeAppelTelephonique());

        // G�n�ration de l'�v�nement al�atoire de d'arriv�e de courriel
        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Ordonnanceur.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());

    }
}
