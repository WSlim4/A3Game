package effects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {

    Clip clip;


    public void setVolume(float volume) { // volume entre 0.0 (mudo) e 1.0 (máximo)
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Converter para decibéis (dB)
            float dB = (float) (Math.log10(volume) * 20);
            gainControl.setValue(dB);
        }
    }


    public void play(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            setVolume(0.5f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Definir volume inicial para 50%


            clip.start();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw new RuntimeException();
        }
    }

    public void stop(){
        clip.stop();
    }

}
