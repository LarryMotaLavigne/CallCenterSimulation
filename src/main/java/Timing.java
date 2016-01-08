import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Larry on 08/01/2016.
 */
public class Timing {
    TreeMap<Integer, Evenement> time = new TreeMap<Integer, Evenement>(Comparator.<Integer>naturalOrder());


    Evenement getNextEvenement() {
        Evenement event = time.firstEntry().getValue();
        System.out.println("Evenement " + event.nom + " d'une durée de " + event.duree + " a lieu.");
        return event;
    }

}
