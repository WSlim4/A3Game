package Player;
import Ponto.Pontos;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;

public class Player extends JPanel {

    // Sprite do jogador
    private int largura = 32; // Altura px do personagem
    private int altura = 16; // Largura px do personagem
    private final int upscaling = 3; // Vezes de aumento do personagem na tela
    private int posX = 100; // Posição horizontal inicial
    private int posY = 500; // Posição vertical inicial
    private Rectangle hitbox = new Rectangle(posX+20, posY, largura*upscaling/2, altura*upscaling); // Hitbox do jogador
    private boolean noChao = true; // Detecta se o personagem está no chão atualmente
    private boolean isGameOver = false;

    private int intervaloFrame = 120; // Intervalo para diminuir o tempo de troca do frames

    public double gravidade = -0.1; //Aceleração vertical para integrar com física
    public double velocidadeY = 0; // Velocidade atual para movimento e mudanca de sprite
    public int forcaPulo = 10; // altura do pulo

    private int frameAtual = 2; // Define o índice do frame da planilha;
    private long ultimoFrame = 0;

    // Animação atual do sprite
    private String animacao = "idle";

    // Variáveis para planilha de sprite e seleção de frame
    private BufferedImage sheet;
    private Image frame;

    // Instancias importadas
    private Pontos pontos;

    // Construtor
    public Player(Pontos pontos) {
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/resource/sprite/p1_" + animacao + ".png"));
            frame = sheet.getSubimage((frameAtual * largura), 0, 32, 16);
            System.out.println("Planilha de jogador carregada :D");
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro na planilha :( " + e.getMessage());
            e.printStackTrace();
        }

        // Importar instancias
        this.pontos = pontos;
    }
    // Desenha na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g) {
        g.drawImage(frame, posX, posY, largura * upscaling, altura * upscaling, null);

        // Descomente essa parte para ver a hitbox
        // g.setColor(Color.RED);
        // g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public void setFrameAtual(int frameAtual){
        long agora = System.currentTimeMillis();

        if (agora - ultimoFrame >= intervaloFrame) {
            try {
                this.frameAtual = frameAtual;
                this.frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura);
            } catch (RasterFormatException e) {
                System.err.println("Frame passou dos limites do spriteSheet, Resetando para o frame 0.");
                this.frameAtual = 0;
                this.frame = sheet.getSubimage(0, 0, largura, altura);
            }
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
            try {
                frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura);
            } catch (RasterFormatException e) {
                System.err.println("O Frame atual acima fora do limite da nova animação, colocando para o primeiro frame.");
                frameAtual = 0;
                frame = sheet.getSubimage(0, 0, largura, altura);
            }
        } catch (IOException | NullPointerException e){
            System.out.println("Erro ao trocar animação: " + e);
        }
    }

    public String getAnimacao(){
        return animacao;
    }

    public int getIntervaloFrame(){ return intervaloFrame; }

    public void setIntervaloFrame(int intervaloFrame){
        this.intervaloFrame = intervaloFrame;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void gameOver(boolean bool){
        isGameOver = bool;
        setAnimacao("death");
        System.out.println("GameOver");
        // pontos.setGameOver(false);
    }

}

