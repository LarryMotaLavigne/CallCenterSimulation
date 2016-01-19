package Evenements;

import Simulation.Simulation;

public class Fin extends Event {
    @Override
    public void run() {
        Simulation.isEnd = true;
    }
}
