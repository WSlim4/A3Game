package Update;

import Obstaculo.Obstaculo;
import Player.Player;
import Effects.Dust;

public class Update {
    private Player player;
    private Obstaculo obstaculo;

    private boolean colidiu = false;

    public void update() {
        if (!colidiu){
            if (player.getHitbox().intersects(obstaculo.getHitbox())) {
                System.out.println("Colidiu!");
                player.gameOver(true);
                colidiu = true;
            }
        }

    }

    public Update(Player player, Obstaculo obstaculo){
        this.player = player;
        this.obstaculo = obstaculo;
    }
}