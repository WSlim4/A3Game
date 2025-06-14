package player;
import ponto.Pontos;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.util.Objects;

public class Player extends JPanel {

    // Sprite do jogador
    private final int largura = 32; // Altura px do personagem
    private final int altura = 16; // Largura px do personagem
    private final int upscaling = 3; // Vezes de aumento do personagem na tela
    private final int posX = 100; // Posição horizontal inicial
    private int posY = 500; // Posição vertical inicial
    private final Rectangle hitbox = new Rectangle(posX +30, posY+(altura *upscaling/2), largura *upscaling/3, altura *upscaling-15); // Hitbox do jogador
    private final boolean viewHitbox = false; // Deixe em true para ver a HITBOX
    private boolean noChao = true; // Detecta se o personagem está no chão atualmente
    private boolean isGameOver = false;

    private final double gravidade = 0.5;
    private final int forcaPulo = -15;

    private double intervaloFrame = 500; // Intervalo para diminuir o tempo de troca do frames
    private double aceleracao = 100;

    private int frameAtual = 1; // Define o índice do frame da planilha;
    private long ultimoFrame = 0; // armazena o tempo em que o ultimo frame foi executado em milissegundos

    // Animação atual do sprite
    private String animacao = "run";

    // Variáveis para planilha de sprite e seleção de frame
    private BufferedImage sheet;
    private Image frame;

    // Construtor
    public Player(Pontos pontos) {
        try {
            sheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resource/sprite/p1_" + animacao + ".png")));
            frame = sheet.getSubimage((frameAtual * largura), 0, 32, 16);
            System.out.println("Planilha de jogador carregada :D");
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro na planilha :( " + e.getMessage());
            e.fillInStackTrace();
            throw new RuntimeException();
        }
    }

    // Troca os frames da planilhad e sprites por um tempo definido pela variável
    public void setFrameAtual(int frameAtual){
        long agora = System.currentTimeMillis();

        if (agora - ultimoFrame >= intervaloFrame) {
            try {
                this.frameAtual = frameAtual; // Define o novo frame
                this.frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura); // Recorta o frame com o novo frame
            } catch (RasterFormatException e) { // Caso ocorra erro, defini para o frame 0
                System.err.println("Frame passou dos limites do spriteSheet, Resetando para o frame 0.");
                this.frameAtual = 0;
                this.frame = sheet.getSubimage(0, 0, largura, altura);
            }
            repaint();
            acelerarPlayer();
            ultimoFrame = agora;
        }
    }

    public void setAnimacao(String animacao){
        this.animacao = animacao; // Atualiza com a nova atualização
        try {
            sheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resource/sprite/p1_" + animacao + ".png"))); // Carrega a nova animação
            try { // Tenta fazer o recorte da planilha de sprites, se der erro, defini o frame em 0 novamente
                frame = sheet.getSubimage((frameAtual * largura), 0, largura, altura);
            } catch (RasterFormatException e) {
                System.err.println("O Frame atual acima fora do limite da nova animação, colocando para o primeiro frame.");
                frameAtual = 0; // Define o frame atual para 0
                frame = sheet.getSubimage(0, 0, largura, altura); // Recorta o primeiro frame do sprite
            }
        } catch (IOException | NullPointerException e){
            System.out.println("Erro ao trocar animação: " + e);
        }
    }

    public int getFrameAtual(){
        return frameAtual;
    }

    public String getAnimacao(){
        return animacao;
    }

    public Rectangle getHITBOX() {
        return hitbox;
    }
    // Executa os metodos de Game Over
    public void gameOver(boolean bool){
        isGameOver = bool;
        setAnimacao("hit");
        System.out.println("GameOver");
    }

    public boolean getIsGameOver(){ return isGameOver;}

    // Faz com que a velocidade da animação seja gradual
    public void acelerarPlayer(){
        long agora = System.currentTimeMillis();
        if (intervaloFrame > 120){
            if (agora - ultimoFrame >= 100){
                intervaloFrame -= aceleracao;
                aceleracao -= 2;
            }
        }
    }

    // Desenha na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g) {
        g.drawImage(frame, posX, posY, largura * upscaling, altura * upscaling, null);

        // Descomente essa parte para ver a HITBOX do jogador
        if (viewHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public boolean getNoChao(){
        return noChao;
    }

    public void setNoChao(boolean noChao){
        this.noChao = noChao;
    }

    public double getGRAVIDADE(){
        return gravidade;
    }

    public int getFORCA_PULO() {
        return forcaPulo;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosY(int posY){
        if (!isGameOver){
            this.posY = posY;
            this.hitbox.setLocation(hitbox.x, posY+ altura /2);
        }
    }
}

