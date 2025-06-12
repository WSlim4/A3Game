package obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MoitaGrande extends Obstaculo {
    private BufferedImage sprite;

    public MoitaGrande() {
        super(1280, 548, 34, 12, 3, 33, 8);
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/moita_grande.png")));
        } catch (IOException e) {
            System.out.println("Erro ao carregar Moita Grande" + e);
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