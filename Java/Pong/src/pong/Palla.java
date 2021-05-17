/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniele
 */
public class Palla extends Thread {

    int x, y;
    int direzioneX, direzioneY;
    JFrame f;
    Random r;
    Condivisa c = Condivisa.getIstance();
    int contatoreRimb;

    public Palla(JFrame f) {
        this.f = f;
        x = f.getWidth() / 2;
        y = f.getHeight() / 2;
        r = new Random();
        contatoreRimb = 0;
    }

    @Override
    public void run() {
        GeneraRandomDirezione();
        while (!c.Finito()) {
            if (c.g1.pronto && c.g2.pronto) {
                SetDirezione();
                Collisioni();
            }
            if (contatoreRimb <= 6 && contatoreRimb >= 3) {
                try {
                    sleep(3);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Palla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (contatoreRimb <= 9 && contatoreRimb > 6) {
                try {
                    sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Palla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (contatoreRimb >= 9) {
                try {
                    sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Palla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    sleep(4);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Palla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void GeneraRandomDirezione() {
        int dirX = r.nextInt(2) + 2;
        if (dirX == 2) {
            direzioneX = -1;
        } else if (dirX == 3) {
            direzioneX = 1;
        }
        int dirY = r.nextInt(2) + 2;
        if (dirY == 2) {
            direzioneY = -1;
        } else if (dirY == 3) {
            direzioneY = 1;
        }
    }

    public void SetDirezione() {
        if (direzioneX == -1) {
            x--;
        }
        if (direzioneX == 1) {
            x++;
        }
        if (direzioneY == -1) {
            y--;
        }
        if (direzioneY == 1) {
            y++;
        }
    }

    public void Collisioni() {
        // check lato sinistro
        if (this.x <= 100) {
            direzioneX = 1;
            x = f.getWidth() / 2;
            y = f.getHeight() / 2;
            c.g2.IncrementaPunteggio();
            // aggiungere punti e suono
        }
        // check lato destro
        if (this.x >= f.getWidth() - 120) {
            direzioneX = -1;
            x = f.getWidth() / 2;
            y = f.getHeight() / 2;
            c.g1.IncrementaPunteggio();
            // aggiungere punti e suono
        }
        // check lato alto
        if (this.y <= 70) {
            direzioneY = 1;
            // aggiungere suono
        }
        // check lato basso
        if (this.y >= f.getHeight() - 80) {
            direzioneY = -1;
            // aggiungere suono
        }
        // collisione con pad sinistra
        if (this.x <= c.g1.getPosX() + c.g1.getLarghezza() && this.y >= c.g1.getPosY() && this.y <= c.g1.getPosY() + c.g1.getAltezza()) {
            direzioneX = 1;
            contatoreRimb++;
        }

        // collisione pad destra
        if (this.x >= c.g2.getPosX() - c.g2.getLarghezza() && this.y >= c.g2.getPosY() && this.y <= c.g2.getPosY() + c.g2.getAltezza()) {
            direzioneX = - 1;
            contatoreRimb++;
        }
    }

}
