import Evenements.Debut;
import Evenements.Event;
import Evenements.Ordonnanceur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;

/**
 * Created by Larry on 08/01/2016.
 */
public class Main {
    public static void main(String[] args) {
        Bureau.init();

        Event event = new Debut();
        event.run();

        while (!Simulation.isEnd) {
            event = Ordonnanceur.getNextEvenement();
            event.run();
        }

        Statistique.run();
    }
}