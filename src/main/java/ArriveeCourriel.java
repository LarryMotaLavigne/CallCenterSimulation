/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourriel extends Event {
    @Override
    public void run() {
        /*****************************************************************************/
        /*******************       RECEPTION d'un COURRIEL       *********************/
        /*****************************************************************************/
        Entite newCourriel = new Entite();
        newCourriel.heure_arrivee = Statistique.date_simu;
        Statistique.courriel_enAttente.add(Statistique.courriel_enAttente.size(), newCourriel); // Insertion à la fin
        Statistique.courriel_arrives++;


        /*****************************************************************************/
        /*******************       NOUVEL EVENEMENT ARRIVEE      *********************/
        /*****************************************************************************/
        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());


        /*****************************************************************************/
        /*******************            DEPART COURRIEL          *********************/
        /*****************************************************************************/
        int id = Statistique.getTeleconseillerCourriel();
        if (id != -1 && Statistique.courriel_enAttente.size() > 0) { // Si un téléconseiller est disponible et si on dispose de courriels à traiter
            Statistique.n_cOccupe++;

            float t = Generateur.loi_uniforme(3, 7);

            Statistique.bureau.get(id).datefintache = Statistique.date_simu + t;
            Statistique.bureau.get(id).tempsDeTravail += t;
            Statistique.bureau.get(id).tempsDeTravail_Courriel += t;

            Statistique.courriel_enAttente.get(0).heure_debut_traitement = Statistique.date_simu;
            Statistique.courriel_enAttente.get(0).heure_fin_traitement = Statistique.date_simu + t;

            Timing.addNewEvenement(Statistique.date_simu + t, new DepartCourriel());
        }

    }
}
