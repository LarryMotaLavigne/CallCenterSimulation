import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by Larry on 08/01/2016.
 */
public class Timing {
    private static TreeMap<Float, ArrayList<Event>> time = new TreeMap<>(Comparator.<Float>naturalOrder());

    static void addNewEvenement(Float date, Event event) {
        ArrayList<Event> newList = time.get(date);
        if (newList == null || newList.isEmpty()) {
            newList = new ArrayList<>();
        }
        newList.add(event);
        time.put(date, newList);

//        System.out.println("Ajout de l'evenement " + Evenement.displayEvenementName(idEvent) + " d'identifiant " + idEvent);
    }

    static Event getNextEvenement() {
        ArrayList<Event> list = time.firstEntry().getValue();
        Event event = null;
        if (list == null || list.isEmpty()) {
            // Suppression de l'�v�nement dans le TreeMap
            time.remove(time.firstEntry().getKey());
            return getNextEvenement();
        } else {
            event = list.get(0);
            list.remove(0);
            Float key = time.firstEntry().getKey();

            System.out.println("\nEvenement " + event.getClass().getName() + " a lieu. Date = " + key.toString());

            Statistique.date_derniereSimu = Statistique.date_simu;
            Statistique.date_simu = key;

            return event;
        }
    }

}
