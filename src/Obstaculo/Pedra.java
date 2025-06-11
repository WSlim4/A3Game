package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Pedra extends Obstaculo{
    private BufferedImage sprite;

    public Pedra() {
        super(1280, 548, 10, 7, 5);
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Sliced/pedra.png")));
        } catch (IOException e) {
            e.fillInStackTrace();
            throw new RuntimeException();
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
