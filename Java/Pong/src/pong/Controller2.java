/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import com.fazecast.jSerialComm.SerialPort;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cazzola
 */
public class Controller2 extends Thread {

    String nome;
    SerialPort port;
    Condivisa c;

    public Controller2(String nome) {
        this.nome = nome;
        port = SerialPort.getCommPort(nome);
        port.setComPortParameters(9600, 8, 1, 0);
        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        this.c = Condivisa.getIstance();
    }

    @Override
    public void run() {
        while (!c.Finito()) {
            if (port.openPort()) {
                System.out.println("Collegato");
            } else {
                System.out.println("Non collegato");
                return;
            }
            byte[] b = new byte[1];
            port.readBytes(b, 1);
            char comando = (char) b[0];
            if (comando == 'i') {
                c.g2.decrementa();
            } else if (comando == 'k') {
                c.g2.incrementa();
            } else if (comando == 'm') {
                c.g2.diventaPronto();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller2.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (port.openPort()) {
                System.out.println("Scollegato");
            } else {
                System.out.println("Non scollegato");
                return;
            }
        }
    }
}