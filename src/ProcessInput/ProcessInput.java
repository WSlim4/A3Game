package ProcessInput;

import Player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ProcessInput implements KeyListener {

    Player player;

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
        // Tecla "D"
        if (tecla == KeyEvent.VK_D){
            player.setAnimacao("run");
        }
        if (tecla == KeyEvent.VK_W){
            player.setAnimacao("death");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        // Tecla "D"
        if (tecla == KeyEvent.VK_D){
            player.setAnimacao("idle");
        }
    }

}
