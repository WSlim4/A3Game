
package Main;

import Effects.Dust;
import Ponto.Pontos;
import Update.Update;
import Render.Render;
import Player.Player;

import javax.swing.*;

public class Game {
    private Update UpdateState;
    private Render Renderer;
    private Player player;
    private Dust dust;
    private GamePanel gamePanel;
    private Pontos pontos;

    {
        this.player = new Player();
        this.dust = new Dust(player);
        this.pontos = new Pontos();

        // Cria painel de jogo e passa os objetos
        this.gamePanel = new GamePanel(player, dust, pontos);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        // Criação da Janela do Jogo
        JFrame janela = new JFrame();
        janela.setTitle("Dino Run");
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setContentPane(gamePanel);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setVisible(true);

        this.UpdateState = new Update();
        this.Renderer = new Render();

        // Inicia o timer apenas uma vez
        pontos.Tempo();

        System.out.println("Controle de Sprite: \n- 'D' - Correr\n- 'W' - Morte\n Nada - Ocioso");
    }

    public void run() {
        while (true) {
            this.UpdateState.update();
            this.Renderer.render(this.player, this.dust);
            gamePanel.repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }
}