/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author D'Ippolito
 */
public class KeyEvent2 implements KeyListener {

    Giocatore giocatore;

    public KeyEvent2(Giocatore g) {
        giocatore = g;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'i') {
            giocatore.decrementa();
        } else if (e.getKeyChar() == 'k') {
            giocatore.incrementa();
        } else if (e.getKeyChar() == 'm') {
            giocatore.diventaPronto();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
