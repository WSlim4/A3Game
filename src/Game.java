import Effects.Dust;
import Obstaculo.Obstaculo;
import Ponto.Pontos;
import Update.Update;
import Render.Render;
import ProcessInput.ProcessInput;
import Player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Game {
    private final Update UpdateState;
    private final Render Renderer;
    private final ProcessInput UserInput;

    private final Player player;
    private final Dust dust;
    private final GamePanel gamePanel;
    private final Obstaculo obstaculo;
    private final Pontos pontos;
    private JFrame janela;

    public boolean started = false;


    {
        this.obstaculo = new Obstaculo();
        this.pontos = new Pontos(obstaculo);
        this.player = new Player(pontos);
        this.dust = new Dust(player);
        this.UpdateState = new Update(player, obstaculo, pontos);
        this.UserInput = new ProcessInput(player);

        this.pontos.setObstaculos(UpdateState.getObstaculos());


        // Cria painel de jogo e passa os objetos
        this.gamePanel = new GamePanel(player, dust, pontos, obstaculo, UpdateState);
        gamePanel.addKeyListener(this.UserInput);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();



        this.Renderer = new Render();

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
        while (true) {
            this.UserInput.read();
            this.UpdateState.update();

            if (started) {
                pontos.Tempo();
            }

            this.Renderer.render(this.player, this.dust);
            gamePanel.repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }
}
