package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Obstaculo {
    protected int upscaling = 3; //Define em quantas vezes o obstáculo será maior

    protected int width = 27; // Largura do sprite
    protected int height = 14; // Altura do Sprite
    protected int x = 800; // Posição horizontal do primeiro sprite
    protected int y = 548-height*upscaling; // Posição vertical do primeiro sprite
    protected boolean verHitbox = false; // Ative para visualizar a hitbox de todos os obstáculos
    private BufferedImage rockImage; // Carregar o primeiro obstáculo
    protected int velocidadeContador = 0; // Aumenta a velocidade de movimento
    private final String OBSTACULO = "moita"; // Sprite do primeiro obstáculo

    // Variável para a hitbox
    protected Rectangle hitbox = new Rectangle(x, (y-height*upscaling), width * upscaling, height * upscaling); // Cria a hitbox do obstáculo

    // Construtor com primeiro obstáculo
    public Obstaculo() {

        try {
            rockImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Sliced/" + OBSTACULO + ".png")));
        } catch (IOException e) {
            e.fillInStackTrace();
            throw new RuntimeException();
        }

    }

    // Construtor com hitbox do tamanho da sprite
    public Obstaculo(int x, int y, int width, int height, int upscaling) {
        this.x = x;
        this.y = (y-height*upscaling);
        this.width = width;
        this.height = height;
        this.upscaling = upscaling;
        this.hitbox = new Rectangle(x, (y-height*upscaling), width * upscaling, height * upscaling);
    }

    // Construtor com hitbox personalizado
    public Obstaculo(int x, int y, int width, int height, int upscaling, int hitWidth, int hitHeight) {
        this.x = x;
        this.y = (y-height*upscaling);
        this.width = width;
        this.height = height;
        this.upscaling = upscaling;
        this.hitbox = new Rectangle(x, y+(height*2), hitWidth * upscaling, hitHeight * upscaling);
    }

    // Movimento horizontal e gradual do obstáculo
    public void move() {
        velocidadeContador++;
        if (velocidadeContador >= 8) {
            this.x -= 1;
            velocidadeContador = 0;
        }

        //if (x < -width * upscaling) {
        //    this = null;
        //}
        hitbox.setLocation(x + (width * upscaling - hitbox.width) / 2, y + (height * upscaling - hitbox.height) / 2);
    }

    // Renderiza na tela
    public void Renderizar(Graphics g) {
        //System.out.println("Obstaculo" + x);
        g.drawImage(rockImage, x, y, width * upscaling, height * upscaling, null);


        // Descomente essa parte para ver a hitbox
        if (verHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            System.out.println(hitbox.y+" "+ hitbox.width+ " "+ hitbox.height);
        }
    }


    public Rectangle getHitbox() {
        return hitbox;
    }

    // Método para importar a intancia dos pontos

    public int getX(){
        return x;
    }

    public int getWidth(){
        return width*upscaling;
    }
}