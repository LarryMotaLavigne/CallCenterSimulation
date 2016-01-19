package Ressources;

import Entites.Entite;
import Evenements.Ordonnanceur;

import java.util.ArrayDeque;
import java.util.ArrayList;

import Simulation.Simulation;
import Statistiques.Statistique;

public class Bureau {
    public static int nAffecteCourriel; // Nombre de téléconseillers affectés aux courriels
    public static int nAffecteAppel; // Nombre de téléconseillers affectés aux appels
    public static int nTeleconseiller; // Nombre de téléconseillers
    public static int nPosteAppel; // Nombre de postes pour les appels téléphoniques

    public static int nConseillerOccupeAppel; // Nombre de téléconseillers occupés pour les appels
    public static int nConseillerOccupeCourriel; // Nombre de téléconseillers occupés pour les courriels

    public static ArrayList<Teleconseiller> liste_teleconseiller;

    // Listes des postes
    public static ArrayList<PosteAppel> liste_poste_appel;
    public static ArrayList<PosteCourriel> liste_poste_courriel;

    // Listes de tâches terminées
    public static ArrayList<Entite> courriel_traite;
    public static ArrayList<Entite> appelTelephonique_traite;

    // Listes d'attentes
    public static ArrayDeque<Entite> courriel_enAttente;
    public static ArrayDeque<Entite> appelTelephonique_enAttente;

    public static void init(int Nc, int Na, int NaMax) {
        /*****************************************************************************/
        /*******************      VARIABLES DE STATISTIQUES      *********************/
        /*****************************************************************************/
        Statistique.courriel_arrives = 0;
        Statistique.courriel_traites = 0;
        Statistique.appel_arrives = 0;
        Statistique.appel_traites = 0;
        Statistique.n_courrielNuit = 0;

        /*****************************************************************************/
        /*******************      VARIABLES DE SIMULATIONS       *********************/
        /*****************************************************************************/
        Simulation.date_simu = 0; // La date de début de la simulation est de 0
        Simulation.isEnd = false; // La simulation n'est pas finie
        Ordonnanceur.clearAndRestart(); // Remise à zéro de l'ordonnanceur


        nAffecteCourriel = Nc;
        nAffecteAppel = Na;
        nTeleconseiller = nAffecteCourriel + nAffecteAppel;
        nPosteAppel = NaMax;

        nConseillerOccupeAppel = 0;
        nConseillerOccupeCourriel = 0;


        /*****************************************************************************/
        /*******************                 LISTES              *********************/
        /*****************************************************************************/
        liste_teleconseiller = new ArrayList<>();

        liste_poste_appel = new ArrayList<>();
        liste_poste_courriel = new ArrayList<>();

        courriel_traite = new ArrayList<>();
        appelTelephonique_traite = new ArrayList<>();

        courriel_enAttente = new ArrayDeque<>();
        appelTelephonique_enAttente = new ArrayDeque<>();


        // Initialisation des téléconseillers
        // Courriel
        for (int i = 0; i < nAffecteCourriel; i++) {
            liste_teleconseiller.add(new Teleconseiller(i, true));
        }
        // Appel téléphonique
        for (int i = nAffecteCourriel; i < nAffecteAppel + nAffecteCourriel; i++) {
            liste_teleconseiller.add(new Teleconseiller(i, false));
        }

        // Initialisation des postes
        // Courriel
        for (int i = 0; i < nTeleconseiller; i++) {
            liste_poste_courriel.add(new PosteCourriel(i));
        }

        // Appel téléphonique
        for (int i = 0; i < nPosteAppel; i++) {
            liste_poste_appel.add(new PosteAppel(i));
        }
    }

    public static int getFreeTeleconseillerCourriel() {
        int id = -1;

        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            if (!teleconseiller.isOccupe && teleconseiller.isAffecteCourriel) {
                id = teleconseiller.id;
                break;
            }
        }
        return id;
    }

    public static int getFreeTeleconseillerAppel() {
        int id = -1;

        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            if (!teleconseiller.isOccupe && !teleconseiller.isAffecteCourriel) {
                id = teleconseiller.id;
                break;
            }
        }
        return id;
    }

    public static int affecteFreeConseillerAppelToConseillerCourriel() {
        int id = -1;

        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            if (!teleconseiller.isOccupe && !teleconseiller.isAffecteCourriel) {
                teleconseiller.isOccupe = true;
                teleconseiller.isAffecteCourriel = true;
                Bureau.nAffecteCourriel++;
                Bureau.nConseillerOccupeCourriel++;
                Bureau.nAffecteAppel--;

                id = teleconseiller.id;
                break;
            }
        }
        return id;
    }

    public static int affecteFreeConseillerCourrielToConseillerAppel() {
        int id = -1;

        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            if (!teleconseiller.isOccupe && teleconseiller.isAffecteCourriel) {
                teleconseiller.isOccupe = true;
                teleconseiller.isAffecteCourriel = false;
                Bureau.nAffecteCourriel--;
                Bureau.nAffecteAppel++;
                Bureau.nConseillerOccupeAppel++;

                id = teleconseiller.id;
                break;
            }
        }
        return id;
    }

    public static void setOccupeConseillerCourriel(int idTeleconseiller) {
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = true;
        Bureau.nConseillerOccupeCourriel++;
    }

    public static void setOccupeConseillerAppel(int idTeleconseiller) {
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = true;
        Bureau.nConseillerOccupeAppel++;
    }

    public static void setFreeConseillerCourriel(int idTeleconseiller) {
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = false;
        Bureau.nConseillerOccupeCourriel--;
    }

    public static void setFreeConseillerAppel(int idTeleconseiller) {
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = false;
        Bureau.nConseillerOccupeAppel--;
    }

    public static int getFreePosteCourriel() {
        int idPoste = -1;

        for (PosteCourriel poste : Bureau.liste_poste_courriel) {
            if (!poste.isOccupe) {
                idPoste = poste.id;
            }
        }
        return idPoste;
    }

    public static void setFreePosteCourriel(int idPoste) {
        Bureau.liste_poste_courriel.get(idPoste).isOccupe = false;
    }

    public static int getFreePosteAppel() {
        int idPoste = -1;

        for (PosteAppel poste : Bureau.liste_poste_appel) {
            if (!poste.isOccupe) {
                idPoste = poste.id;
            }
        }
        return idPoste;
    }

    public static void setFreePosteAppel(int idPoste) {
        Bureau.liste_poste_appel.get(idPoste).isOccupe = false;
    }

    public static void setOccupePosteCourriel(int idPoste) {
        Bureau.liste_poste_courriel.get(idPoste).isOccupe = true;
    }

    public static void setOccupePosteAppel(int idPoste) {
        Bureau.liste_poste_appel.get(idPoste).isOccupe = true;
    }

}
