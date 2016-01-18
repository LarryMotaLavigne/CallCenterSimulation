package Statistiques;

import Entites.Entite;
import Ressources.Bureau;
import Ressources.PosteAppel;
import Ressources.PosteCourriel;
import Ressources.Teleconseiller;
import Simulation.Simulation;

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
        System.out.println("Temps d'occupation moyen des postes : " + utilisationPosteMoyen());
        System.out.println("Temps d'occupation moyen des postes d'APPELS : " + utilisationPosteAppelMoyen() + " (" + tauxUtilisationPosteAppelMoyen() + "%)");
        System.out.println("Temps d'occupation moyen des postes de COURRIELS : " + utilisationPosteCourrielMoyen() + " (" + tauxUtilisationPosteCourrielMoyen() + "%)");
    }

    private static float courrielAttenteAvantTraitement() {
        int count = 0;
        for (Entite courriel : Bureau.courriel_traite) {
            count += (courriel.getHeure_debut_traitement() - courriel.getHeure_arrivee());
        }
        return count / Bureau.courriel_traite.size();

    }

    private static float courrielTempsSysteme() {
        int count = 0;
        for (Entite courriel : Bureau.courriel_traite) {
            count += (courriel.getHeure_fin_traitement() - courriel.getHeure_arrivee());
        }
        return count / Bureau.courriel_traite.size();
    }

    private static float utilisationPosteMoyen() {
        float count = 0;
        for(PosteAppel poste : Bureau.liste_poste_appel){
            count += poste.getTempsUtilisation();
        }

        for(PosteCourriel poste : Bureau.liste_poste_courriel){
            count += poste.getTempsUtilisation();
        }

        return count / (Bureau.liste_poste_courriel.size() + Bureau.liste_poste_appel.size());
    }

    private static float utilisationPosteAppelMoyen(){
        float count = 0;
        for(PosteAppel poste : Bureau.liste_poste_appel){
            count += poste.getTempsUtilisation();
        }

        return count / Bureau.liste_poste_appel.size();
    }

    private static float tauxUtilisationPosteAppelMoyen(){
        float utilisationMoyen = utilisationPosteAppelMoyen();
        return (utilisationMoyen / Simulation.dateFinSimu) * 100;
    }

    private static float utilisationPosteCourrielMoyen(){
        float count = 0;
        for(PosteCourriel poste : Bureau.liste_poste_courriel){
            count += poste.getTempsUtilisation();
        }

        return count / Bureau.liste_poste_courriel.size();
    }

    private static float tauxUtilisationPosteCourrielMoyen(){
        float utilisationMoyen = utilisationPosteCourrielMoyen();
        return (utilisationMoyen / Simulation.dateFinSimu) * 100;
    }

    private static float travailMoyenAppel() {
        float count = 0;
        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            count += teleconseiller.tempsDeTravail_AppelTelephonique;
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
