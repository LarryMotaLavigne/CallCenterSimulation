/**
 * Created by Larry on 08/01/2016.
 */
public class Teleconseiller {

    final int id;
    float tempsDeTravail;
    float tempsDeTravail_Courriel;
    float tempsDeTravail_AppelTelephonique;
    boolean isOccupe;
    boolean isAffecteCourriel;
    float datefintache;

    public Teleconseiller(int id,boolean affecteCourriel) {
        this.id = id;
        this.isOccupe = false;
        this.isAffecteCourriel = affecteCourriel;
    }

}
