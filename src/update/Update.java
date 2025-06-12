package update;

import effects.Music;
import player.Player;
import obstaculo.*;
import ponto.Pontos;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;
import javax.sound.sampled.*;


public class Update {
    private final Player player;
    private final Obstaculo obstaculo;
    private final Pontos pontos;
    private final Runnable onGameOver;
    private Music music;

    private final Random random = new Random(); // Variável para sorteio de Obstáculos
    private final List<Obstaculo> obstaculos = new ArrayList<>();

    private double chanceObstaculo = 5;
    private double tempoAtual = 0;

    private long ultimoFrame = 0;


    private boolean colidiu = false;

    private double velocidadeY;
    private int posY = 500;
    private final Timer DELAY = new Timer();
    private Clip clip;

    private AudioInputStream soundHit;
    private AudioInputStream soundQueda;

    public void update() {
        if (!colidiu){
            for (Obstaculo o : obstaculos) {
                if (player.getHITBOX().intersects(o.getHitbox()) || player.getHITBOX().intersects(obstaculo.getHitbox())) {
                    // Instruções executadas exatamente no momento da morte
                    pontos.createFile();
                    music.stop();
                    colidiu = true;
                    System.out.println("Colidiu!!");
                    player.gameOver(true);
                    pontos.setGameOver(false);
                    try {
                        clip.open(soundHit);
                        clip.start();
                    } catch (Exception e){
                        System.out.println("Erro no momento de colisão do GameOver: " + e);
                    }


                    DELAY.schedule(new TimerTask() {
                        // Instruções executadas com delay de dois segundos após a morte
                        @Override
                        public void run() {
                            try {
                                player.setAnimacao("death");
                                clip.stop(); // Para o som anterior
                                clip.close(); // Fecha o som anterior
                                clip.open(soundQueda); // Abre o novo som de queda
                                clip.start(); // Inicia o som
                            } catch (Exception e){
                                System.out.println("Erro após a colisão do GameOver: " + e);
                            }
                        }
                    }, 2000);
                    DELAY.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Instruções executadas com delay de 3 segundos após a morte

                            System.out.println("GameOver finalizado");
                            if (onGameOver != null) {
                                onGameOver.run();
                            }
                        }
                    }, 3500);
                }
            }
        }

        long agora = System.currentTimeMillis();
        if (agora - ultimoFrame >= 100 && !colidiu){
            if (tempoAtual >= chanceObstaculo){
                if(chanceObstaculo >= 1.5){
                    chanceObstaculo -= 0.3;
                }
                tempoAtual = 0;
                sortearObstaculo();
            }
            tempoAtual += 0.1;
            ultimoFrame = agora;
        }

        if (!player.getNoChao()){
            velocidadeY += player.getGRAVIDADE();
            posY += velocidadeY;
            player.setPosY(posY);


            int ALTURA_CHAO = 500;
            if (player.getPosY() >= ALTURA_CHAO && !player.getIsGameOver()){
                player.setPosY(ALTURA_CHAO);
                player.setNoChao(true);
                velocidadeY = player.getFORCA_PULO();
                player.setAnimacao("run");
            }
        }
    }

    public Update(Player player, Obstaculo obstaculo, Pontos pontos, Runnable onGameOver, Music music) {
        this.player = player;
        this.obstaculo = obstaculo;
        this.pontos = pontos;
        this.onGameOver = onGameOver;
        this.music = music;
        // ... rest of constructor ...
        try {
            soundHit = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/resource/audio/effects/hit.wav")));
            soundQueda = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/resource/audio/effects/Queda.wav")));
            clip = AudioSystem.getClip();
        } catch (Exception e){
            System.out.println("Erro ao executar audio hit: " + e);
        }
    }

    public void sortearObstaculo() {
        int tipo = random.nextInt(0,4);


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
        }

        obstaculos.add(o);
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

    public void setVelocidadeY(int velocidadeY){
        this.velocidadeY = velocidadeY;
    }
}