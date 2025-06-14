import obstaculo.Obstaculo;
import player.Player;
import effects.Dust;
import ponto.Pontos;
import update.Update;

import javax.swing.*;
import java.awt.*;

    // JPanel global
public class GamePanel extends JPanel {
    private final Player player;
    private final Dust dust;
    private final Pontos pontos;
    private final Obstaculo obstaculo;
    private final Update update;


    public GamePanel(Player player, Dust dust, Pontos pontos, Obstaculo obstaculo, Update update) {
        this.player = player;
        this.dust = dust;
        this.pontos = pontos;
        this.obstaculo = obstaculo;
        this.update = update;
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhos
        // background tem que ser o primeiro para não sobrepor o player e DUST
        pontos.Renderizar(g); // incorpora pontuação e background
        obstaculo.Renderizar(g);
        dust.Renderizar(g);
        player.Renderizar(g);
        update.Renderizar(g);
    }
}
