package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obstaculo {
    private int x = 800;
    private int y = 507;
    private int width = 27;
    private int height = 14;
    private BufferedImage rockImage;
    private int velocidadeContador = 0;

    int upscaling = 3; //Define em quantas vezes o obstáculo será maior

    private Rectangle hitbox = new Rectangle(x, y, width * upscaling, height * upscaling); // Cria a hitbox do obstáculo

    public Obstaculo() {

        // Carrega a imagem da pedra
        try {
            rockImage = ImageIO.read(getClass().getResource("/resource/objects/Sliced/moita.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void move(double speed) {
        velocidadeContador++;
        if (velocidadeContador >= 8) {
            this.x -= 1;
            velocidadeContador = 0;
        }

        if (x < -width * upscaling) {
            x = 1280;
        }
        hitbox.setLocation(x, y);
    }

    public void Renderizar(Graphics g) {
        //System.out.println("Obstaculo" + x);
        g.drawImage(rockImage, x, y, width * upscaling, height * upscaling, null);

        // Descomente essa parte para ver a hitbox
        // g.setColor(Color.RED);
        // g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

}