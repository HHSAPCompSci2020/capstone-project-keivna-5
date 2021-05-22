package worldSetting;

import jay.jaysound.JayLayer;

/**
 * Contains two methods to play each sound
 * @author elise
 */
public class SoundPlayer{

	/**
	 * Plays a shutter click sound
	 */
	public static void playShutterSound() {
		String[] soundEffects = new String[]{"shutter-click.mp3"};
		JayLayer soundTest = new JayLayer("media/","media/",false);
		soundTest.addSoundEffects(soundEffects);
		soundTest.playSoundEffect(0);
	}

	/**
	 * Plays a background sea sound with waves crashing
	 */
	public static void playSeaSound() {
		String[] soundEffects = new String[]{"sea-sound.mp3"};
		JayLayer soundTest = new JayLayer("media/","media/",false);
		soundTest.addSoundEffects(soundEffects); //length is 4:19
		soundTest.playSoundEffect(0);
	}
	
	/**
	 * Plays a car sound, rushing by, no traffic involved
	 */
	public static void playCarSound() {
		String[] soundEffects = new String[]{"car-passing.mp3"};
		JayLayer soundTest = new JayLayer("media/","media/",false);
		soundTest.addSoundEffects(soundEffects); //length is 0:14
		soundTest.playSoundEffect(0);
	}
}

