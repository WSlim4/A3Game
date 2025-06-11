import Effects.Dust;
import Obstaculo.Obstaculo;
import Ponto.Pontos;
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
    private Obstaculo obstaculo;
    private Pontos pontos;
    private JFrame janela;

    public boolean started = false;

    public Game() {
        this.obstaculo = new Obstaculo();
        this.pontos = new Pontos(obstaculo);
        this.player = new Player(pontos);
        this.dust = new Dust(player);
        this.UserInput = new ProcessInput(player);
        this.UpdateState = new Update(player, obstaculo);

        this.pontos.setObstaculos(UpdateState.getObstaculos());

        this.gamePanel = new GamePanel(player, dust, pontos, obstaculo, UpdateState);
        gamePanel.addKeyListener(this.UserInput);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        this.Renderer = new Render();

        System.out.println("Controle de Sprite: \n- 'D' - Correr\n- 'W' - Morte\n Nada - Ocioso");
    }

    public void start() {
        janela = new JFrame();
        janela.setTitle("Dino Run");
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