/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

/**
 *
 * @author Daniele
 */
public class Condivisa {

    Giocatore g1, g2;

    private static Condivisa istance = null;

    private Condivisa() {
        g1 = new Giocatore();
        g2 = new Giocatore();
    }

    private Condivisa(Giocatore g1, Giocatore g2) {
        this.g1 = g1;
        this.g2 = g2;
    }

    public static Condivisa getIstance(Giocatore g1, Giocatore g2) {
        if (istance == null) {
            istance = new Condivisa(g1, g2);
        }
        return istance;
    }

    public static Condivisa getIstance() {
        if (istance == null) {
            istance = new Condivisa();
        }
        return istance;
    }

    public boolean Finito() {
        if (g1.getPunteggio() >= 5 || g2.getPunteggio() >= 5) {
            return true;
        }
        return false;
    }
}
