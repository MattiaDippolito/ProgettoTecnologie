/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author D'Ippolito
 */
public class JFrame extends javax.swing.JFrame {

    ArrayList<Giocatore> Giocatori;
    Palla p;
    Giocatore g1, g2;
    Condivisa c;

    public JFrame() {
        initComponents();
        this.setTitle("Pong");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        
        Giocatori = new ArrayList<Giocatore>();
        g1 = new Giocatore("sinistra", this);
        Giocatori.add(g1);
        
        g2 = new Giocatore("destra", this);
        Giocatori.add(g2);
        
        c = Condivisa.getIstance(g1, g2);
        
        Controller1 controller1 = new Controller1("COM3");
        Controller2 controller2 = new Controller2("COM5");
        
        controller1.start();
        controller2.start();
        
        p = new Palla(this);
        p.start();
        
        this.addKeyListener(new keyEvent(g1));
        this.addKeyListener(new KeyEvent2(g2));
        
        Disegna disegna = new Disegna(this, 33);
        disegna.start();
        
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        if (!g1.pronto || !g2.pronto) {
            Image offscreen3 = createImage(this.getWidth(), this.getHeight());
            Graphics offgc3 = offscreen3.getGraphics();

            offgc3.setColor(Color.black);
            offgc3.fillRect(0, 0, this.getWidth(), this.getHeight());
            offgc3.setColor(Color.white);
            Font finizio1 = new Font("Arial", Font.PLAIN, 100);
            offgc3.setFont(finizio1);
            offgc3.drawString("PONG!", this.getWidth() / 2 - 130, this.getHeight() - 450);
            Font finizio2 = new Font("Arial", Font.PLAIN, 30);
            offgc3.setFont(finizio2);
            if (!g1.pronto) {
                offgc3.drawString("Premere z per G1 Pronto", this.getWidth() / 2 - 400, this.getHeight() - 300);
            } else {
                offgc3.drawString("G1 Pronto", this.getWidth() / 2 - 400, this.getHeight() - 300);
            }
            if (!g2.pronto) {
                offgc3.drawString("Premere m per G2 Pronto", this.getWidth() / 2 + 130, this.getHeight() - 300);
            } else {
                offgc3.drawString("G2 Pronto", this.getWidth() / 2 + 130, this.getHeight() - 300);
            }
            g.drawImage(offscreen3, 0, 0, null);
        } else {
            Image offscreen = createImage(this.getWidth(), this.getHeight());
            Graphics offgc = offscreen.getGraphics();
            // set del colore del fondo
            offgc.setColor(Color.black);
            offgc.fillRect(0, 0, this.getWidth(), this.getHeight());
            // set del colore del campo
            offgc.setColor(Color.white);
            // lato sopra
            offgc.fillRect(100, 70, this.getWidth() - 200, 10);
            // lato sotto
            offgc.fillRect(100, this.getHeight() - 70, this.getWidth() - 200, 10);
            // lato sinistra
            offgc.fillRect(100, 70, 10, this.getHeight() - 140);
            // lato destra
            offgc.fillRect(this.getWidth() - 110, 70, 10, this.getHeight() - 140);

            // centro tratteggiato
            for (int i = 0; 80 + 20 * i <= this.getHeight() - 140; i++) {
                if (i % 2 == 0) {
                    offgc.fillRect(this.getWidth() / 2 - 5, 110 + 20 * i, 10, 20);
                }
            }

            for (Giocatore gioc : Giocatori) {
                offgc.setColor(Color.white);
                offgc.fillRect(gioc.getPosX(), gioc.getPosY(), gioc.getLarghezza(), gioc.getAltezza());
            }

            offgc.setColor(Color.white);
            offgc.fillOval(p.x, p.y, 15, 15);

            offgc.setColor(Color.white);
            Font f1 = new Font("Arial", Font.PLAIN, 30);
            offgc.setFont(f1);
            offgc.drawString(g1.PunteggioToString(), this.getWidth() / 2 - 75, this.getHeight() - 600);
            offgc.drawString(g2.PunteggioToString(), this.getWidth() / 2 + 75, this.getHeight() - 600);

            g.drawImage(offscreen, 0, 0, null);

            if (c.Finito()) {
                Image offscreen2 = createImage(this.getWidth(), this.getHeight());
                Graphics offgc2 = offscreen2.getGraphics();

                offgc2.setColor(Color.black);
                offgc2.fillRect(0, 0, this.getWidth(), this.getHeight());

                offgc2.setColor(Color.white);

                Font f2 = new Font("Arial", Font.PLAIN, 50);
                offgc2.setFont(f2);
                offgc2.drawString("GAME OVER", this.getWidth() / 2 - 150, this.getHeight() / 2);

                Font f3 = new Font("Arial", Font.PLAIN, 35);
                offgc2.setFont(f3);
                if (g1.getPunteggio() > g2.getPunteggio()) {
                    offgc2.drawString("Player1 ha vinto", this.getWidth() / 2 - 115, this.getHeight() / 2 + 50);
                } else {
                    offgc2.drawString("Player2 ha vinto", this.getWidth() / 2 - 115, this.getHeight() / 2 + 50);
                }

                g.drawImage(offscreen2, 0, 0, null);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
