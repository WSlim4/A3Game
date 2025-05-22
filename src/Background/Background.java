package Background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class Background  {

    private BufferedImage[] backgroundLayer = new BufferedImage[6];
    private BufferedImage[] chao = new BufferedImage[2];
    private int x;



    public Background() {
        // Carregar Background Asset
        try {
            backgroundLayer[0] = ImageIO.read(getClass().getResource("/resource/background/Day_1/1.png"));
            backgroundLayer[1] = ImageIO.read(getClass().getResource("/resource/background/Day_1/2.png"));
            backgroundLayer[2] = ImageIO.read(getClass().getResource("/resource/background/Day_1/3.png"));
            backgroundLayer[3] = ImageIO.read(getClass().getResource("/resource/background/Day_1/4.png"));
            chao[0] = ImageIO.read(getClass().getResource("/resource/floor/Sliced/grass.png"));
            chao[1] = ImageIO.read(getClass().getResource("/resource/floor/Sliced/Tile_14.png"));
            System.out.println("Leu com sucesso");
        } catch (IOException e){
            System.err.println("Não leu um ou mais asset");
            e.printStackTrace();
        }
    }
    // Desenha o background na tela, carregado no GamePainel.java
    public void Renderizar(Graphics g){
            g.drawImage(backgroundLayer[0], 0, -50,1280, 720,  null);
            g.drawImage(backgroundLayer[1], 0, -50, 1280, 720, null);
            g.drawImage(backgroundLayer[2], 0, -50,1280, 720,  null);
            g.drawImage(backgroundLayer[3], 0, -50, 1280, 720,  null);
            for (int i = 0; i < 1280; i++) {
                x = i*32;
                g.drawImage(chao[0], x, 548, 32, 32, null);
                g.drawImage(chao[1], x, 580, 32, 32, null);
                g.drawImage(chao[1], x, 612, 32, 32, null);
                g.drawImage(chao[1], x, 644, 32, 32, null);
                g.drawImage(chao[1], x, 676, 32, 32, null);
                g.drawImage(chao[1], x, 708, 32, 32, null);

            }
    }
}
