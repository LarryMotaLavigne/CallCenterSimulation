package Ressources;

public class Poste {
    protected int id;
    protected float tempsUtilisation;
    protected boolean isOccupe;

    public Poste(int idPoste){
        this.id = idPoste;
        this.tempsUtilisation = 0;
        this.isOccupe = false;
    }

    public void addTempsUtilisation(float t){
        this.tempsUtilisation += t;
    }

    public float getTempsUtilisation(){
        return this.tempsUtilisation;
    }
}
