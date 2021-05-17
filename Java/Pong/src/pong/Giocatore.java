/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author D'Ippolito
 */
public class Giocatore {

    private char comandoUp;
    private char comandoDown;
    private String posizione;
    private int posY, posX;
    private int punteggio;
    private JFrame f;
    private int larghezza = 15;
    private int altezza = 50;
    boolean pronto;
    
    public Giocatore(){
        this.punteggio = 1;
        this.pronto = false;
    }

    public Giocatore(String posizione, JFrame f) {
        this.posizione = posizione;
        if (posizione == "sinistra") {
            comandoUp = 'w';
            comandoDown = 's';
            posX = 120;
        } else if (posizione == "destra") {
            comandoUp = 'i';
            comandoDown = 'k';
            posX = f.getWidth() - 135;
        }
        posY = f.getHeight() / 2 - altezza;
        this.f = f;
        this.pronto = false;
    }
    // va in giú 
    public void incrementa() {
        if (posY < f.getHeight() - 100 - altezza) {
            posY += 50;
        }
    }
    // va in su
    public void decrementa() {
        if (posY > 40 + altezza) {
            posY -= 50;
        }
    }

    public int getPunteggio() {
        return punteggio;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getLarghezza() {
        return this.larghezza;
    }

    public int getAltezza() {
        return this.altezza;
    }
    
    public void IncrementaPunteggio(){
        punteggio++;
    }
    
    public String PunteggioToString(){
        return "" + punteggio;
    }
    
    public void diventaPronto(){
        pronto = true;
    }
}
