package Ponto;
import Fonte.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Pontos {

    private long ms = 0;
    private long pontos = 0;
    Font fonte = FontLoader.loadFont("src/resource/font/font.otf", 30f);
    // teste é para ser substituido por uma boolean que indica Game Over ou não
    private boolean teste = true;
    private Timer tempo;

    public void Tempo() {
        tempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nesta posição é para ser "se game over for falso"
                if (teste) {
                    ms++;
                    // valor temporário de pontos, após a finalização do jogo podemos mudar isso
                    pontos = ms/100;
                }
            }
        });
        tempo.start();
    }


    public void Renderizar(Graphics g){
        g.setColor(Color.white);
        g.setFont(fonte);
        g.drawString(" " + pontos, 1100, 50);
    }

}
