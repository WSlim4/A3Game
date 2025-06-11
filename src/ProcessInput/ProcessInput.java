package ProcessInput;

import Player.Player;
import Update.Update;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ProcessInput implements KeyListener {

    Player player;
    Update update;

    public ProcessInput(Player player) {
        this.player = player;
    }

    public void read() {
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        // Tecla "ESPAÇO"
        if (tecla == KeyEvent.VK_SPACE && player.getNoChao()){
            try {
                player.setAnimacao("idle");
                player.setNoChao(false);
                int y = 0;
                update.setVelocidadeY(y);
            }catch (NullPointerException n){
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        // Tecla "D"
        if (tecla == KeyEvent.VK_D){
            player.setAnimacao("run");
        }
    }


}
