/**
 * Created by Larry on 11/01/2016.
 */
public class Fin extends Event{
    @Override
    public void run() {
        Statistique.isEnd = true;
    }
}
