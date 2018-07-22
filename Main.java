import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
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


public class Main{

	static MusicPlayer player= new MusicPlayer();
	static int arduinoCommand = 2;
	static HashMap<String, String> songs = new HashMap();
	String[] modes = {"fear", "love", "neutral", "fun", "sad", "downNeutral", "upNeutral"};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		
	//	addActionListener(this);
		//user input: aiming for voice recognition
		System.out.print("Please enter the words that you want to say:     ");
		Scanner scan =new Scanner(System.in);
		String input = scan.nextLine();
		
		songs.put("fear", "Music/Taylor Swift - Safe & Sound.wav");
		songs.put("love", "Music/Taylor Swift - Love Story.wav");
		songs.put("fun", "Music/Franzl Lang - Auf Und Auf Voll Lebenslust.wav");
		songs.put("sad", "Music/jht.wav");
		songs.put("downNeutral", "Music/Ryan Gosling - City of Stars.wav");
		songs.put("upNeutral", "Music/hlddz.wav");
		songs.put("neutral", "Music/Bon Jovi - It's My Life.wav");
		songs.put("random", "Music/Alan Silvestri - The Avengers.wav");
		songs.put("casino", "Music/ds.wav");
		songs.put("party", "Music/LMFAO - Party Rock Anthem.wav");
		songs.put("wedding", "Music/Bruno Mars - Just the Way You Are.wav");
		songs.put("nightclub", "Music/Bassjackers - Ready (Extended Mix).wav");
		songs.put("study", "Music/lxdy.wav");
		
		//playing the music intended
		String[] in0 = input.split(" ");
		ArrayList<String> in1= new ArrayList<String>();
		for(int i= 0; i< in0.length; i++) {
			in1.add(in0[i]);
		}
		
		if(keywordScan(in1)!=0) {
			int res= keywordScan(in1);
			System.out.println(res);
			//play pre-designated songs
			switch(res) {
			case 1:
				//du sheng
				//System.out.println(songs.get("casino"));
				player.christmas=MusicPlayer.loadSound(songs.get("casino"));
			//	player.play();
				break;
			case 2:
				player.christmas=MusicPlayer.loadSound(songs.get("party"));
				//party rock anthem
				break;
			case 3:
				player.christmas=MusicPlayer.loadSound(songs.get("wedding"));
				//love story
				break;
			case 4:
				//READY!(Original Mix)
				player.christmas=MusicPlayer.loadSound(songs.get("nightclub"));
				break;
			case 5:
				player.christmas=MusicPlayer.loadSound(songs.get("study"));
				//liu xing de yun
				break;
			}
		}else {
			//analyzing the input
			AnalyzeAlorithm(in1);
		}
		
		player.play();
		
		//run the thread
		SerialTest main = new SerialTest();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
		
		
	}

	
	//analyze input that falls out of the catagories
	public static void AnalyzeAlorithm(ArrayList<String> in1) {
		// TODO Auto-generated method stub
	    Random ran= new Random();
	    if(ran.nextDouble()<0.1) {
	    	//play random music:   shiki no uta
	    	player.christmas=MusicPlayer.loadSound(songs.get("random"));
	    }else {
	    	//analyze words
	    	if(in1.contains("scared")||in1.contains("terrified")) {
	    		//safe and sound
	    		player.christmas=MusicPlayer.loadSound(songs.get("fear"));
	    	}else if(in1.contains("love")||in1.contains("sweet")) {
	    		//just the way you are
	    		player.christmas=MusicPlayer.loadSound(songs.get("love"));
	    	}else if(in1.contains("funny")||in1.contains("laugh")||in1.contains("fun")) {
	    		//auf und auf voll lebenslust
	    		player.christmas=MusicPlayer.loadSound(songs.get("fun"));
	    	}else if(in1.contains("sad")||in1.contains("bad")) {
	    		//ju hua tai
	    		player.christmas=MusicPlayer.loadSound(songs.get("sad"));
	    	}else if(in1.contains("lost")||in1.contains("lonely")||in1.contains("alone")) {
	    		//city of stars
	    		player.christmas=MusicPlayer.loadSound(songs.get("downNeutral"));
	    	}else if(in1.contains("good")||in1.contains("pleasant")) {
	    		//huan le dou di zhu
	    		player.christmas=MusicPlayer.loadSound(songs.get("upNeutral"));
	    	}else {
	    		//It's my life
	    		player.christmas=MusicPlayer.loadSound(songs.get("neutral"));
	    	}
	    }
		
	}



	//check if any keywords are contained
	public static int keywordScan(ArrayList<String> in1) {
		if(in1.contains("casino")) {
			System.out.println("you have entered casino");
			return 1;
		}else if(in1.contains("party")) {
			System.out.println("you have entered party");
			return 2;
		}else if(in1.contains("wedding")) {
			System.out.println("you have entered wedding");
			return 3;
		}else if(in1.contains("nightclub")) {
			System.out.println("you have entered nightclub");
			return 4;
		}else if(in1.contains("study")) {
			System.out.println("you have entered study");
			return 5;
		}
		return 0;
	}


	

}
