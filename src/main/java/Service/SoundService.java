package Service;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import Util.WorkWithFilesUtil;

public class SoundService {
	
	private WorkWithFilesUtil fileUtil = new WorkWithFilesUtil();
	Clip clip;
	String path = "Sounds/";
	URL soundURL[] = new URL[30];
	FloatControl volume;
	float minVolume,maxVolume;

	public void playBackgroundMusic(int level){
		String thisPath = path+"Level "+level+" Theme.wav";
		URL backgroundSound = fileUtil.get(thisPath);
		SetFile(backgroundSound);
		SetVolume();
		play();
		loop();
	}

	public void playSE(String name){
		String thisPath = path+"SFX/"+name+".wav";
		URL seSound = fileUtil.get(thisPath);
		SetFile(seSound);
		SetVolume();
		play();
	}

	public void play(){clip.start();}

	public void loop(){clip.loop(Clip.LOOP_CONTINUOUSLY);}

	public void stop(){clip.stop();}

	public void muteAudio(){
		if(volume.getValue() == minVolume)
			volume.setValue(maxVolume);
		else volume.setValue(minVolume);
	}
	
	private void SetFile(URL url)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e){}
	}

	private void SetVolume(){
		volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		minVolume = volume.getMinimum();
		maxVolume = volume.getMaximum();
		volume.setValue(minVolume);
	}

}
