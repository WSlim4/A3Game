package Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Day  {

    private final BufferedImage[] backgroundLayer = new BufferedImage[3];

    public Day() {
        // Carregar Background Asset
        try {
            backgroundLayer[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Day/1.png")));
            backgroundLayer[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Day/2.png")));
            backgroundLayer[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Day/4.png")));
            System.out.println("Leu os assets com sucesso - Day");
        } catch (IOException e){
            System.err.println("Não leu um ou mais asset - Day");
        }
    }
    // Desenha o background na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g, int movimento){
            g.drawImage(backgroundLayer[0], 0, -50,1280, 720,  null);
            g.drawImage(backgroundLayer[1], movimento, -50,1280,720, null);
            g.drawImage(backgroundLayer[1], movimento + 1280, -50, 1280, 720, null );
            g.drawImage(backgroundLayer[2], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[2], movimento + 1280, -50, 1280, 720, null);
    }
}
