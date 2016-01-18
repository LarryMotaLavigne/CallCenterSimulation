package Ressources;

import Entites.Entite;
import Statistiques.Statistique;
import javafx.geometry.Pos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by Florian on 17/01/2016.
 */
public class Bureau {
    // Nb Ressources
    public static int nAffecteCourriel = 2;
    public static int nAffecteAppel = 5;
    public static int nTeleconseiller = nAffecteCourriel + nAffecteAppel;
    public static int nPosteAppel = 5;

    public static int nConseillerOccupeAppel = 0;
    public static int nConseillerOccupeCourriel = 0;

    public static ArrayList<Teleconseiller> liste_teleconseiller = new ArrayList<Teleconseiller>();
    public static ArrayList<Float> list_poste = new ArrayList<>(nTeleconseiller);

    public static ArrayList<PosteAppel> liste_poste_appel = new ArrayList<>();
    public static ArrayList<PosteCourriel> liste_poste_courriel = new ArrayList<>();


    // Listes de tâches terminées
    public static ArrayList<Entite> courriel_traite = new ArrayList<>();
    public static ArrayList<Entite> appelTelephonique_traite = new ArrayList<>();

    // Listes d'attentes
    public static ArrayDeque<Entite> courriel_enAttente = new ArrayDeque<>();
    public static ArrayDeque<Entite> appelTelephonique_enAttente = new ArrayDeque<>();

    public static void init() {
        // Initialisation des téléconseillers
        // Courriel
        for (int i = 0; i < nAffecteCourriel; i++) {
            liste_teleconseiller.add(new Teleconseiller(i,true));
        }
        // Appel téléphonique
        for (int i = nAffecteCourriel; i < nAffecteAppel + nAffecteCourriel; i++) {
            liste_teleconseiller.add(new Teleconseiller(i,false));
        }

        // Initialisation des postes
        // Courriel
        for (int i = 0; i < nTeleconseiller; i++){
            liste_poste_courriel.add(new PosteCourriel(i));
        }

        // Appel téléphonique
        for (int i = 0; i < nPosteAppel; i++){
            liste_poste_appel.add(new PosteAppel(i));
        }
    }

    public static int getFreeTeleconseillerCourriel() {
        int id = -1;

        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            if(!teleconseiller.isOccupe && teleconseiller.isAffecteCourriel){
                id = teleconseiller.id;
                break;
            }
        }
        return id;
    }

    public static int getFreeTeleconseillerAppel() {
        int id = -1;

        for (Teleconseiller teleconseiller : Bureau.liste_teleconseiller) {
            if(!teleconseiller.isOccupe && !teleconseiller.isAffecteCourriel){
                id = teleconseiller.id;
                break;
            }
        }
        return id;
    }

    public static int affecteFreeConseillerAppelToConseillerCourriel(){
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

    public static int affecteFreeConseillerCourrielToConseillerAppel(){
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

    public static void setOccupeConseillerCourriel(int idTeleconseiller){
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = true;
        Bureau.nConseillerOccupeCourriel++;
    }

    public static void setOccupeConseillerAppel(int idTeleconseiller){
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = true;
        Bureau.nConseillerOccupeAppel++;
    }

    public static void setFreeConseillerCourriel(int idTeleconseiller){
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = false;
        Bureau.nConseillerOccupeCourriel--;
    }

    public static void setFreeConseillerAppel(int idTeleconseiller){
        Bureau.liste_teleconseiller.get(idTeleconseiller).isOccupe = false;
        Bureau.nConseillerOccupeAppel--;
    }

    public static int getFreePosteCourriel(){
        int idPoste = -1;

        for(PosteCourriel poste : Bureau.liste_poste_courriel){
            if(!poste.isOccupe){
                idPoste = poste.id;
            }
        }
        return idPoste;
    }

    public static int getFreePosteAppel(){
        int idPoste = -1;

        for(PosteAppel poste : Bureau.liste_poste_appel){
            if(!poste.isOccupe){
                idPoste = poste.id;
            }
        }
        return idPoste;
    }

    public static void setOccupePosteCourriel(int idPoste){
        Bureau.liste_poste_courriel.get(idPoste).isOccupe = true;
    }

    public static void setOccupePosteAppel(int idPoste){
        Bureau.liste_poste_appel.get(idPoste).isOccupe = true;
    }

    public static void setFreePosteCourriel(int idPoste){
        Bureau.liste_poste_courriel.get(idPoste).isOccupe = false;
    }

    public static void setFreePosteAppel(int idPoste){
        Bureau.liste_poste_appel.get(idPoste).isOccupe = false;
    }

}
