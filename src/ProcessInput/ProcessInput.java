package ProcessInput;

import Player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

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
        // Tecla "ESPAÇO"
        if (tecla == KeyEvent.VK_SPACE && player.getNoChao()){
            try {
                player.setAnimacao("idle");
                if (tecla == KeyEvent.VK_SPACE && player.getNoChao()) {
                    player.setAnimacao("idle");
                    playJumpSound();
                    player.setNoChao(false);
                }
            }catch (NullPointerException n){
                System.out.println("Problema inesperado ao processar pulo: " + n);
            }
        }
    }

    public void playJumpSound() {
        try {
            AudioInputStream soundPulo = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/resource/audio/effects/pulo.wav")));
            Clip tempClip = AudioSystem.getClip();
            tempClip.open(soundPulo);
            tempClip.start();
        } catch (Exception e) {
            System.out.println("Erro ao reproduzir som do pulo: " + e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }




}
