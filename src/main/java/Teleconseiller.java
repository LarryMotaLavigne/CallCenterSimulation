/**
 * Created by Larry on 08/01/2016.
 */
public class Teleconseiller {

    final int id;
    float tempsDeTravail;
    float tempsDeTravail_Courriel;
    float tempsDeTravail_AppelTelephonique;
    boolean occupeCourriel;
    boolean occupeAppelTelephonique;

    public Teleconseiller(int id) {
        this.id = id;
        this.occupeCourriel = false;
        this.occupeAppelTelephonique = false;
    }


    public int getId() {
        return id;
    }

    public float getTempsDeTravail() {
        return tempsDeTravail;
    }

    public void setTempsDeTravail(float tempsDeTravail) {
        this.tempsDeTravail = tempsDeTravail;
    }

    public float getTempsDeTravail_Courriel() {
        return tempsDeTravail_Courriel;
    }

    public void setTempsDeTravail_Courriel(float tempsDeTravail_Courriel) {
        this.tempsDeTravail_Courriel = tempsDeTravail_Courriel;
    }

    public float getTempsDeTravail_AppelTelephonique() {
        return tempsDeTravail_AppelTelephonique;
    }

    public void setTempsDeTravail_AppelTelephonique(float tempsDeTravail_AppelTelephonique) {
        this.tempsDeTravail_AppelTelephonique = tempsDeTravail_AppelTelephonique;
    }

    public boolean isOccupeCourriel() {
        return occupeCourriel;
    }

    public void setOccupeCourriel(boolean occupeCourriel) {
        this.occupeCourriel = occupeCourriel;
    }

    public boolean isOccupeAppelTelephonique() {
        return occupeAppelTelephonique;
    }

    public void setOccupeAppelTelephonique(boolean occupeAppelTelephonique) {
        this.occupeAppelTelephonique = occupeAppelTelephonique;
    }
}
