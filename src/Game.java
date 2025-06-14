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
import java.util.Objects;

public class Game {
    private final Update updateState;
    private final Render renderer;
    private final ProcessInput userInput;

    private final Player player;
    private final Dust dust;
    private final GamePanel gamePanel;
    private final Obstaculo obstaculo;
    private final Pontos pontos;
    private JFrame janela;
    private Music music = new Music();

    private Clip clip;

    public boolean started = false;

    {
        this.obstaculo = new Obstaculo();
        this.pontos = new Pontos(obstaculo);
        this.player = new Player(pontos);
        this.dust = new Dust(player);
        this.updateState = new Update(player, obstaculo, pontos, () -> {
            janela.dispose();
            SwingUtilities.invokeLater(() -> new GameOver(pontos).setVisible(true));
        }, music);
        this.userInput = new ProcessInput(player);

        this.pontos.setObstaculos(updateState.getObstaculos());


        // Cria painel de jogo e passa os objetos
        this.gamePanel = new GamePanel(player, dust, pontos, obstaculo, updateState);
        gamePanel.addKeyListener(this.userInput);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();



        this.renderer = new Render();

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
        janela.setContentPane(gamePanel);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setVisible(true);
        gamePanel.requestFocusInWindow();
    }

    public void run() {
        if (started){
            music.play("src/resource/audio/music/tilha1.wav");
        }
        while (true) {
            this.userInput.read();
            this.updateState.update();

            if (started) {
                pontos.Tempo();
            }

            this.renderer.render(this.player, this.dust);
            gamePanel.repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }
}
