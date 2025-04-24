package Effects;

import Player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Dust {
    public int posInicio = 100; // Posição que será retornado depois de cada ciclo
    public int x = 100; // Posição Horizontal do Dust
    private int y = 520; // Posição vertical do Dust
    private int largura = 16; // Largura px do Dust
    private int altura = 16; // altura px do Dust
    private final int upscaling = 2; // Vezes de aumento do Dust na tela
    private double velocidadeX = -3; // Velocidade em que o efeito será empurrado pra trás

    // Variáveis para carregamento e recorte do sprite sheet
    private BufferedImage sheet;
    private Image frame;

    private int frameAtual = 0; // Frame atual do sprite
    private long ultimoFrame = 0; // Tempo do ultimo frame, usado para manter a sincronia e velocidade da animação
    private final int intervaloFrame = 80; // Tempo em que os sprite são trocados

    private Player player;

    public Dust(Player player) {
        this.player = player;
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/resource/sprite/dust.png"));
            frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura); // Recorta a sprite sheet
            System.out.println("Dust carregado :D");
        } catch (IOException | NullPointerException e) {
            System.err.println("Dust não carregado :( " + e.getMessage());
        }
    }

    // Desenha na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g) {
        if(Objects.equals(player.getAnimacao(), "run")) {
            g.drawImage(frame, x, y, (largura * upscaling), (altura * upscaling), null);
        }
        x += velocidadeX;
    }

    public int getFrameAtual(){
        return frameAtual;
    };

    public void setFrameAtual(int frameAtual){
        long agora = System.currentTimeMillis();

        if (agora - ultimoFrame >= intervaloFrame) {
            this.frameAtual = frameAtual;
            frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura);
            ultimoFrame = agora;
        }

    }
}
