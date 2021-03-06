package main;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable{
	GameState gs;
	/**
	 * Creates a Sound object
	 * @param url
	 * The relative path of the sound that should be played
	 */
	Sound(GameState gs){
		this.gs = gs;
	}
	@Override
	public void run() {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(GameState.class.getResource("/res/sound/m.wav"));
			AudioFormat format = audioStream.getFormat();
	   		DataLine.Info info = new DataLine.Info(Clip.class, format);
	   		Clip clip = (Clip)AudioSystem.getLine(info);
	   		
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
