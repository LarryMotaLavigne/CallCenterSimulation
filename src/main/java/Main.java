import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Larry on 08/01/2016.
 */
public class Main {
    int n_aOccupe; // Nombre de téléconseillers affectés aux appels téléphoniques
    int n_cOccupe; // Nombre de téléconseillers affectés aux courriels
    int courriel_arrives; // Nombre de courriels arrivés
    int courriel_traites; // Nombre de courriels traités
    int appel_arrives; // Nombre d'appels arrivés
    int appel_traites; // Nombre d'appels traités


    List<Integer> bureau_appels_telephoniques = new ArrayList<Integer>();
    List<Integer> bureau_courriels = new ArrayList<Integer>();


    float date_simu;
    float date_derniereSimu;

    Random rand = new Random();

    void depart(int Nc, int Nat){
        // Ajout des téléconseillers (avoir s'il ne faut pas créer une classe téléconseiller
        for (int i = 0; i < Nc; i++) {
            bureau_appels_telephoniques.add(i);
        }
        for (int i = Nc; i < Nat + Nc; i++) {
            bureau_courriels.add(i);
        }
        n_aOccupe = 0;
        n_cOccupe = 0;
        courriel_arrives = 0;
        courriel_traites = 0;
        appel_arrives = 0;
        appel_traites = 0;
        date_simu = 0;
        date_derniereSimu = 0;

        rand.nextInt(61) + 20

    }

}
