/**
 * Created by Larry on 11/01/2016.
 */
public class ArriveeAppelTelephonique extends Event {
    @Override
    public void run() {
        Entite newAppel = new Entite();
        newAppel.heure_arrivee = Statistique.date_simu;

        // G�n�ration d'une nouvelle arriv�e d'Appels
        float t_at = Generateur.loi_exponentielle_appelTelephonique(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_at, new ArriveeAppelTelephonique());

        // G�n�ration d'un nouveau d�part d'Appels
        boolean isAttributed = false;
        for (int i = 0; i < Statistique.N; i++) {
            if(!Statistique.bureau.get(i).isAffecteCourriel && !Statistique.bureau.get(i).isOccupe){
                isAttributed = true;
                Statistique.bureau.get(i).isOccupe = true;
            }
        }


    }
}
