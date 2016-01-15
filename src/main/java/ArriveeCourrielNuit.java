/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourrielNuit extends Event {
    @Override
    public void run() {
        Entite newCourriel = new Entite();
        newCourriel.heure_arrivee = Statistique.date_simu;
        Statistique.courriel_enAttente.add(Statistique.courriel_enAttente.size(), newCourriel); // Insertion à la fin

        // Génération d'un nouveau départ de Courriel
        int id = Statistique.getTeleconseillerCourriel();

        // Un téléconseiller est disponible
        if (id != -1) {
            Statistique.n_cOccupe++;
            float t = Generateur.loi_uniforme(3, 7);
            Statistique.bureau.get(id).datefintache = Statistique.date_simu + t;
            Statistique.courriel_enAttente.get(0).heure_debut_traitement = Statistique.date_simu;
            Statistique.courriel_enAttente.get(0).heure_fin_traitement = Statistique.date_simu + t;
            Timing.addNewEvenement(Statistique.date_simu + t, new DepartCourriel());
        }
        Statistique.courriel_arrives++;
    }
}
