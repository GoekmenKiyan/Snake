package org.example;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    /**
     * https://www.youtube.com/watch?v=nUHh_J2Acy8
     * This Class was entirely created using this Youtube Video
     */

    Clip clip;
    URL soundURL[] = new URL[10];

    /**
     * Creating an Array with all the Soundfiles
     */
    public Sound() {

        soundURL[0] = getClass().getResource("/Sounds/audios_eat.wav");
        soundURL[1] = getClass().getResource("/Sounds/audios_died.wav");
        soundURL[2] = getClass().getResource("/Sounds/8bitsoundtrack.wav");
    }

    /**
     * Selecting a Sound from the Array
     */
    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.0f);

        } catch (Exception e) {

        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }
}
