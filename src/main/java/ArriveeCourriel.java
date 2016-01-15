/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourriel extends Event {
    @Override
    public void run() {
        Entite newCourriel = new Entite();
        newCourriel.heure_arrivee = Statistique.date_simu;
        Statistique.list_courriel.add(Statistique.list_courriel.size(),newCourriel); // Insertion � la fin

        // G�n�ration d'une nouvelle arriv�e de Courriel
        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());


        // G�n�ration d'un nouveau d�part de Courriel
        boolean isAttributed = false;
        // On regarde si un teleconseiller courriel est pr�sent
        for (int i = 0; i < Statistique.N; i++) {
            if(Statistique.bureau.get(i).isAffecteCourriel && !Statistique.bureau.get(i).isOccupe) {
                Statistique.bureau.get(i).isOccupe = true;
                isAttributed = true;
            }
        }
        // Si tous les teleconseillers Courriel sont pris, on regarde du cot� des AT
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
            // Tous les conseillers sont occup�s
        }

        if(Statistique.n_cOccupe > Statistique.Nc){

        }else{
            Statistique.n_cOccupe++;
        }






        Statistique.courriel_arrives++;

        Statistique.list_courriel.add(newCourriel);




    }
}
