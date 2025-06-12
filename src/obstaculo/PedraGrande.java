package obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PedraGrande extends Obstaculo{
    private BufferedImage sprite;

    public PedraGrande() {
        super(1280, 548, 19, 10, 5, 14,8);
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/pedra_grande.png")));
        } catch (IOException e) {
            System.out.println("Erro ao carregar Pedra Grande" + e);
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
