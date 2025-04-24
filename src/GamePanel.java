import Player.Player;
import Effects.Dust;

import javax.swing.*;
import java.awt.*;

    // JPanel global
public class GamePanel extends JPanel {
    private Player player;
    private Dust dust;

    public GamePanel(Player player, Dust dust) {
        this.player = player;
        this.dust = dust;
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhos
        dust.Renderizar(g);
        player.Renderizar(g);
    }
}
