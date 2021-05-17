/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author D'Ippolito
 */
public class keyEvent implements KeyListener {

    Giocatore giocatore1;

    public keyEvent(Giocatore g1) {
        giocatore1 = g1;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            giocatore1.decrementa();
        } else if (e.getKeyChar() == 's') {
            giocatore1.incrementa();
        } else if (e.getKeyChar() == 'z') {
            giocatore1.diventaPronto();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
