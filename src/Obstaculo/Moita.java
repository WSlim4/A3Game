package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Moita extends Obstaculo {
    private BufferedImage sprite;

    public Moita() {
        super(1280, 548, 27, 14, 3);
        try {
            sprite = ImageIO.read(getClass().getResource("/resource/objects/Sliced/moita.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {
        super.move();  // chama o move() do Obstaculo, que atualiza a posição do x
        System.out.println(width + " " + height);
    }

    @Override
    public void Renderizar(Graphics g) {
        g.drawImage(sprite, x, y, 27 * upscaling, 14 * upscaling, null);

        //Permite renderizar a hitbox do obstáculo
        if (verHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }
}