import Background.Background;
import Player.Player;
import Effects.Dust;
import Ponto.Pontos;

import javax.swing.*;
import java.awt.*;

    // JPanel global
public class GamePanel extends JPanel {
    private Player player;
    private Dust dust;
    private Pontos pontos;


    public GamePanel(Player player, Dust dust, Pontos pontos) {
        this.player = player;
        this.dust = dust;
        this.pontos = pontos;
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhos
        // background tem que ser o primeiro para não sobrepor o player e dust
        pontos.Renderizar(g); // incorpora pontuação e background
        dust.Renderizar(g);
        player.Renderizar(g);
    }
}
