
/**
 * Created by Larry on 08/01/2016.
 */
public class Main {
    public static void main(String[] args) {
        Event event = new Depart();
        event.run();

        while (!Statistique.isEnd) {
            event = Timing.getNextEvenement();
            event.run();
        }

        Statistique.run();
    }
}
