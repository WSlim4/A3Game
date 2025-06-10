package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MoitaGrande extends Obstaculo {
    private BufferedImage sprite;

    public MoitaGrande() {
        super(1280, 548, 39, 12, 3);
        try {
            sprite = ImageIO.read(getClass().getResource("/resource/objects/Sliced/moita_grande.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {
        super.move();  // chama o move() do Obstaculo, que atualiza a posição do x
    }

    @Override
    public void Renderizar(Graphics g) {
        g.drawImage(sprite, x, y, width * upscaling, height * upscaling, null);

        //Permite renderizar a hitbox do obstáculo
        if (verHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }
}