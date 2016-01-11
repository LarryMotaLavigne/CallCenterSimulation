/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeCourriel extends Event {
    @Override
    public void run() {
        Entite newCourriel = new Courriel();

        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());

        for (int i = 0; i < Statistique.N; i++) {
            // Si le téléconseiller n'est pas occupé
            if(!Statistique.bureau.get(i).isOccupeCourriel() && !Statistique.bureau.get(i).isOccupeAppelTelephonique()){
                Statistique.bureau.get(i).setOccupeCourriel(true);
            }
        }

        if(Statistique.n_cOccupe > Statistique.Nc){

        }else{
            Statistique.n_cOccupe++;
        }






        Statistique.courriel_arrives++;
        this.entite = newCourriel;
        this.entite.heure_arrivee = Statistique.date_simu;




    }
}
