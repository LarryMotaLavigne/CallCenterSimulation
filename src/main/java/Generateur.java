import java.util.Random;

/**
 * Created by Larry on 09/01/2016.
 */
public class Generateur {
    private static Random rand = new Random();

    public static int loi_uniforme(int borneInf, int borneSup){
        int result = rand.nextInt(borneSup-borneInf+1) + borneInf;
        return result;

    }

    public static double loi_exponentielle(double lambda) {
        return -(1 / lambda) * Math.log( 1 - rand.nextDouble() );
    }

}
