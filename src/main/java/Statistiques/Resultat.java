package Statistiques;

/**
 * Created by Florian on 19/01/2016.
 */
public class Resultat {
    private int courriel_arrives; // Nombre de courriels arrivés

    private int courriel_traites; // Nombre de courriels traités

    private int appel_arrives; // Nombre d'appels arrivés

    private int appel_traites; // Nombre d'appels traités

    private int n_courrielNuit; // Nombre de courriels recu dans la nuit

    // Courriels Globales
    private int courriel_non_traites = 0;
    private float courriel_temps_systeme = 0;
    private float courriel_attente_avant_traitement = 0;

    // Appels Globales
    private int appel_non_traites = 0;
    private float appel_temps_systeme = 0;
    private float appel_attente_avant_traitement = 0;

    // Temps de travail des téléconseillers Globales
    private float travailMoyen = 0;
    private float travailMoyenCourriel = 0;
    private float travailMoyenAppel = 0;

    // Temps d'occupation des postes de travail
    private float utilisationPosteMoyen = 0;
    private float utilisationPosteAppelMoyen = 0;
    private float utilisationPosteCourrielMoyen = 0;
    private float tauxUtilisationPosteAppelMoyen = 0;
    private float tauxUtilisationPosteCourrielMoyen = 0;

    public Resultat(){

    }

    public int getCourriel_arrives() {
        return courriel_arrives;
    }

    public void setCourriel_arrives(int courriel_arrives) {
        this.courriel_arrives = courriel_arrives;
    }

    public int getCourriel_traites() {
        return courriel_traites;
    }

    public void setCourriel_traites(int courriel_traites) {
        this.courriel_traites = courriel_traites;
    }

    public int getAppel_arrives() {
        return appel_arrives;
    }

    public void setAppel_arrives(int appel_arrives) {
        this.appel_arrives = appel_arrives;
    }

    public int getAppel_traites() {
        return appel_traites;
    }

    public void setAppel_traites(int appel_traites) {
        this.appel_traites = appel_traites;
    }

    public int getN_courrielNuit() {
        return n_courrielNuit;
    }

    public void setN_courrielNuit(int n_courrielNuit) {
        this.n_courrielNuit = n_courrielNuit;
    }

    public int getCourriel_non_traites() {
        return courriel_non_traites;
    }

    public void setCourriel_non_traites(int courriel_non_traites) {
        this.courriel_non_traites = courriel_non_traites;
    }

    public float getCourriel_temps_systeme() {
        return courriel_temps_systeme;
    }

    public void setCourriel_temps_systeme(float courriel_temps_systeme) {
        this.courriel_temps_systeme = courriel_temps_systeme;
    }

    public float getCourriel_attente_avant_traitement() {
        return courriel_attente_avant_traitement;
    }

    public void setCourriel_attente_avant_traitement(float courriel_attente_avant_traitement) {
        this.courriel_attente_avant_traitement = courriel_attente_avant_traitement;
    }

    public int getAppel_non_traites() {
        return appel_non_traites;
    }

    public void setAppel_non_traites(int appel_non_traites) {
        this.appel_non_traites = appel_non_traites;
    }

    public float getAppel_temps_systeme() {
        return appel_temps_systeme;
    }

    public void setAppel_temps_systeme(float appel_temps_systeme) {
        this.appel_temps_systeme = appel_temps_systeme;
    }

    public float getAppel_attente_avant_traitement() {
        return appel_attente_avant_traitement;
    }

    public void setAppel_attente_avant_traitement(float appel_attente_avant_traitement) {
        this.appel_attente_avant_traitement = appel_attente_avant_traitement;
    }

    public float getTravailMoyen() {
        return travailMoyen;
    }

    public void setTravailMoyen(float travailMoyen) {
        this.travailMoyen = travailMoyen;
    }

    public float getTravailMoyenCourriel() {
        return travailMoyenCourriel;
    }

    public void setTravailMoyenCourriel(float travailMoyenCourriel) {
        this.travailMoyenCourriel = travailMoyenCourriel;
    }

    public float getTravailMoyenAppel() {
        return travailMoyenAppel;
    }

    public void setTravailMoyenAppel(float travailMoyenAppel) {
        this.travailMoyenAppel = travailMoyenAppel;
    }

    public float getUtilisationPosteMoyen() {
        return utilisationPosteMoyen;
    }

    public void setUtilisationPosteMoyen(float utilisationPosteMoyen) {
        this.utilisationPosteMoyen = utilisationPosteMoyen;
    }

    public float getUtilisationPosteAppelMoyen() {
        return utilisationPosteAppelMoyen;
    }

    public void setUtilisationPosteAppelMoyen(float utilisationPosteAppelMoyen) {
        this.utilisationPosteAppelMoyen = utilisationPosteAppelMoyen;
    }

    public float getUtilisationPosteCourrielMoyen() {
        return utilisationPosteCourrielMoyen;
    }

    public void setUtilisationPosteCourrielMoyen(float utilisationPosteCourrielMoyen) {
        this.utilisationPosteCourrielMoyen = utilisationPosteCourrielMoyen;
    }

    public float getTauxUtilisationPosteAppelMoyen() {
        return tauxUtilisationPosteAppelMoyen;
    }

    public void setTauxUtilisationPosteAppelMoyen(float tauxUtilisationPosteAppelMoyen) {
        this.tauxUtilisationPosteAppelMoyen = tauxUtilisationPosteAppelMoyen;
    }

    public float getTauxUtilisationPosteCourrielMoyen() {
        return tauxUtilisationPosteCourrielMoyen;
    }

    public void setTauxUtilisationPosteCourrielMoyen(float tauxUtilisationPosteCourrielMoyen) {
        this.tauxUtilisationPosteCourrielMoyen = tauxUtilisationPosteCourrielMoyen;
    }
}
