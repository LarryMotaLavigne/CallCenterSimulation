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

    static void run(){
        System.out.println("====================================");
        System.out.println("=            STATISTIQUES          =");
        System.out.println("====================================");
        System.out.println("Nombre de courriels reçus dans la nuit : " + n_courrielNuit);

    }

}
