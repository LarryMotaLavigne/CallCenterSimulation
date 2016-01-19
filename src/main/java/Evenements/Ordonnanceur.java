package Evenements;

import Simulation.Simulation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class Ordonnanceur {
    private static TreeMap<Float, ArrayList<Event>> time = new TreeMap<>(Comparator.<Float>naturalOrder());

    public static void addNewEvenement(Float date, Event event) {
        ArrayList<Event> newList = time.get(date);
        if (newList == null || newList.isEmpty()) {
            newList = new ArrayList<>();
        }
        newList.add(event);
        time.put(date, newList);
    }

    public static Event getNextEvenement() {
        ArrayList<Event> list = time.firstEntry().getValue();
        Event event = null;
        if (list == null || list.isEmpty()) {
            // Suppression de l'événement dans le TreeMap
            time.remove(time.firstEntry().getKey());
            return getNextEvenement();
        } else {
            event = list.get(0);
            list.remove(0);
            Float key = time.firstEntry().getKey();

            //System.out.println("\nEvenement " + event.getClass().getName() + " a lieu. Date = " + key.toString());

            Simulation.date_simu = key;

            return event;
        }
    }

    public static void clearAndRestart() {
        time.clear();
        time = new TreeMap<>(Comparator.<Float>naturalOrder());
    }

}
