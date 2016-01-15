/**
 * Created by Larry on 11/01/2016.
 */
public class DepartAppelTelephonique extends Event {
    @Override
    public void run() {
        if(Statistique.appelTelephonique_enAttente.size() > 0){
            /*****************************************************************************/
            /*******************          REPONSE A UN APPEL         *********************/
            /*****************************************************************************/
            Entite appel = Statistique.appelTelephonique_enAttente.remove(0);
            Statistique.appelTelephonique_traite.add(appel);
            Statistique.appel_traites++;

            /*****************************************************************************/
            /*******************   FIN DE TACHE DU TELECONSEILLER    *********************/
            /*****************************************************************************/
            int id = Statistique.getTeleconseillerFindeTache(Statistique.date_simu);
            Statistique.bureau.get(id).datefintache = 0;
            Statistique.bureau.get(id).isOccupe = false;


            /*****************************************************************************/
            /*******************            TACHE SUIVANTE           *********************/
            /*****************************************************************************/

            // S'il y a un appel, il est prioritaire
            if (Statistique.appelTelephonique_enAttente.size() > 0) {
                id = Statistique.getTeleconseillerAppel();
                if (id != -1) {
                    Statistique.n_aOccupe++;
                    float t = Generateur.loi_uniforme(5, 15);

                    Statistique.bureau.get(id).isAffecteCourriel = false;
                    Statistique.bureau.get(id).datefintache = Statistique.date_simu + t;
                    Statistique.bureau.get(id).tempsDeTravail += t;
                    Statistique.bureau.get(id).tempsDeTravail_AppelTelephonique += t;

                    Statistique.appelTelephonique_enAttente.get(0).heure_debut_traitement = Statistique.date_simu;
                    Statistique.appelTelephonique_enAttente.get(0).heure_fin_traitement = Statistique.date_simu + t;

                    Timing.addNewEvenement(Statistique.date_simu + t, new DepartAppelTelephonique());
                }
            }
            // Sinon, on s'occupe des courriels
            else if (Statistique.courriel_enAttente.size() > 0) {
                id = Statistique.getTeleconseillerCourriel();
                if (id != -1) { // Si un téléconseiller est disponible
                    Statistique.n_cOccupe++;
                    float t = Generateur.loi_uniforme(3, 7);

                    Statistique.bureau.get(id).isAffecteCourriel = true;
                    Statistique.bureau.get(id).datefintache = Statistique.date_simu + t;
                    Statistique.bureau.get(id).tempsDeTravail += t;
                    Statistique.bureau.get(id).tempsDeTravail_Courriel += t;

                    Statistique.courriel_enAttente.get(0).heure_debut_traitement = Statistique.date_simu;
                    Statistique.courriel_enAttente.get(0).heure_fin_traitement = Statistique.date_simu + t;

                    Timing.addNewEvenement(Statistique.date_simu + t, new DepartCourriel());
                }
            }

        }
    }
}
