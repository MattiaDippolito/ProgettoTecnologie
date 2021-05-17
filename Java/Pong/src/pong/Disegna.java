/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniele
 */
public class Disegna extends Thread {

    JFrame f;
    int FPS;
    Condivisa cond = Condivisa.getIstance();

    public Disegna(JFrame f, int i) {
        this.f = f;
        FPS = 1000 / i;
    }

    @Override
    public void run() {
        while (!cond.Finito()) {
            f.repaint();
            try {
                Thread.sleep(FPS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Disegna.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        f.repaint();
    }

}
