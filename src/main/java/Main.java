import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Larry on 08/01/2016.
 */
public class Main {
    public static void main(String[] args){
        Evenement.depart();

        while(!Evenement.isEnd){
            int idEvent = Timing.getNextEvenement();
            Evenement.run(idEvent);
        }

        Statistique.run();
    }
}
