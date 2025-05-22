import Background.Background;
import Player.Player;
import Effects.Dust;

import javax.swing.*;
import java.awt.*;

    // JPanel global
public class GamePanel extends JPanel {
    private Player player;
    private Dust dust;
    private Background background;

    public GamePanel(Player player, Dust dust, Background background) {
        this.player = player;
        this.dust = dust;
        this.background = background;
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhos
        background.Renderizar(g); // background tem que ser o primeiro para não sobrepor o player e dust
        dust.Renderizar(g);
        player.Renderizar(g);
    }
}
