package effects;

import player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Dust {
    public int posInicio = 100; // Posição que será retornado depois de cada ciclo
    public int x = 100; // Posição Horizontal do Dust
    private final int Y = 520; // Posição vertical do Dust
    private final int LARGURA = 16; // Largura px do Dust
    private int altura = 16; // altura px do Dust
    private final int upscaling = 2; // Vezes de aumento do Dust na tela
    private double velocidadeX = -3; // Velocidade em que o efeito será empurrado pra trás

    // Variáveis para carregamento e recorte do sprite sheet
    private BufferedImage sheet;
    private Image frame;

    private int frameAtual = 0; // Frame atual do sprite
    private long ultimoFrame = 0; // Tempo do ultimo frame, usado para manter a sincronia e velocidade da animação
    private final int intervaloFrame = 80; // Tempo em que os sprite são trocados

    private Player player; // Import da instância do player

    // Método construtor
    public Dust(Player player) {
        this.player = player; // Importa a instancia do player
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/resource/sprite/dust.png"));
            frame = sheet.getSubimage((frameAtual * LARGURA), 0, LARGURA, altura); // Recorta a sprite sheet
            System.out.println("Dust carregado :D");
        } catch (IOException | NullPointerException e) {
            System.err.println("Dust não carregado :( " + e.getMessage());
        }
    }

    // Desenha na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g) {
        x += velocidadeX; // Aumenta constantemente a posição X do sprite, dando efeito de indo para trás

        // Renderiza apenas se player estiver com a animação "run"
        if(Objects.equals(player.getAnimacao(), "run")) {
            g.drawImage(frame, x, Y, (LARGURA * upscaling), (altura * upscaling), null);
        }
    }

    // Define o novo frame da animação
    public void setFrameAtual(int frameAtual){
        long agora = System.currentTimeMillis();

        if (frameAtual == 0) {
            // Caso ele reinicie o loop, o frame é definido para o primeiro
            this.frameAtual = 0;
            frame = sheet.getSubimage(0, 0, LARGURA, altura);
            ultimoFrame = agora;
        }

        // Troca o frame com base no tempo definido
        if (agora - ultimoFrame >= intervaloFrame) {
            this.frameAtual = frameAtual;
            frame = sheet.getSubimage((frameAtual * LARGURA), 0, LARGURA, altura);
            ultimoFrame = agora;
        }

    }

    // Método get
    public int getFrameAtual(){
        return frameAtual;
    }
}
