package Ponto;
import Background.*;
import Fonte.FontLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Pontos {

    private long ms = 0;
    private long pontos = 0;
    Font fonte = FontLoader.loadFont("src/resource/font/font.otf", 30f);
    // teste é para ser substituido por uma boolean que indica Game Over ou não
    private boolean teste = true;
    private Timer tempo;
    private PassagemDeTempo passagemDeTempo = new PassagemDeTempo();
    private int movimento = 0;
    double velocidade = 0;
    private int cicloDia;
    private int ciclo = 0;
    private int lua = 1480;
    private int luaVelocidade = 0;



    public void Tempo() {
        tempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nesta posição é para ser "se game over for falso"
                if (teste) {
                    ms++;
                    velocidade += 1;
                    if (velocidade == 8){
                        movimento -= 1;
                        velocidade = 0;
                    } else if (movimento == -1280) {
                        movimento = 0;
                        velocidade = 0;
                    }

                    ciclo++;
                    cicloDia = ciclo/700;
                    if (cicloDia == 2001){
                        ciclo = 0;
                        cicloDia = 0;
                    }

                    luaVelocidade += 1;
                    if (luaVelocidade == 220){
                        lua -= 1;
                        luaVelocidade = 0;
                    } else if (lua == -2560) {
                        lua = 0;
                        luaVelocidade = 0;
                    }

                    // valor temporário de pontos, após a finalização do jogo podemos mudar isso
                    pontos = ms/700;

                }
            }
        });
        tempo.start();
    }


    public void Renderizar(Graphics g){
        passagemDeTempo.Renderizar(g, movimento, cicloDia, lua);
        g.setColor(Color.white);
        g.setFont(fonte);
        g.drawString(" " + pontos, 1100, 50);
    }

}
