
package Main;

import Player.Player;
import Effects.Dust;
import Ponto.Pontos;
import UI.UI;
import Input.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Player player;
    private Dust dust;
    private Pontos pontos;
    private UI ui;

    public enum GameState { PLAYING, GAME_OVER, PAUSED }
    private GameState gameState = GameState.PLAYING;

    private final int screenWidth = 1280;
    private final int screenHeight = 720;
    private final int tileSize = 48;

    public GamePanel(Player player, Dust dust, Pontos pontos) {
        this.player = player;
        this.dust = dust;
        this.pontos = pontos;
        this.ui = new UI(this);

        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);

        KeyHandler keyHandler = new KeyHandler(this);
        addKeyListener(keyHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, screenWidth, screenHeight);

        // Renderização dos componentes do jogo
        pontos.Renderizar(g);
        dust.Renderizar(g);
        player.Renderizar(g);


        if (gameState != GameState.PLAYING) {
            ui.draw(g2);
        }
    }

    // Getters e Setters
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState state) {
        this.gameState = state;
        if (state == GameState.GAME_OVER) {
            pontos.stopTimer();
        } else if (state == GameState.PLAYING) {
            pontos.startTimer();
        }
        repaint();
    }

    public void resetGame() {
        this.gameState = GameState.PLAYING;
        pontos.resetPontos();
        player.reset();
        dust = new Dust(player);
        repaint();
        requestFocusInWindow();
    }

    public int getScreenWidth() { return screenWidth; }
    public int getScreenHeight() { return screenHeight; }
    public int getTileSize() { return tileSize; }
    public Player getPlayer() { return player; }
    public UI getGameUI() { return ui; }

    // Setters para injeção de dependências
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setDust(Dust dust) {
        this.dust = dust;
    }

    public void setPontos(Pontos pontos) {
        this.pontos = pontos;
    }
}