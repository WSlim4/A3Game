package background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Amanhecer {

    private final BufferedImage[] backgroundLayer = new BufferedImage[4];

    public Amanhecer() {
        // Carregar Background Asset
        try {
            backgroundLayer[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Amanhecer/1.png")));
            backgroundLayer[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Amanhecer/2.png")));
            backgroundLayer[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Amanhecer/3.png")));
            backgroundLayer[3] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Amanhecer/4.png")));
            System.out.println("Leu os assets com sucesso - Amanhecer");
        } catch (IOException e){
            System.err.println("Não leu um ou mais asset - Amanhecer");
        }
    }
    // Desenha o background na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g, int movimento){
            g.drawImage(backgroundLayer[0], 0, -50,1280, 720,  null);
            g.drawImage(backgroundLayer[1], movimento, -50,1280,720, null);
            g.drawImage(backgroundLayer[1], movimento + 1280, -50, 1280, 720, null );
            g.drawImage(backgroundLayer[2], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[2], movimento + 1280, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[3], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[3], movimento + 1280, -50, 1280, 720, null);
    }
}
