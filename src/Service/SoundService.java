package Service;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundService {
	
	Clip clip;
	String path = "Sounds/";
	File soundURL[] = new File[30];
	File test = new File("Sounds/MainTheme.wav");
	public SoundService(){

		soundURL[0] = new File("Sounds/MainTheme.wav");
		soundURL[1] = new File("Sounds/SFX/chest.wav");
		soundURL[2] = new File("Sounds/SFX/crystal.wav");
		soundURL[3] = new File("Sounds/SFX/key.wav");
	}

	public void playBackgroundMusic(int level){
		String thisPath = path+"Level "+level+" Theme.wav";
		File backgroundSound =  new File(thisPath);
		SetFile(backgroundSound);
		play();
		loop();
	}

	public void playSE(String name){
		String thisPath = path+"SFX/"+name+".wav";
		File seSound = new File(thisPath);
		SetFile(seSound);
		play();
	}

	public void SetFile(File url)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e){}
	}

	public void play()
	{
		clip.start();
	}
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop()
	{
		clip.stop();
	}

}
