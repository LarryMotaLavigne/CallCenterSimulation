import com.sun.org.apache.xpath.internal.operations.Neg;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Larry on 09/01/2016.
 */
public class Statistique {

    /****************************************************/
    /**             VARIABLES DE STATISTIQUES          **/
    /****************************************************/
    static int courriel_arrives = 0; // Nombre de courriels arrivés
    static int courriel_traites = 0; // Nombre de courriels traités

    static int appel_arrives = 0; // Nombre d'appels arrivés
    static int appel_traites = 0; // Nombre d'appels traités

    static int n_courrielNuit = 0; // Nombre de courriels reçu dans la nuit


    /****************************************************/
    /**                     VARIABLES                  **/
    /****************************************************/
    // Nb Ressources
    static int Nc = 5;
    static int Nat = 5;
    static int N = Nc + Nat;
    static int NatMax = 5;


    static boolean isEnd = false;
    static float dateFinSimu = 240;

    static int n_aAffecte = 0;
    static int n_cAffecte = 0;

    static int n_aOccupe = 0;
    static int n_cOccupe = 0;


    static float date_simu = 0;
    static float date_derniereSimu = 0;


    // Files d'attente
    static ArrayList<Teleconseiller> bureau = new ArrayList<>();
    static ArrayList<Float> list_poste = new ArrayList<>(NatMax);

    static ArrayList<Entite> courriel_traite = new ArrayList<>();
    static ArrayList<Entite> appelTelephonique_traite = new ArrayList<>();

    static ArrayList<Entite> courriel_enAttente = new ArrayList<>();
    static ArrayList<Entite> appelTelephonique_enAttente = new ArrayList<>();





    static void run() {
        System.out.println("====================================");
        System.out.println("=            STATISTIQUES          =");
        System.out.println("====================================");
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("COURRIELS");
        System.out.println("Nombre de courriels recus dans la nuit : " + n_courrielNuit);
        System.out.println("Nombre de courriels traites : "+ courriel_traites);
        System.out.println("Nombre de courriels arrives : "+courriel_arrives);
        System.out.println("Nombre de courriels non traites : "+(courriel_arrives-courriel_traites));
        System.out.println("Temps moyen dans le systeme : " + courrielTempsSysteme());
        System.out.println("Temps d'attente avant traitement : " + courrielAttenteAvantTraitement());
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("APPELS TELEPHONIQUES");
        System.out.println("Nombre d'appels traites : "+appel_traites);
        System.out.println("Nombre d'appels arrives : "+appel_arrives);
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
        for (int i = 0; i < Statistique.courriel_traite.size(); i++) {
            Entite courriel = Statistique.courriel_traite.get(i);
            count += (courriel.heure_debut_traitement - courriel.heure_arrivee);
        }
        return count / Statistique.courriel_traite.size();

    }

    private static float courrielTempsSysteme() {
        int count = 0;
        for (int i = 0; i < Statistique.courriel_traite.size(); i++) {
            Entite courriel = Statistique.courriel_traite.get(i);
            count += (courriel.heure_fin_traitement - courriel.heure_arrivee);
        }
        return count / Statistique.courriel_traite.size();
    }

    private static float posteMoyen() {
        return 0;
    }

    private static float travailMoyenAppel() {
        float count = 0;
        for (int i = 0; i < Statistique.N; i++) {
            count += bureau.get(i).tempsDeTravail_AppelTelephonique;
        }
        return count / Statistique.N;
    }

    private static float travailMoyenCourriel() {
        float count = 0;
        for (int i = 0; i < Statistique.N; i++) {
            count += bureau.get(i).tempsDeTravail_Courriel;
        }
        return count / Statistique.N;
    }

    private static float travailMoyen() {
        float count = 0;
        for (int i = 0; i < Statistique.N; i++) {
            count += bureau.get(i).tempsDeTravail;
        }
        return count / Statistique.N;
    }


    public static int getTeleconseillerCourriel() {
        int id = -1;
        for (int i = 0; i < Statistique.N; i++) {
            Teleconseiller teleconseiller = bureau.get(i);
            if(!teleconseiller.isOccupe && id == -1){
                teleconseiller.isOccupe = true;
                teleconseiller.isAffecteCourriel = true;
                teleconseiller.datefintache = date_simu;
                id = i;
            }
        }
        return id;
    }


    public static int getTeleconseillerAppel(){
        int id = -1;
        int count=0;
        for (int i = 0; i < Statistique.N; i++) {
            if(bureau.get(i).isOccupe && !bureau.get(i).isAffecteCourriel)
                count++;
        }
        if(count < NatMax){
            for (int i = 0; i < Statistique.N; i++) {
                Teleconseiller teleconseiller = bureau.get(i);
                if(!teleconseiller.isOccupe && id == -1){
                    teleconseiller.isOccupe = true;
                    teleconseiller.isAffecteCourriel = false;
                    teleconseiller.datefintache = date_simu;
                    id = i;
                }
            }
        }
        return id;
    }



    public static int getTeleconseillerFindeTache(float date){
        for (int i = 0; i < Statistique.N; i++) {
            Teleconseiller teleconseiller = bureau.get(i);
            if(teleconseiller.datefintache == date)
                return i;
        }
        return -1;
    }
}
