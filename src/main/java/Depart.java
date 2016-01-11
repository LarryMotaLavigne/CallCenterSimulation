import java.util.ArrayList;

/**
 * Created by Larry on 11/01/2016.
 */
public class Depart extends Event{

    @Override
    public void run() {
        System.out.println("-> " + this.getClass().getName());
        // Initialisation des t�l�conseillers
        Statistique.bureau = new ArrayList<>();
        // Courriel
        for (int i = 0; i < Statistique.Nc; i++) {
            Statistique.bureau.add(new Teleconseiller(i));
        }
        // Appel t�l�phonique
        for (int i = Statistique.Nc; i < Statistique.Nat + Statistique.Nc; i++) {
            Statistique.bureau.add(new Teleconseiller(i));
        }

        // G�n�ration de l'�v�nement de FIN
        Timing.addNewEvenement(Statistique.dateFinSimu, new Fin());


        // G�n�ration des �v�nements pour le courriel de nuit
        Statistique.n_courrielNuit = Generateur.loi_uniforme(20, 80);
        for (int i = 0; i < Statistique.n_courrielNuit; i++) {
            Timing.addNewEvenement(Statistique.date_simu, new ArriveeCourrielNuit());
        }

        // G�n�ration de l'�v�nement al�atoire d'arriv�e d'un appel t�l�phonique
        float t_at = Generateur.loi_exponentielle_appelTelephonique(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_at, new ArriveeAppelTelephonique());

        // G�n�ration de l'�v�nement al�atoire de d'arriv�e de courriel
        float t_c = Generateur.loi_exponentielle_courriel(Statistique.date_simu);
        Timing.addNewEvenement(Statistique.date_simu + t_c, new ArriveeCourriel());

    }
}
