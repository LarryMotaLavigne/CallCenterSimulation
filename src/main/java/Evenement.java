import java.util.ArrayList;

/**
 * Created by Larry on 08/01/2016.
 */
public class Evenement {
    /****************************************************/
    /**                 ID DES EVENEMENTS              **/
    /****************************************************/
    final static int EvenementDepart = 0;
    final static int EvenementArriveeAppelTelephonique = 1;
    final static int EvenementArriveeCourriel = 2;
    final static int EvenementReponseAppelTelephonique = 3;
    final static int EvenementReponseCourriel = 4;
    final static int EvenementFin = 5;


    /****************************************************/
    /**                     VARIABLES                  **/
    /****************************************************/
    static ArrayList<Teleconseiller> bureau_appels_telephoniques = new ArrayList<>();
    static ArrayList<Teleconseiller> bureau_courriels = new ArrayList<>();
    static int Nc = 5;
    static int Nat = 5;
    static boolean isEnd = false;
    static float dateFinSimu = 240;
    static int n_aOccupe = 0; // Nombre de téléconseillers affectés aux appels téléphoniques
    static int n_cOccupe = 0; // Nombre de téléconseillers affectés aux courriels
    static float date_simu = 0;
    static float date_derniereSimu = 0;

    static void run(int idEvent) {
        switch (idEvent) {
            case EvenementDepart:
                depart();
                break;
            case EvenementArriveeAppelTelephonique:
                arriveeAppelTelephonique();
                break;
            case EvenementArriveeCourriel:
                arriveeCourriel();
                break;
            case EvenementReponseAppelTelephonique:
                reponseAppelTelephonique();
                break;
            case EvenementReponseCourriel:
                reponseCourriel();
                break;
            case EvenementFin:
                fin();
                break;
            default:
                break;
        }
    }

    static String displayEvenementName(int idEvent) {
        switch (idEvent) {
            case EvenementDepart:
                return "EvenementDepart";
            case EvenementArriveeAppelTelephonique:
                return "arriveeAppelTelephonique";
            case EvenementArriveeCourriel:
                return "arriveeCourriel";
            case EvenementReponseAppelTelephonique:
                return "reponseAppelTelephonique";
            case EvenementReponseCourriel:
                return "reponseCourriel";
            case EvenementFin:
                return "fin";
            default:
                return "";
        }
    }

    static void depart() {
        System.out.println("-> " + new Object() {
        }.getClass().getEnclosingMethod().getName());
        // Initialisation des téléconseillers
        for (int i = 0; i < Nc; i++) {
            bureau_appels_telephoniques.add(new Teleconseiller(i));
        }
        for (int i = Nc; i < Nat + Nc; i++) {
            bureau_courriels.add(new Teleconseiller(i));
        }

        // Génération de l'événement de FIN
        Timing.addNewEvenement(dateFinSimu, EvenementFin);


        // Génération des événements pour le courriel de nuit
        Statistique.n_courrielNuit = Generateur.loi_uniforme(20, 80);
        for (int i = 0; i < Statistique.n_courrielNuit; i++) {
            Timing.addNewEvenement(date_simu, EvenementArriveeCourriel);
        }

        // Génération de l'événement aléatoire d'arrivée d'un appel téléphonique
        float t_at = Generateur.loi_exponentielle_appelTelephonique(date_simu);
        Timing.addNewEvenement(date_simu + t_at, EvenementArriveeAppelTelephonique);

        // Génération de l'événement aléatoire de d'arrivée de courriel
        float t_c = Generateur.loi_exponentielle_courriel(date_simu);
        Timing.addNewEvenement(date_simu + t_c, EvenementArriveeCourriel);

    }

    static void arriveeAppelTelephonique() {
        System.out.println("-> " + new Object() {
        }.getClass().getEnclosingMethod().getName());

    }

    static void arriveeCourriel() {
        System.out.println("-> " + new Object() {
        }.getClass().getEnclosingMethod().getName());

    }

    static void reponseAppelTelephonique() {
        System.out.println("-> " + new Object() {
        }.getClass().getEnclosingMethod().getName());

    }

    static void reponseCourriel() {
        System.out.println("-> " + new Object() {
        }.getClass().getEnclosingMethod().getName());

    }

    static void fin() {
        System.out.println("-> " + new Object() {
        }.getClass().getEnclosingMethod().getName());
        isEnd = true;
    }
}
