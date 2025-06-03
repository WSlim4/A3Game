package Background;

import java.awt.*;

public class PassagemDeTempo {

    private int movimento;
    private Day dia = new Day();
    private Fim_de_Tarde tarde = new Fim_de_Tarde();
    private Noite noite = new Noite();
    private Amanhecer amanhecer = new Amanhecer();

    public void Renderizar (Graphics g, int movimento, int cicloDia, int lua){
        this.movimento = movimento;

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
