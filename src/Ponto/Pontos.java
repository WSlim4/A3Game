
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
    private boolean teste = true;
    private Timer tempo;
    private PassagemDeTempo passagemDeTempo = new PassagemDeTempo();
    private int movimento = 0;
    double velocidade = 0;
    private int cicloDia;
    private int ciclo = 0;
    private int lua;
    private int luaVelocidade = 0;

    public void Tempo() {
        if (tempo != null) {
            tempo.stop(); // Evita múltiplos timers
        }

        tempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    if (luaVelocidade == 240){
                        lua -= 1;
                        luaVelocidade = 0;
                    } else if (lua == -2560) {
                        lua = 0;
                        luaVelocidade = 0;
                    }

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

    // Novos métodos para controle do timer
    public void stopTimer() {
        teste = false;
        if (tempo != null) {
            tempo.stop();
        }
    }

    public void startTimer() {
        teste = true;
        if (tempo != null) {
            tempo.start();
        } else {
            Tempo();
        }
    }

    public void resetPontos() {
        ms = 0;
        pontos = 0;
        movimento = 0;
        velocidade = 0;
        ciclo = 0;
        cicloDia = 0;
        lua = 0;
        luaVelocidade = 0;
        teste = true;
        if (tempo != null) {
            tempo.stop();
        }
        Tempo();
    }
}