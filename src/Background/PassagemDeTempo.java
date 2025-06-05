package Background;

import java.awt.*;

public class PassagemDeTempo {


    private final Day dia = new Day();
    private final FimDeTarde tarde = new FimDeTarde();
    private final Noite noite = new Noite();
    private final Amanhecer amanhecer = new Amanhecer();

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
    }
}
