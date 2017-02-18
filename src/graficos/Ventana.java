/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author Alberto pc
 */
public class Ventana {
    private final int width, height;
    private final String title;
    private JFrame JF;
    private Canvas c;
    private BufferStrategy bs;
    private Graphics g;
    private int cont = 0;
    private long currentTime, refTime;
    public Ventana(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        iniciarVentana();
    }
    
    private void iniciarVentana(){
        // iniciar la ventana
        JF = new JFrame();
        JF.setSize(width, height);
        JF.setTitle(title);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.setVisible(true);
        JF.setResizable(false);
        JF.setLocationRelativeTo(null);
        // crear y agregar canvas
        // pack() ajusta el canvas al JFrame
        c = new Canvas();
        c.setPreferredSize(new Dimension(width, height));
        JF.add(c);
        JF.pack();
        // crear BufferStrategy con el fin de pintar 
        // bs facilita el pintado sobre el canvas
        c.createBufferStrategy(2);
        refTime = System.currentTimeMillis();
        pintar();
    }
    
    private void pintar(){
        while (true) {   
            currentTime = System.currentTimeMillis();
            if (tick()==1) {
                // asigna el bufferstrategy que creamos a nuestra variable bs
                bs = c.getBufferStrategy();
                // el objeto g es basicamente nuestro pincel
                g = bs.getDrawGraphics();
                // limpia la pantalla 
                g.clearRect(0, 0, width, width);
                /* 
                pinta un rectangulo azul y luego pinta circulos rojos sobre
                este
                */
                g.setColor(Color.BLUE);
                g.fillRect(0, 0, width, width);
                g.setColor(Color.RED);
                g.fillOval(cont*10, cont*10, 10, 10);
                cont++;
                cont %=height/10;
                // guarda el pincel y muestra todo lo pintado
                g.dispose();
                bs.show(); 
            }    
        }       
    }
    /**
     * Devuelve 1 si han pasado 100 ms, 0 si no
     * Nos permite decidir si pintar o no(se pintara cada 100ms)
     * refTime es el tiempo de referencia, y currentTime el actual
     * @return 
     */
    private int tick(){
        if (currentTime-refTime >= 100) {
            refTime = currentTime;
            return 1;
        }
        return 0;
    }
    
    
}
