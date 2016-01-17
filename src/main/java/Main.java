import Evenements.Depart;
import Evenements.Event;
import Evenements.Ordonnanceur;
import Ressources.Bureau;
import Statistiques.Statistique;

/**
 * Created by Larry on 08/01/2016.
 */
public class Main {
    public static void main(String[] args) {
        Bureau.init();

        Event event = new Depart();
        event.run();

        while (!Statistique.isEnd) {
            event = Ordonnanceur.getNextEvenement();
            event.run();
        }

        Statistique.run();
    }
}