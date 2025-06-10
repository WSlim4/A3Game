
package Player;
import Main.GamePanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends JPanel {

    // Sprite do jogador
    private int largura = 32; // Altura px do personagem
    private int altura = 16; // Largura px do personagem
    private final int upscaling = 3; // Vezes de aumento do personagem na tela
    private int posX = 100; // Posição horizontal inicial
    private int posY = 500; // Posição vertical inicial
    private boolean noChao = true; // Detecta se o personagem está no chão atualmente
    private GamePanel gamePanel; // Referência ao GamePanel

    private final int intervaloFrame = 120; // Intervalo para diminuir o tempo de troca do frames

    public double gravidade = -0.1; //Aceleração vertical para integrar com física
    public double velocidadeY = 0; // Velocidade atual para movimento e mudanca de sprite
    public int forcaPulo = 10; // altura do pulo

    private int frameAtual = 2; // Define o índice do frame da planilha;
    private long ultimoFrame = 0;

    // Animação atual do sprite
    private String animacao = "idle";

    // Variáveis para planilha de sprite e seleção de frame
    public BufferedImage sheet;
    public Image frame;

    public Player() {
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/resource/sprite/p1_" + animacao + ".png"));
            frame = sheet.getSubimage((frameAtual * largura), 0, 32, 16);
            System.out.println("Planilha de jogador carregada :D");
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro na planilha :( " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Desenha na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g) {
        g.drawImage(frame, posX, posY, largura * upscaling, altura * upscaling, null);
    }

    public void setFrameAtual(int frameAtual){
        long agora = System.currentTimeMillis();

        if (agora - ultimoFrame >= intervaloFrame) {
            this.frameAtual = frameAtual;
            this.frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura);
            repaint();
            ultimoFrame = agora;
        }
    }

    public int getFrameAtual(){
        return frameAtual;
    }

    public void setAnimacao(String animacao){
        this.animacao = animacao;
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/resource/sprite/p1_" + animacao + ".png"));
            if (animacao.equals("death") && gamePanel != null) {
                gamePanel.setGameState(GamePanel.GameState.GAME_OVER);
            }
        } catch (IOException | NullPointerException e){
            System.out.println("Erro ao trocar animação" + e);
        }
        frame = sheet.getSubimage((frameAtual * largura), 0, 32, 16);
    }

    public String getAnimacao(){
        return animacao;
    }

    public void reset() {
        // Reseta posições
        posX = 100;
        posY = 500;

        // Reseta física
        velocidadeY = 0;
        noChao = true;

        // Reseta animação
        frameAtual = 2;
        animacao = "idle";
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/resource/sprite/p1_" + animacao + ".png"));
            frame = sheet.getSubimage((frameAtual * largura), 0, 32, 16);
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao resetar jogador: " + e.getMessage());
        }
        repaint();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public void setPosY(int y) { this.posY = y; }
    public int getAltura() { return altura * upscaling; }
    public int getLargura() { return largura * upscaling; }
}