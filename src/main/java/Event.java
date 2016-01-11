/**
 * Created by Larry on 11/01/2016.
 */
public abstract class Event {
    Entite entite;

    public Event() {
        this.entite = null;
    }

    public abstract void run();
}
