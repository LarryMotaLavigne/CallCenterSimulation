package Statistiques;

import Entites.Entite;
import Ressources.Bureau;
import Ressources.PosteAppel;
import Ressources.PosteCourriel;
import Ressources.Teleconseiller;
import Simulation.Simulation;

import java.util.ArrayList;

public class Statistique {

    /****************************************************/
    /**             VARIABLES DE STATISTIQUES          **/
    /****************************************************/
    public static int courriel_arrives; // Nombre de courriels arrivés
    public static int courriel_arrives_globales = 0;

    public static int courriel_traites; // Nombre de courriels traités
    public static int courriel_traites_globales = 0;

    public static int appel_arrives; // Nombre d'appels arrivés
    public static int appel_arrives_globales = 0;

    public static int appel_traites; // Nombre d'appels traités
    public static int appel_traites_globales = 0;

    public static int n_courrielNuit; // Nombre de courriels recu dans la nuit
    public static int n_courrielNuit_globales = 0;

    // Courriels Globales
    public static int courriel_non_traites_globales = 0;
    public static float courriel_temps_systeme_globales = 0;
    public static float courriel_attente_avant_traitement_globales = 0;

    // Appels Globales
    public static int appel_non_traites_globales = 0;
    public static float appel_temps_systeme_globales = 0;
    public static float appel_attente_avant_traitement_globales = 0;

    // Temps de travail des téléconseillers Globales
    public static float travailMoyen_globales = 0;
    public static float travailMoyenCourriel_globales = 0;
    public static float travailMoyenAppel_globales = 0;

    // Temps d'occupation des postes de travail
    public static float utilisationPosteMoyen_globales = 0;
    public static float utilisationPosteAppelMoyen_globales = 0;
    public static float utilisationPosteCourrielMoyen_globales = 0;
    public static float tauxUtilisationPosteAppelMoyen_globales = 0;
    public static float tauxUtilisationPosteCourrielMoyen_globales = 0;


    // Liste des résultats de toutes les itérations
    public static ArrayList<Resultat> liste_resultat = new ArrayList<Resultat>();

    // Nombre d'itérations
    public static int nbIterations = 0;

