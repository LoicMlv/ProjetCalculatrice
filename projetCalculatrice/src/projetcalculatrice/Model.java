package projetcalculatrice;

import autrevent.*;
import java.util.ArrayList;

/**
 *
 * @author loic.malvoisin
 * @author gaetan.pichout
 */
public class Model {

    private AutreEventNotifieur notifieur = new AutreEventNotifieur();

    public void addAutreEventListener(AutreEventListener listener) {
        notifieur.addAutreEventListener(listener);
    }

    public void removeAutreEventListener(AutreEventListener listener) {
        notifieur.removeAutreEventListener(listener);
    }
}