import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JApplet;
import javax.swing.JButton;

public class MusicPlayer{
	
	public static AudioClip christmas;
	
	public MusicPlayer() {
		//AudioClip christmas = loadSound(filename);
	}
	
	public static AudioClip loadSound(String filename) {
		URL url = null;
		try {
			url = new URL("file:" + filename);
		} 
		catch (MalformedURLException e) {;}
		return JApplet.newAudioClip(url);
	}
	public static void play() {
		christmas.play();
	} 
	public static void stop() {
		christmas.stop();
	}
	
	
	
}
   