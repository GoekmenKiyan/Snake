package org.example;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    /**
     * The following lines of Code have been taken from the Internet
     * https://www.youtube.com/watch?v=nUHh_J2Acy8
     * last visit: 23.01.2023
     */
    Clip clip;
    URL soundURL[] = new URL[5];

    /**
     * Creating an Array with all the Soundfiles
     */
    public Sound() {

        soundURL[0] = getClass().getResource("/Sounds/audios_eat.wav");
        soundURL[1] = getClass().getResource("/Sounds/audios_died.wav");
        soundURL[2] = getClass().getResource("/Sounds/click.wav");
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
}
