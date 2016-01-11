import java.util.ArrayList;

/**
 * Created by Larry on 09/01/2016.
 */
public class Statistique {

    /****************************************************/
    /**             VARIABLES DE STATISTIQUES          **/
    /****************************************************/
    static int courriel_arrives = 0; // Nombre de courriels arriv�s
    static int courriel_traites = 0; // Nombre de courriels trait�s

    static int appel_arrives = 0; // Nombre d'appels arriv�s
    static int appel_traites = 0; // Nombre d'appels trait�s

    static int n_courrielNuit = 0; // Nombre de courriels re�u dans la nuit




    /****************************************************/
    /**                     VARIABLES                  **/
    /****************************************************/
    static ArrayList<Teleconseiller> bureau;
    static ArrayList<Entite> entite = new ArrayList<>();
    static int Nc = 5;
    static int Nat = 5;
    static int N = Nc + Nat;
    static int NatMax = 5;
    static boolean isEnd = false;
    static float dateFinSimu = 240;
    static int n_aOccupe = 0; // Nombre de t�l�conseillers affect�s aux appels t�l�phoniques
    static int n_cOccupe = 0; // Nombre de t�l�conseillers affect�s aux courriels
    static float date_simu = 0;
    static float date_derniereSimu = 0;



    static void run() {
        System.out.println("====================================");
        System.out.println("=            STATISTIQUES          =");
        System.out.println("====================================");
        System.out.println("Nombre de courriels recus dans la nuit : " + n_courrielNuit);

    }

}
