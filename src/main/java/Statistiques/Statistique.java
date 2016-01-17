package Statistiques;

import Entites.Entite;
import Ressources.Bureau;
import Ressources.Teleconseiller;

import java.util.ArrayList;

/**
 * Created by Larry on 09/01/2016.
 */
public class Statistique {

    /****************************************************/
    /**             VARIABLES DE STATISTIQUES          **/
    /****************************************************/
    public static int courriel_arrives = 0; // Nombre de courriels arriv�s
    public static int courriel_traites = 0; // Nombre de courriels trait�s

    public static int appel_arrives = 0; // Nombre d'appels arriv�s
    public static int appel_traites = 0; // Nombre d'appels trait�s

    public static int n_courrielNuit = 0; // Nombre de courriels re�u dans la nuit


    /****************************************************/
    /**                     VARIABLES                  **/
    /****************************************************/
    public static boolean isEnd = false;
    public static float dateFinSimu = 240;

    public static float date_simu = 0;
    public static float date_derniereSimu = 0;


    public static void run() {
        System.out.println("====================================");
        System.out.println("=            STATISTIQUES          =");
        System.out.println("====================================");
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("COURRIELS");
        System.out.println("Nombre de courriels recus dans la nuit : " + n_courrielNuit);
        System.out.println("Nombre de courriels traites : "+ courriel_traites);
        System.out.println("Nombre de courriels arrives : "+ courriel_arrives);
        System.out.println("Nombre de courriels non traites : "+(courriel_arrives-courriel_traites));
        System.out.println("Temps moyen dans le systeme : " + courrielTempsSysteme());
        System.out.println("Temps d'attente avant traitement : " + courrielAttenteAvantTraitement());
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("APPELS TELEPHONIQUES");
        System.out.println("Nombre d'appels traites : "+ appel_traites);
        System.out.println("Nombre d'appels arrives : "+ appel_arrives);
        System.out.println("Nombre d'appels non traites : "+(appel_arrives-appel_traites));
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("TELECONSEILLERS");
        System.out.println("Temps moyen de travail : " + travailMoyen());
        System.out.println("Temps moyen de travail sur les courriels : " + travailMoyenCourriel());
        System.out.println("Temps moyen de travail sur les appels telephoniques : " + travailMoyenAppel());
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("POSTES");
        System.out.println("Temps d'occupation moyen des postes : " + posteMoyen());
    }

    private static float courrielAttenteAvantTraitement() {
        int count = 0;
        for (int i = 0; i < Bureau.courriel_traite.size(); i++) {
            Entite courriel = Bureau.courriel_traite.get(i);
            count += (courriel.getHeure_debut_traitement() - courriel.getHeure_arrivee());
        }
        return count / Bureau.courriel_traite.size();

    }

    private static float courrielTempsSysteme() {
        int count = 0;
        for (int i = 0; i < Bureau.courriel_traite.size(); i++) {
            Entite courriel = Bureau.courriel_traite.get(i);
            count += (courriel.getHeure_fin_traitement() - courriel.getHeure_arrivee());
        }
        return count / Bureau.courriel_traite.size();
    }

    private static float posteMoyen() {
        return 0;
    }

    private static float travailMoyenAppel() {
        float count = 0;
        for (int i = 0; i < Bureau.nTeleconseiller; i++) {
            count += Bureau.liste_teleconseiller.get(i).tempsDeTravail_AppelTelephonique;
        }
        return count / Bureau.nTeleconseiller;
    }

    private static float travailMoyenCourriel() {
        float count = 0;
        for (int i = 0; i < Bureau.nTeleconseiller; i++) {
            count += Bureau.liste_teleconseiller.get(i).tempsDeTravail_Courriel;
        }
        return count / Bureau.nTeleconseiller;
    }

    private static float travailMoyen() {
        float count = 0;
        for (int i = 0; i < Bureau.nTeleconseiller; i++) {
            count += Bureau.liste_teleconseiller.get(i).tempsDeTravail;
        }
        return count / Bureau.nTeleconseiller;
    }
}
