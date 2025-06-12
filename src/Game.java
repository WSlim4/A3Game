import effects.Dust;
import effects.Music;
import obstaculo.Obstaculo;
import ponto.Pontos;
import update.Update;
import render.Render;
import process_input.ProcessInput;
import player.Player;
import javax.sound.sampled.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class Game {
    private final Update UPDATE_STATE;
    private final Render RENDERER;
    private final ProcessInput USER_INPUT;

    private final Player PLAYER;
    private final Dust DUST;
    private final GamePanel GAME_PANEL;
    private final Obstaculo OBSTACULO;
    private final Pontos PONTOS;
    private JFrame janela;
    private Music music = new Music();

    private Clip clip;

    public boolean started = false;

    {
        this.OBSTACULO = new Obstaculo();
        this.PONTOS = new Pontos(OBSTACULO);
        this.PLAYER = new Player(PONTOS);
        this.DUST = new Dust(PLAYER);
        this.UPDATE_STATE = new Update(PLAYER, OBSTACULO, PONTOS, () -> {
            janela.dispose();
            SwingUtilities.invokeLater(() -> new GameOver(PONTOS).setVisible(true));
        }, music);
        this.USER_INPUT = new ProcessInput(PLAYER);

        this.PONTOS.setObstaculos(UPDATE_STATE.getObstaculos());


        // Cria painel de jogo e passa os objetos
        this.GAME_PANEL = new GamePanel(PLAYER, DUST, PONTOS, OBSTACULO, UPDATE_STATE);
        GAME_PANEL.addKeyListener(this.USER_INPUT);
        GAME_PANEL.setFocusable(true);
        GAME_PANEL.requestFocusInWindow();



        this.RENDERER = new Render();

        System.out.println("Controle de Sprite: \n- 'D' - Correr\n- 'W' - Morte\n Nada - Ocioso");
    }



// Criação da Janela do Jogo
    public void start() {
        janela = new JFrame();
        janela.setTitle("Dino Run");



        try {
            BufferedImage icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resource/sprite/p1_hit.png")));
            janela.setIconImage(icon);
        } catch (Exception e){
            System.out.println("Ícone não carregado.");
        }


        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setContentPane(GAME_PANEL);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setVisible(true);
        GAME_PANEL.requestFocusInWindow();

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(""));
        } catch (Exception e){

        }

    }

    public void run() {
        if (started){
            music.play("src/resource/audio/music/tilha1.wav");
        }
        while (true) {
            this.USER_INPUT.read();
            this.UPDATE_STATE.update();

            if (started) {
                PONTOS.Tempo();
            }

            this.RENDERER.render(this.PLAYER, this.DUST);
            GAME_PANEL.repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }
}
