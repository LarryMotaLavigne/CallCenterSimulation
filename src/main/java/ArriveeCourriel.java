/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourriel extends Event {
    @Override
    public void run() {
        Entite newCourriel = new Entite();
        newCourriel.heure_arrivee = Statistique.date_simu;
        Statistique.list_courriel.add(Statistique.list_courriel.size(),newCourriel); // Insertion à la fin

        // Génération d'une nouvelle arrivée de Courriel
        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());


        // Génération d'un nouveau départ de Courriel
        boolean isAttributed = false;
        // On regarde si un teleconseiller courriel est présent
        for (int i = 0; i < Statistique.N; i++) {
            if(Statistique.bureau.get(i).isAffecteCourriel && !Statistique.bureau.get(i).isOccupe) {
                Statistique.bureau.get(i).isOccupe = true;
                isAttributed = true;
            }
        }
        // Si tous les teleconseillers Courriel sont pris, on regarde du coté des AT
        if(!isAttributed){
            for (int i = 0; i < Statistique.N; i++) {
                if(!Statistique.bureau.get(i).isAffecteCourriel && !Statistique.bureau.get(i).isOccupe) {
                    Statistique.bureau.get(i).isAffecteCourriel = true;
                    Statistique.bureau.get(i).isOccupe = true;
                    isAttributed = true;
                }
            }
        }
        if(!isAttributed){
            // Tous les conseillers sont occupés
        }

        if(Statistique.n_cOccupe > Statistique.Nc){

        }else{
            Statistique.n_cOccupe++;
        }






        Statistique.courriel_arrives++;

        Statistique.list_courriel.add(newCourriel);




    }
}
