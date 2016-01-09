import java.util.ArrayList;

/**
 * Created by Larry on 08/01/2016.
 */
public class Evenement {
    static ArrayList<Teleconseiller> bureau_appels_telephoniques;
    static ArrayList<Teleconseiller> bureau_courriels;

    static int Nc = 5;
    static int Nat = 5;

    static boolean isEnd = false;

    static int n_aOccupe; // Nombre de téléconseillers affectés aux appels téléphoniques
    static int n_cOccupe; // Nombre de téléconseillers affectés aux courriels

    static int courriel_arrives; // Nombre de courriels arrivés
    static int courriel_traites; // Nombre de courriels traités

    static int appel_arrives; // Nombre d'appels arrivés
    static int appel_traites; // Nombre d'appels traités

    static int n_courrielNuit; // Nombre de courriels reçu dans la nuit

    static float date_simu;
    static float date_derniereSimu;


    static void run(int idEvent){
        switch (idEvent){
            case 0:
                depart();
                break;
            case 1:
                arriveeAppelTelephonique();
                break;
            case 2:
                arriveeCourriel();
                break;
            case 3:
                reponseAppelTelephonique();
                break;
            case 4:
                reponseCourriel();
                break;
            case 5:
                fin();
                break;
            default:
                break;
        }
    }

    static void depart(){
            // Ajout des téléconseillers
            for (int i = 0; i < Nc; i++) {
                
                bureau_appels_telephoniques.add(new Teleconseiller(i));
            }
            for (int i = Nc; i < Nat + Nc; i++) {
                bureau_courriels.add(new Teleconseiller(i));
            }
            n_aOccupe = 0;
            n_cOccupe = 0;
            courriel_arrives = 0;
            courriel_traites = 0;
            appel_arrives = 0;
            appel_traites = 0;
            date_simu = 0;
            date_derniereSimu = 0;

            Evenement.fin();

            n_courrielNuit = Generateur.loi_uniforme(20,80);
        }

    static void arriveeAppelTelephonique(){

    }

    static void arriveeCourriel(){

    }

    static void reponseAppelTelephonique(){

    }

    static void reponseCourriel(){

    }

    static void fin(){

    }
}
