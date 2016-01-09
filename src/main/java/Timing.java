import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Larry on 08/01/2016.
 */
public class Timing {
    private static TreeMap<Integer, Integer> time = new TreeMap<Integer, Integer>(Comparator.<Integer>naturalOrder());
    private static int endSimulation;

    static void addNewEvenement(Integer idEvent, Integer date){
        time.put(date,idEvent);
    }

    static Integer getNextEvenement() {
        Integer idEvent = time.firstEntry().getValue();
        Integer key = time.firstEntry().getKey();

        System.out.println("Evenement " + "" + " d'une durée de " + "event.duree" + " a lieu.");
        time.remove(time.firstEntry().getKey());
        
        return idEvent;
    }

}
