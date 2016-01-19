package Ressources;

public class Teleconseiller {
    static final boolean COURRIEL = true;
    static final boolean APPEL = false;

    public final int id;
    public float tempsDeTravail;
    public float tempsDeTravail_Courriel;
    public float tempsDeTravail_AppelTelephonique;
    public boolean isOccupe;
    public boolean isAffecteCourriel;

    public Teleconseiller(int id,boolean affecteCourriel) {
        this.id = id;
        this.isOccupe = false;
        this.isAffecteCourriel = affecteCourriel;
    }

}
