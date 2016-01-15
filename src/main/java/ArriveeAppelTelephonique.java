/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeAppelTelephonique extends Event {
    @Override
    public void run() {
        Entite newAppel = new Entite();
        newAppel.heure_arrivee = Statistique.date_simu;
        Statistique.appelTelephonique_enAttente.add(Statistique.appelTelephonique_enAttente.size(), newAppel); // Insertion à la fin

        // Génération d'une nouvelle arrivée d'appel
        float t_c = Generateur.loi_exponentielle_appelTelephonique(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_c, new ArriveeAppelTelephonique());


        // Génération d'un nouveau départ d'appel
        int id = Statistique.getTeleconseillerAppel();

        // Un téléconseiller est disponible
        if (id != -1) {
            Statistique.n_aOccupe++;
            float t = Generateur.loi_uniforme(5, 15);
            Statistique.bureau.get(id).datefintache = Statistique.date_simu + t;
            Statistique.bureau.get(id).tempsDeTravail += t;
            Statistique.bureau.get(id).tempsDeTravail_AppelTelephonique += t;
            Statistique.appelTelephonique_enAttente.get(0).heure_debut_traitement = Statistique.date_simu;
            Statistique.appelTelephonique_enAttente.get(0).heure_fin_traitement = Statistique.date_simu + t;
            Timing.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique());
        }
        Statistique.appel_arrives++;
    }
}
