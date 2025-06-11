import Effects.Dust;
import Obstaculo.Obstaculo;
import Ponto.Pontos;
import Update.Update;
import Render.Render;
import ProcessInput.ProcessInput;
import Player.Player;
import javax.swing.*;

public class Game {
    private final Update UPDATE_STATE;
    private final Render RENDERER;
    private final ProcessInput USER_INPUT;

    private final Player PLAYER;
    private final Dust DUST;
    private final GamePanel GAMEPANEL;
    private final Obstaculo OBSTACULO;
    private final Pontos PONTOS;


    {
        this.OBSTACULO = new Obstaculo();
        this.PONTOS = new Pontos(OBSTACULO);
        this.PLAYER = new Player(PONTOS);
        this.DUST = new Dust(PLAYER);
        this.USER_INPUT = new ProcessInput(PLAYER);
        this.UPDATE_STATE = new Update(PLAYER, OBSTACULO);

        this.PONTOS.setObstaculos(UPDATE_STATE.getObstaculos());


        // Cria painel de jogo e passa os objetos
        this.GAMEPANEL = new GamePanel(PLAYER, DUST, PONTOS, OBSTACULO, UPDATE_STATE);
        GAMEPANEL.addKeyListener(this.USER_INPUT);
        GAMEPANEL.setFocusable(true);
        GAMEPANEL.requestFocusInWindow();



        // Criação da Janela do Jogo
        JFrame janela = new JFrame();
        janela.setTitle("Dino Run");
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setContentPane(GAMEPANEL);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setVisible(true);



        this.RENDERER = new Render();

        System.out.println("Controle de Sprite: \n- 'D' - Correr\n- 'W' - Morte\n Nada - Ocioso");
    }


    public void run() {
        while (true) {
            this.USER_INPUT.read();
            this.UPDATE_STATE.update();
            PONTOS.Tempo();
            this.RENDERER.render(this.PLAYER, this.DUST);
            GAMEPANEL.repaint();
            try { Thread.sleep(16); } catch (Exception e) {
                e.fillInStackTrace();
                throw new RuntimeException();
            }
        }
    }
}
