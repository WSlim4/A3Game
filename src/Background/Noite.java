package Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Noite {

    private final BufferedImage[] backgroundLayer = new BufferedImage[6];
    private final BufferedImage[] chao = new BufferedImage[2];

    public Noite() {
        // Carregar Background Asset
        try {
            backgroundLayer[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Noite/1.png")));
            backgroundLayer[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Noite/2.png")));
            backgroundLayer[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Noite/3.png")));
            backgroundLayer[3] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Noite/4.png")));
            backgroundLayer[4] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Noite/5.png")));
            backgroundLayer[5] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/background/Noite/6.png")));
            chao[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/floor/Sliced/grass.png")));
            chao[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/floor/Sliced/Tile_14.png")));
            System.out.println("Leu os assets com sucesso - Noite");
        } catch (IOException e){
            System.err.println("Não leu um ou mais asset - Noite");
        }
    }
    // Desenha o background na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g, int movimento, int lua){
            g.drawImage(backgroundLayer[0], 0, -50,1280, 720,  null);
            g.drawImage(backgroundLayer[1], lua + 1330, -50, 1280, 720, null );
            g.drawImage(backgroundLayer[2], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[2], movimento + 1280, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[3], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[3], movimento + 1280, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[4], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[4], movimento + 1280, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[5], movimento, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[5], movimento + 1280, -50, 1280, 720, null);
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
