import java.util.*;

/**
 * Created by Larry on 08/01/2016.
 */
public class Timing {
    private static TreeMap<Float, ArrayList<Integer>> time = new TreeMap<>(Comparator.<Float>naturalOrder());
    private static int endSimulation;


    static void addNewEvenement(Float date, Integer idEvent){
        ArrayList<Integer> newList = time.get(date);
        if(newList == null || newList.isEmpty()){
            newList = new ArrayList<>();
        }
        newList.add(idEvent);
        time.put(date,newList);

        System.out.println("Ajout de l'evenement " + Evenement.displayEvenementName(idEvent) + " d'identifiant "+idEvent);
        time.put(date,newList);
    }

    static Integer getNextEvenement() {
        ArrayList<Integer> list = time.firstEntry().getValue();
        Integer idEvent = null;
        if(list == null || list.isEmpty()){
            // Suppression de l'événement dans le TreeMap
            time.remove(time.firstEntry().getKey());
            return getNextEvenement();
        }else{
            idEvent = list.get(0);
            list.remove(0);
            Float key = time.firstEntry().getKey();

            System.out.println("\nEvenement " + Evenement.displayEvenementName(idEvent) + " a lieu. Date = " + key.toString());


            return idEvent;
        }
    }

}
