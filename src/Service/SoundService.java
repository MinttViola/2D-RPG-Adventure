package Service;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundService {
	
	Clip clip;
	String path = "/Assets/Sounds/";
	URL soundURL[] = new URL[30];
	URL test = getClass().getResource("/Assets/Sounds/MainTheme.wav");
	public SoundService(){

		soundURL[0] = getClass().getResource("/Assets/Sounds/MainTheme.wav");
		soundURL[1] = getClass().getResource("/Assets/Sounds/SFX/chest.wav");
		soundURL[2] = getClass().getResource("/Assets/Sounds/SFX/crystal.wav");
		soundURL[3] = getClass().getResource("/Assets/Sounds/SFX/key.wav");
	}

	public void playBackgroundMusic(int level){
		String thisPath = path+"Level "+level+" Theme.wav";
		URL backgroundSound = getClass().getResource(thisPath);
		SetFile(backgroundSound);
		play();
		loop();
	}

	public void playSE(String name){
		String thisPath = path+"SFX/"+name+".wav";
		URL seSound = getClass().getResource(thisPath);
		SetFile(seSound);
		play();
	}

	public void SetFile(URL url)
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
