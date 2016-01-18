package Evenements;

import Evenements.Event;
import Simulation.Simulation;
import Statistiques.Statistique;

/**
 * Created by Larry on 11/01/2016.
 */
public class Fin extends Event {
    @Override
    public void run() {
        Simulation.isEnd = true;
    }
}
