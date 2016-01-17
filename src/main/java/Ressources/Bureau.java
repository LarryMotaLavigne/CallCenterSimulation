package Ressources;

import Entites.Entite;
import Statistiques.Statistique;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by Florian on 17/01/2016.
 */
public class Bureau {
    // Nb Ressources
    public static int nAffecteCourriel = 5;
    public static int nAffecteAppel = 5;
    public static int nTeleconseiller = nAffecteCourriel + nAffecteAppel;
    public static int nPosteAppel = 5;

    public static int nConseillerOccupeAppel = 0;
    public static int nConseillerOccupeCourriel = 0;

    public static ArrayList<Teleconseiller> liste_teleconseiller = new ArrayList<Teleconseiller>();
    public static ArrayList<Float> list_poste = new ArrayList<>(nTeleconseiller);

    // Listes de tâches terminées
    public static ArrayList<Entite> courriel_traite = new ArrayList<>();
    public static ArrayList<Entite> appelTelephonique_traite = new ArrayList<>();

    // Listes d'attentes
    public static ArrayDeque<Entite> courriel_enAttente = new ArrayDeque<>();
    public static ArrayDeque<Entite> appelTelephonique_enAttente = new ArrayDeque<>();

    public static void init() {
        // Initialisation des téléconseillers
        // Courriel
        for (int i = 0; i < nAffecteAppel; i++) {
            liste_teleconseiller.add(new Teleconseiller(i,true));
        }
        // Appel téléphonique
        for (int i = nAffecteAppel; i < nAffecteAppel + nAffecteCourriel; i++) {
            liste_teleconseiller.add(new Teleconseiller(i,false));
        }
    }

    public static int getFreeTeleconseillerCourriel() {
        Teleconseiller teleconseiller = null;
        int id = -1;

        for (Teleconseiller tmpTeleconseiller : Bureau.liste_teleconseiller) {
            if(!tmpTeleconseiller.isOccupe && tmpTeleconseiller.isAffecteCourriel){
                teleconseiller = tmpTeleconseiller;
                id = teleconseiller.id;
                break;
            }
        }

        return id;
    }

    public static int getFreeTeleconseillerAppel() {
        Teleconseiller teleconseiller = null;
        int id = -1;

        for (Teleconseiller tmpTeleconseiller : Bureau.liste_teleconseiller) {
            if(!tmpTeleconseiller.isOccupe && !tmpTeleconseiller.isAffecteCourriel){
                teleconseiller = tmpTeleconseiller;
                id = teleconseiller.id;
                break;
            }
        }

        return id;
    }

    public static int getTeleconseillerAppel(){
        int id = -1;
        int count=0;
        for (int i = 0; i < Bureau.nTeleconseiller; i++) {
            if(Bureau.liste_teleconseiller.get(i).isOccupe && !Bureau.liste_teleconseiller.get(i).isAffecteCourriel)
                count++;
        }
        if(count < Bureau.nPosteAppel){
            for (int i = 0; i < Bureau.nTeleconseiller; i++) {
                Teleconseiller teleconseiller = Bureau.liste_teleconseiller.get(i);
                if(!teleconseiller.isOccupe && id == -1){
                    teleconseiller.isOccupe = true;
                    teleconseiller.isAffecteCourriel = false;
                    teleconseiller.dateFinTache = Statistique.date_simu;
                    id = i;
                }
            }
        }
        return id;
    }

    public static int getTeleconseillerFindeTache(float date){
        for (int i = 0; i < Bureau.nTeleconseiller; i++) {
            Teleconseiller teleconseiller = Bureau.liste_teleconseiller.get(i);
            if(teleconseiller.dateFinTache == date)
                return i;
        }
        return -1;
    }

    public static int affecteFreeConseillerAppelToConseillerCourriel(){
        Teleconseiller teleconseiller = null;
        int id = -1;

        for (Teleconseiller tmpTeleconseiller : Bureau.liste_teleconseiller) {
            if (!tmpTeleconseiller.isOccupe && !tmpTeleconseiller.isAffecteCourriel) {
                teleconseiller = tmpTeleconseiller;
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
        Teleconseiller teleconseiller = null;
        int id = -1;

        for (Teleconseiller tmpTeleconseiller : Bureau.liste_teleconseiller) {
            if (!tmpTeleconseiller.isOccupe && tmpTeleconseiller.isAffecteCourriel) {
                teleconseiller = tmpTeleconseiller;
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

}
