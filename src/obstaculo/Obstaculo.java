package obstaculo;

import java.awt.*;

public class Obstaculo {
    protected int upscaling = 3; //Define em quantas vezes o obstáculo será maior

    protected int width; // Largura do sprite
    protected int height; // Altura do Sprite
    protected int x; // Posição horizontal do sprite
    protected int y; // Posição vertical do sprite
    protected boolean verHitbox = false; // Ative para visualizar a hitbox de todos os obstáculos
    protected int velocidadeContador = 0; // Aumenta a velocidade de movimento

    // Variável para a hitbox
    protected Rectangle hitbox = new Rectangle(); // Cria a hitbox do obstáculo

    public Obstaculo(){

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

        hitbox.setLocation(x + (width * upscaling - hitbox.width) / 2, y + (height * upscaling - hitbox.height) / 2);
    }

    // Renderiza na tela
    public void Renderizar(Graphics g) {
        // Defina "verHitbox" para true para renderizar as hitbox
        if (verHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    // Métodos set e get
    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX(){
        return x;
    }

    public int getWidth(){
        return width*upscaling;
    }
}