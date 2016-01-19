import Evenements.Debut;
import Evenements.Event;
import Evenements.Ordonnanceur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;

public class Main {
    public static void main(String[] args) {
        int nbIterations = 10000; // Nombre d'itérations
        int Nc = 8; // Nombre de téléconseillers attribués aux courriels
        int Na = 3; // Nombre de téléconseillers attribués aux appels
        // ATTENTION : NaMax doit être >= à Na
        int NaMax = 11; // Quantité de postes disponibles pour les appels téléphoniques

        Simulation.dateDebutSimu = 0;
        Simulation.date_simu = Simulation.dateDebutSimu;

        for (int i = 0; i < nbIterations; i++) {
            Bureau.init(Nc,Na,NaMax);

            Event event = new Debut();
            event.run();

            while (!Simulation.isEnd) {
                event = Ordonnanceur.getNextEvenement();
                event.run();
            }
            Statistique.run(false);
        }
        Statistique.runGlobale(nbIterations);
    }
}