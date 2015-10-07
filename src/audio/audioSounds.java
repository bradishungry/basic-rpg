package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class audioSounds {
	
	private Clip clip;

	public audioSounds(String s) {
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat original = ais.getFormat();
			AudioFormat decode = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, original.getSampleRate(), 16, original.getChannels(), 
					original.getChannels() * 2, original.getSampleRate(), false);
			AudioInputStream decodedAis = AudioSystem.getAudioInputStream(decode, ais);
			clip = AudioSystem.getClip();
			clip.open(decodedAis);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if(clip == null){
			return;
		}
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop(){
		if(clip.isRunning()){
			clip.stop();
		}
	}
	
	public void close(){
		clip.stop();
		clip.close();
	}
	
}
