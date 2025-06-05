package Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Day  {

    private final BufferedImage[] backgroundLayer = new BufferedImage[3];
    private final BufferedImage[] chao = new BufferedImage[2];

    public Day() {
        // Carregar Background Asset
        try {
            backgroundLayer[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Day/1.png")));
            backgroundLayer[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Day/2.png")));
            backgroundLayer[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Day/4.png")));
            chao[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/floor/Sliced/grass.png")));
            chao[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/floor/Sliced/Tile_14.png")));
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
            for (int i = 0; i < 1280; i++) {
                int x = i*32;
                g.drawImage(chao[0], x, 548, 32, 32, null);
                g.drawImage(chao[1], x, 580, 32, 32, null);
                g.drawImage(chao[1], x, 612, 32, 32, null);
                g.drawImage(chao[1], x, 644, 32, 32, null);
                g.drawImage(chao[1], x, 676, 32, 32, null);
                g.drawImage(chao[1], x, 708, 32, 32, null);
            }
    }
}
