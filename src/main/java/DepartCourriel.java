/**
 * Created by Larry on 11/01/2016.
 */
public class DepartCourriel extends Event {
    @Override
    public void run() {
        Entite courriel = Statistique.courriel_enAttente.remove(0);
        Statistique.courriel_traite.add(courriel);
        Statistique.courriel_traites++;

        int id = Statistique.getTeleconseillerCourrielFindeTache(Statistique.date_simu);
        Statistique.bureau.get(id).datefintache = 0;
        Statistique.bureau.get(id).isOccupe = false;
    }
}
