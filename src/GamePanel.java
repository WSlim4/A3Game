import Obstaculo.Obstaculo;
import Player.Player;
import Effects.Dust;
import Ponto.Pontos;
import Update.Update;

import javax.swing.*;
import java.awt.*;

    // JPanel global
public class GamePanel extends JPanel {
    private final Player PLAYER;
    private final Dust DUST;
    private final Pontos PONTOS;
    private final Obstaculo OBSTACULO;
    private final Update UPDATE;


    public GamePanel(Player player, Dust dust, Pontos pontos, Obstaculo obstaculo, Update update) {
        this.PLAYER = player;
        this.DUST = dust;
        this.PONTOS = pontos;
        this.OBSTACULO = obstaculo;
        this.UPDATE = update;
        setPreferredSize(new Dimension(1280, 720));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhos
        // background tem que ser o primeiro para não sobrepor o player e dust
        PONTOS.Renderizar(g); // incorpora pontuação e background
        OBSTACULO.Renderizar(g);
        DUST.Renderizar(g);
        PLAYER.Renderizar(g);
        UPDATE.Renderizar(g);
    }
}
