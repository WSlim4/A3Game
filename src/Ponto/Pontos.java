package Ponto;
import Background.*;
import Fonte.FontLoader;
import Obstaculo.Obstaculo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Pontos {

    private List<Obstaculo> listaObstaculos;
    private long ms = 0;
    private long pontos = 0;
    Font fonte = FontLoader.loadFont("src/resource/font/font.otf", 30f);
    private boolean gameOver = true;
    private final PassagemDeTempo PASSAGEM_TEMPO = new PassagemDeTempo();
    private int movimento = 0;
    double velocidade = 0;
    private int cicloDia;
    private int ciclo = 0;
    private int lua;
    private int luaVelocidade = 0;
    private final Obstaculo OBSTACULO;

    public Pontos(Obstaculo obstaculo){
        this.OBSTACULO = obstaculo;
        System.out.println("Pontos contrutor");
    }

    public void Tempo() {
        // new ActionListener() não pode ser trocado por lambda, pois outros avisos aparecem ou erros.
        // nesta posição é para ser "se game over for falso"
        // valor temporário de pontos, após a finalização do jogo podemos mudar isso
        Timer tempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nesta posição é para ser "se game over for falso"
                if (gameOver) {
                    ms++;
                    velocidade += 1;


                    if (velocidade == 8) {
                        movimento -= 1;
                        velocidade = 0;
                    } else if (movimento == -1280) {
                        movimento = 0;
                        velocidade = 0;
                    }

                    OBSTACULO.move();
                    for (Obstaculo o : listaObstaculos) {
                        o.move();
                    }

                    ciclo++;
                    cicloDia = ciclo / 700;
                    if (cicloDia == 2001) {
                        ciclo = 0;
                        cicloDia = 0;
                    }

                    luaVelocidade += 1;
                    if (luaVelocidade == 240) {
                        lua -= 1;
                        luaVelocidade = 0;
                    } else if (lua == -2560) {
                        lua = 0;
                        luaVelocidade = 0;
                    }

                    // valor temporário de pontos, após a finalização do jogo podemos mudar isso
                    pontos = ms / 700;

                }
            }
        });
        tempo.start();
    }


    public void Renderizar(Graphics g){
        PASSAGEM_TEMPO.Renderizar(g, movimento, cicloDia, lua);
        g.setColor(Color.white);
        g.setFont(fonte);
        g.drawString(" " + pontos, 1100, 50);
    }

    public double getVelocidade(){
        return velocidade;
    }

    public void setGameOver(boolean bool){
        gameOver = bool;
    }

    public void setObstaculos(List<Obstaculo> obstaculos){
        this.listaObstaculos = obstaculos;
    }

}
