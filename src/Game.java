import Effects.Dust;
import Update.Update;
import Render.Render;
import ProcessInput.ProcessInput;
import Player.Player;

import javax.swing.*;

public class Game {
    private Update UpdateState;
    private Render Renderer;
    private ProcessInput UserInput;

    private Player player;
    private Dust dust;
    private GamePanel gamePanel;

    {
        this.player = new Player();
        this.dust = new Dust(player);
        this.UserInput = new ProcessInput(player);

        // Cria painel de jogo e passa os objetos
        this.gamePanel = new GamePanel(player, dust);
        gamePanel.addKeyListener(this.UserInput);
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

        System.out.println("Controle de Sprite: \n- 'D' - Correr\n- 'W' - Morte\n Nada - Ocioso");
    }

    {

    }

    public void run() {
        while (true) {
            this.UserInput.read();
            this.UpdateState.update();
            this.Renderer.render(this.player, this.dust);
            gamePanel.repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }
}
