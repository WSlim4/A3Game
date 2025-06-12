package background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PassagemDeTempo {


    private final Day dia = new Day();
    private final FimDeTarde tarde = new FimDeTarde();
    private final Noite noite = new Noite();
    private final Amanhecer amanhecer = new Amanhecer();
    private BufferedImage chao;

    public PassagemDeTempo() {
        try {
            chao = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/floor/grass.png")));
        } catch (IOException e) {
            System.err.println("Erro - Chao");
        }
    }

    public void Renderizar (Graphics g, int movimento, int cicloDia, int lua){
        if (cicloDia > -1 && cicloDia < 700){
            dia.Renderizar(g, movimento);
        } else if (cicloDia > 699 && cicloDia < 1000) {
            tarde.Renderizar(g, movimento);
        } else if (cicloDia > 999 && cicloDia < 1700){
            noite.Renderizar(g, movimento, lua);
        } else if (cicloDia > 1699) {
            amanhecer.Renderizar(g, movimento);
        }
        g.drawImage(chao, movimento, 548, 1280, 192, null);
        g.drawImage(chao, movimento + 1280, 548, 1280, 192, null);
    }
}
