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

    static void run(){
        System.out.println("====================================");
        System.out.println("=            STATISTIQUES          =");
        System.out.println("====================================");
        System.out.println("Nombre de courriels re�us dans la nuit : " + n_courrielNuit);

    }

}
