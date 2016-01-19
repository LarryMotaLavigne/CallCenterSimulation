import Evenements.Debut;
import Evenements.Event;
import Evenements.Ordonnanceur;
import Ressources.Bureau;
import Simulation.Simulation;
import Statistiques.Statistique;

public class Main {
    public static void main(String[] args) {
        int nbIterations = 100; // Nombre d'itérations
        int Nc = 1; // Nombre de téléconseillers attribués aux courriels
        int Na = 1; // Nombre de téléconseillers attribués aux appels
        int NaMax = 1; // Quantité de postes disponibles pour les appels téléphoniques

        for (int i = 0; i < nbIterations; i++) {
            Bureau.init(Nc,Na,NaMax);

            Event event = new Debut();
            event.run();

            while (!Simulation.isEnd) {
                event = Ordonnanceur.getNextEvenement();
                event.run();
            }
            Statistique.run();
        }
        Statistique.runGlobale(nbIterations);
    }
}