    public static void run(boolean activeDisplay) {

        enregistreResultat();

        if(activeDisplay){
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
            System.out.println("Temps moyen dans le systeme : " + appelTempsSysteme());
            System.out.println("Temps d'attente avant traitement : " + appelAttenteAvantTraitement());
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


        n_courrielNuit_globales += n_courrielNuit;
        courriel_traites_globales += courriel_traites;
        courriel_arrives_globales += courriel_arrives;
        courriel_non_traites_globales += (courriel_arrives-courriel_traites);
        courriel_temps_systeme_globales += courrielTempsSysteme();
        courriel_attente_avant_traitement_globales += courrielAttenteAvantTraitement();
        appel_traites_globales += appel_traites;
        appel_arrives_globales += appel_arrives;
        appel_non_traites_globales += (appel_arrives-appel_traites);
        appel_temps_systeme_globales += appelTempsSysteme();
        appel_attente_avant_traitement_globales += appelAttenteAvantTraitement();
        travailMoyen_globales += travailMoyen();
        travailMoyenCourriel_globales += travailMoyenCourriel();

        travailMoyenAppel_globales += travailMoyenAppel();

        utilisationPosteMoyen_globales += utilisationPosteMoyen();

        utilisationPosteAppelMoyen_globales += utilisationPosteAppelMoyen();
        tauxUtilisationPosteAppelMoyen_globales += tauxUtilisationPosteAppelMoyen();

        utilisationPosteCourrielMoyen_globales += utilisationPosteCourrielMoyen();
        tauxUtilisationPosteCourrielMoyen_globales += tauxUtilisationPosteCourrielMoyen();
    }

    private static void enregistreResultat(){
        Resultat resultat = new Resultat();
        resultat.setN_courrielNuit(n_courrielNuit);
        resultat.setCourriel_traites(courriel_traites);
        resultat.setCourriel_arrives(courriel_arrives);
        resultat.setCourriel_non_traites(courriel_arrives-courriel_traites);
        resultat.setCourriel_temps_systeme(courrielTempsSysteme());
        resultat.setCourriel_temps_systeme(courrielAttenteAvantTraitement());

        resultat.setAppel_traites(appel_traites);
        resultat.setAppel_arrives(appel_arrives);
        resultat.setAppel_non_traites(appel_arrives-appel_traites);
        resultat.setAppel_temps_systeme(appelTempsSysteme());
        resultat.setAppel_temps_systeme(appelAttenteAvantTraitement());

        resultat.setTravailMoyen(travailMoyen());
        resultat.setTravailMoyenCourriel(travailMoyenCourriel());
        resultat.setTravailMoyenAppel(travailMoyenAppel());

        resultat.setUtilisationPosteMoyen(utilisationPosteMoyen());
        resultat.setUtilisationPosteCourrielMoyen(utilisationPosteCourrielMoyen());
        resultat.setUtilisationPosteAppelMoyen(utilisationPosteAppelMoyen());
        resultat.setTauxUtilisationPosteCourrielMoyen(tauxUtilisationPosteCourrielMoyen());
        resultat.setTauxUtilisationPosteAppelMoyen(tauxUtilisationPosteAppelMoyen());

        liste_resultat.add(resultat);
    }

    public static void runGlobale(int nbIter){
        nbIterations = nbIter;

        System.out.println("====================================");
        System.out.println("=       STATISTIQUES GLOBALES      =");
        System.out.println("====================================");

        System.out.println("---------------------------");
        System.out.println("COURRIELS");
        System.out.println("---------------------------");
        System.out.println("Nombre de courriels recus dans la nuit : " + ((float) n_courrielNuit_globales /nbIterations));
        System.out.println("Nombre de courriels traites : "+ ((float)courriel_traites_globales/nbIterations));
        System.out.println("Nombre de courriels arrives : "+ ((float)courriel_arrives_globales/nbIterations));
        System.out.println("Nombre de courriels non traites : "+((float)courriel_non_traites_globales/nbIterations));
        System.out.println("Temps moyen d'un courriel dans le systeme : " + courriel_temps_systeme_globales/nbIterations);
        System.out.println("Temps d'attente avant traitement : " + courriel_attente_avant_traitement_globales/nbIterations);
        System.out.print("Intervalle de confiance 95% courriels non traités : ");
        courrielNonTraiteIntervalleDeConfiance95();
        System.out.print("Intervalle de confiance 95% temps d'attente réponse courriel : ");
        tempsAttenteCourrielIntervalleDeConfiance95();
        System.out.println();


        System.out.println("---------------------------");
        System.out.println("APPELS TELEPHONIQUES");
        System.out.println("---------------------------");
        System.out.println("Nombre d'appels traites : "+ appel_traites_globales/nbIterations);
        System.out.println("Nombre d'appels arrives : "+ appel_arrives_globales/nbIterations);
        System.out.println("Nombre d'appels non traites : "+appel_non_traites_globales/nbIterations);
        System.out.println("Temps moyen dans le systeme : " + appel_temps_systeme_globales/nbIterations);
        System.out.println("Temps d'attente avant traitement : " + appel_attente_avant_traitement_globales/nbIterations);
        System.out.print("Intervalle de confiance 95% appels traités : ");
        appelTraiteIntervalleDeConfiance95();
        System.out.print("Intervalle de confiance 95% temps d'attente appel : ");
        tempsAttenteClientAppelIntervalleDeConfiance95();
        System.out.println();


        System.out.println("---------------------------");
        System.out.println("TELECONSEILLERS");
        System.out.println("---------------------------");
        System.out.println("Temps moyen de travail : " + travailMoyen_globales/nbIterations);
        System.out.println("Temps moyen de travail sur les courriels : " + travailMoyenCourriel_globales/nbIterations);
        System.out.println("Temps moyen de travail sur les appels telephoniques : " + travailMoyenAppel_globales/nbIterations);
        System.out.print("Intervalle de confiance 95% taux occupation : ");
        tauxOccupationConseillersIntervalleDeConfiance95();
        System.out.println();



        System.out.println("---------------------------");
        System.out.println("POSTES");
        System.out.println("---------------------------");
        System.out.println("Temps d'occupation moyen des postes : " + utilisationPosteMoyen_globales/nbIterations);
        System.out.println("Temps d'occupation moyen des postes d'APPELS : " + utilisationPosteAppelMoyen_globales/nbIterations + " (" + tauxUtilisationPosteAppelMoyen_globales/nbIterations + "%)");
        System.out.println("Temps d'occupation moyen des postes de COURRIELS : " + utilisationPosteCourrielMoyen_globales/nbIterations + " (" + tauxUtilisationPosteCourrielMoyen_globales/nbIterations + "%)");
        System.out.print("Intervalle de confiance 95% taux occupation des postes appels : ");
        tauxOccupationPosteAppelIntervalleDeConfiance95();







    }

    private static float courrielAttenteAvantTraitement() {
        int count = 0;
        for (Entite courriel : Bureau.courriel_traite) {
            count += (courriel.getHeure_debut_traitement() - courriel.getHeure_arrivee());
        }
        return count / Bureau.courriel_traite.size();

    }

    private static float appelAttenteAvantTraitement() {
        int count = 0;
        for (Entite appel : Bureau.appelTelephonique_traite) {
            count += (appel.getHeure_debut_traitement() - appel.getHeure_arrivee());
        }
        return count / Bureau.appelTelephonique_traite.size();

    }

    private static float courrielTempsSysteme() {
        int count = 0;
        for (Entite courriel : Bureau.courriel_traite) {
            count += (courriel.getHeure_fin_traitement() - courriel.getHeure_arrivee());
        }
        return count / Bureau.courriel_traite.size();
    }

    private static float appelTempsSysteme() {
        int count = 0;
        for(Entite appel : Bureau.appelTelephonique_traite){
            count += (appel.getHeure_fin_traitement() - appel.getHeure_arrivee());
        }
        return count / Bureau.appelTelephonique_traite.size();
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

    private static void courrielNonTraiteIntervalleDeConfiance95(){
        double esperance = Statistique.courriel_non_traites_globales / Statistique.nbIterations;

        double count  = 0;
        for(Resultat resultat : liste_resultat){
            count +=  (resultat.getCourriel_non_traites() - esperance) * (resultat.getCourriel_non_traites() - esperance);
        }

        double variance = (1./(nbIterations-1)) * count;

        System.out.println("Pr(" + (esperance - (1.96 * Math.sqrt(variance/nbIterations))) +" <= u <= " +
                                                                (esperance + (1.96 * Math.sqrt(variance/nbIterations))) + ") = 95%");
    }

    private static void appelTraiteIntervalleDeConfiance95(){
        double esperance = Statistique.appel_traites_globales / Statistique.nbIterations;

        double count  = 0;
        for(Resultat resultat : liste_resultat){
            count +=  (resultat.getAppel_traites() - esperance) * (resultat.getAppel_traites() - esperance);
        }

        double variance = (1./(nbIterations-1)) * count;

        System.out.println("Pr(" + (esperance - (1.96 * Math.sqrt(variance/nbIterations))) +" <= u <= " +
                (esperance + (1.96 * Math.sqrt(variance/nbIterations))) + ") = 95%");
    }

    private static void tempsAttenteClientAppelIntervalleDeConfiance95(){
        double esperance = Statistique.appel_attente_avant_traitement_globales / Statistique.nbIterations;

        double count  = 0;
        for(Resultat resultat : liste_resultat){
            count +=  (resultat.getAppel_attente_avant_traitement() - esperance) * (resultat.getAppel_attente_avant_traitement() - esperance);
        }

        double variance = (1./(nbIterations-1)) * count;

        System.out.println("Pr(" + (esperance - (1.96 * Math.sqrt(variance/nbIterations))) +" <= u <= " +
                (esperance + (1.96 * Math.sqrt(variance/nbIterations))) + ") = 95%");
    }

    private static void tempsAttenteCourrielIntervalleDeConfiance95(){
        double esperance = Statistique.courriel_attente_avant_traitement_globales / Statistique.nbIterations;

        double count  = 0;
        for(Resultat resultat : liste_resultat){
            count +=  (resultat.getCourriel_attente_avant_traitement() - esperance) * (resultat.getCourriel_attente_avant_traitement() - esperance);
        }

        double variance = (1./(nbIterations-1)) * count;

        System.out.println("Pr(" + (esperance - (1.96 * Math.sqrt(variance/nbIterations))) +" <= u <= " +
                (esperance + (1.96 * Math.sqrt(variance/nbIterations))) + ") = 95%");
    }

    private static void tauxOccupationConseillersIntervalleDeConfiance95(){
        double esperance = (Statistique.travailMoyen_globales / Statistique.nbIterations);

        double count  = 0;
        for(Resultat resultat : liste_resultat){
            count +=  (resultat.getTravailMoyen() - esperance) * (resultat.getTravailMoyen() - esperance);
        }

        double variance = (1./(nbIterations-1)) * count;

        System.out.println("Pr(" + 100*(esperance - (1.96 * Math.sqrt(variance/nbIterations)))/ (Simulation.dateFinSimu - Simulation.dateDebutSimu) +" <= u <= " +
                100*(esperance + (1.96 * Math.sqrt(variance/nbIterations)))/(Simulation.dateFinSimu - Simulation.dateDebutSimu) + ") = 95%");
    }

    private static void tauxOccupationPosteAppelIntervalleDeConfiance95(){
        double esperance = Statistique.tauxUtilisationPosteAppelMoyen_globales / Statistique.nbIterations;

        double count  = 0;
        for(Resultat resultat : liste_resultat){
            count +=  (resultat.getTauxUtilisationPosteAppelMoyen() - esperance) * (resultat.getTauxUtilisationPosteAppelMoyen() - esperance);
        }

        double variance = (1./(nbIterations-1)) * count;

        System.out.println("Pr(" + (esperance - (1.96 * Math.sqrt(variance/nbIterations))) +" <= u <= " +
                (esperance + (1.96 * Math.sqrt(variance/nbIterations))) + ") = 95%");
    }

}
