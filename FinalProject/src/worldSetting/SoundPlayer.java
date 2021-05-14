package worldSetting;

import jay.jaysound.JayLayer;

public class SoundPlayer{

	//gets pissed if initialize in construtor, not sure why
	public static void playShutterSound() {
		String[] soundEffects = new String[]{"shutter-click.mp3"};
		JayLayer soundTest = new JayLayer("audio/","audio/",false);
		soundTest.addSoundEffects(soundEffects);
		soundTest.playSoundEffect(0);
	}
	
	//gets pissed if initialize in construtor, not sure why
		public static void playSeaSound() {
			String[] soundEffects = new String[]{"sea-sound.mp3"};
			JayLayer soundTest = new JayLayer("audio/","audio/",false);
			soundTest.addSoundEffects(soundEffects);
			soundTest.playSoundEffect(0);
		}
}

