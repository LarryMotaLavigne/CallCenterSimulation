package Entites;

public class Entite {
    float heure_arrivee = 0;
    float heure_debut_traitement = 0;
    float heure_fin_traitement = 0;

    public float getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(float heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public float getHeure_debut_traitement() {
        return heure_debut_traitement;
    }

    public void setHeure_debut_traitement(float heure_debut_traitement) {
        this.heure_debut_traitement = heure_debut_traitement;
    }

    public float getHeure_fin_traitement() {
        return heure_fin_traitement;
    }

    public void setHeure_fin_traitement(float heure_fin_traitement) {
        this.heure_fin_traitement = heure_fin_traitement;
    }
}
