package projetcalculatrice;

import javax.swing.SwingUtilities;

import projetcalculatrice.Controller.*;
import projetcalculatrice.Model.*;
import projetcalculatrice.View;

public class Run {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                Model model = new Model();
                View view = new View(model);
                Controller controller = new Controller(model, view);

                model.addObserver(view);

            }
        });

    }

}