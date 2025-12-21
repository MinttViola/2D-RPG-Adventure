package Service;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Util.WorkWithFilesUtil;

public class SoundService {
	
	private WorkWithFilesUtil fileUtil = new WorkWithFilesUtil();
	Clip clip;
	String path = "Sounds/";
	URL soundURL[] = new URL[30];
	/*public SoundService(){
		soundURL[1] = fileUtil.get("Sounds/SFX/chest.wav");
		soundURL[2] = fileUtil.get("Sounds/SFX/crystal.wav");
		soundURL[3] = fileUtil.get("Sounds/SFX/key.wav");
	}*/

	public void playBackgroundMusic(int level){
		String thisPath = path+"Level "+level+" Theme.wav";
		URL backgroundSound = fileUtil.get(thisPath);
		SetFile(backgroundSound);
		play();
		loop();
	}

	public void playSE(String name){
		String thisPath = path+"SFX/"+name+".wav";
		URL seSound = fileUtil.get(thisPath);
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
