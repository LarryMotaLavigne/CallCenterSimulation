/**
 * Created by Larry on 11/01/2016.
 */
public class DepartAppelTelephonique extends Event {
    @Override
    public void run() {
        Entite appel = Statistique.appelTelephonique_enAttente.remove(0);
        Statistique.appelTelephonique_traite.add(appel);
        Statistique.appel_traites++;

        int id = Statistique.getTeleconseillerAppelFindeTache(Statistique.date_simu);
        Statistique.bureau.get(id).datefintache = 0;
        Statistique.bureau.get(id).isOccupe = false;
    }
}
