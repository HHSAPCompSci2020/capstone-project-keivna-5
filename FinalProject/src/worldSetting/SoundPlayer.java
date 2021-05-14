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
		JayLayer soundTest = new JayLayer("audio/","audio/",false);
		soundTest.addSoundEffects(soundEffects);
		soundTest.playSoundEffect(0);
	}

	//length is 4:19
	/**
	 * plays a background sea sound with waves crashing
	 */
	public static void playSeaSound() {
		String[] soundEffects = new String[]{"sea-sound.mp3"};
		JayLayer soundTest = new JayLayer("audio/","audio/",false);
		soundTest.addSoundEffects(soundEffects);
		soundTest.playSoundEffect(0);
	}
}

