package Menu;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SomUtils {
    private static Clip musicaFundo;

    // Toca efeitos sonoros uma vez (ex: clique)
    public static void tocarSom(String caminhoSom) {
        try {
            URL url = SomUtils.class.getResource(caminhoSom);
            if (url == null) {
                System.err.println("Arquivo de som não encontrado: " + caminhoSom);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Toca a música de fundo em loop (ex: no menu)
    public static void tocarMusicaMenu(String caminhoMusica) {
        try {
            URL url = SomUtils.class.getResource(caminhoMusica);
            if (url == null) {
                System.err.println("Música de fundo não encontrada: " + caminhoMusica);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            musicaFundo = AudioSystem.getClip();
            musicaFundo.open(audioIn);
            musicaFundo.loop(Clip.LOOP_CONTINUOUSLY); // Loop infinito
            musicaFundo.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Parar a música de fundo (ex: ao sair do menu)
    public static void pararMusicaFundo() {
        if (musicaFundo != null && musicaFundo.isRunning()) {
            musicaFundo.stop();
            musicaFundo.close();
        }
    }
}