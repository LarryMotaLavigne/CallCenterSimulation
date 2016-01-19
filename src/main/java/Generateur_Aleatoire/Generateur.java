package Generateur_Aleatoire;

import java.util.Random;

public class Generateur {
    private static Random rand = new Random();

    public static int loi_uniforme(int borneInf, int borneSup) {
        return rand.nextInt(borneSup - borneInf + 1) + borneInf;

    }

    private static float loi_exponentielle(double lambda) {

        return (float) (-(1 / lambda) * Math.log(1 - rand.nextDouble()));
    }

    public static float loi_exponentielle_appelTelephonique(float date) {
        if (date <= 60) {
            return loi_exponentielle(1. / 5);
        } else if (date <= 180) {
            return loi_exponentielle(1.);
        } else {
            return loi_exponentielle(1. / 10);
        }
    }

    public static float loi_exponentielle_courriel(float date) {
        if (date <= 60) {
            return loi_exponentielle(2.);
        } else {
            return loi_exponentielle(1. / 5);
        }
    }
}
