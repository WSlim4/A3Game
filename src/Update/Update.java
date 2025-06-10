package Update;

import Player.Player;
import Obstaculo.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Update {
    private Player player;
    private Obstaculo obstaculo;

    private Random random = new Random(); // Variável para sorteio de Obstáculos
    private List<Obstaculo> obstaculos = new ArrayList<>();

    private double chanceObstaculo = 5;
    private double tempoAtual = 0;

    private long ultimoFrame = 0;


    private boolean colidiu = false;

    public void update() {
        if (!colidiu){
            for (Obstaculo o : obstaculos) {
                if (player.getHitbox().intersects(o.getHitbox()) || player.getHitbox().intersects(obstaculo.getHitbox())) {
                    System.out.println("Colidiu!");
                    player.gameOver(true);
                    colidiu = true;
                }
            }
        }

        long agora = System.currentTimeMillis();
        if (agora - ultimoFrame >= 100){
            if (tempoAtual >= chanceObstaculo){
                if(chanceObstaculo >= 2){
                    chanceObstaculo -= 0.4;
                }
                tempoAtual = 0;
                sortearObstaculo();
            }
            tempoAtual += 0.1;
            //System.out.println(tempoAtual);
            ultimoFrame = agora;
        }

    }


    public Update(Player player, Obstaculo obstaculo){
        this.player = player;
        this.obstaculo = obstaculo;
    }

    public void sortearObstaculo() {
        int tipo = random.nextInt(0,5);


        Obstaculo o = null;

        switch (tipo) {
            case 0:
                o = new MoitaGrande();
                System.out.println("Obstáculo lançado: Moita Grande");
                break;
            case 1:
                o = new Moita();
                System.out.println("Obstáculo lançado: Moita");
                break;
            case 2:
                o = new Pedra();
                System.out.println("Obstáculo lançado: Pedra");
                break;
            case 3:
                o = new PedraGrande();
                System.out.println("Obstáculo lançado: Pedra Grande");
                break;
            case 4:
                int altura = random.nextInt(0,4);
                o = new Ave(altura);
                System.out.println("Obstáculo lançado: Pterossauro na altura " + altura);
                break;
        }

        obstaculos.add(o);
        System.out.println(obstaculos);
    }

    public void Renderizar(Graphics g) {
        for (Obstaculo o : obstaculos) {
            o.Renderizar(g);
        }

        // Remover oque já saiu da tela
        obstaculos.removeIf(o -> o.getX() + o.getWidth() < 0);
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }
}