package worldSetting;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import jay.jaysound.JayLayer;

public class SoundPlayer{

	private static JayLayer sound;
	String shutterString = "shutter-click.mp3";

	public SoundPlayer () {

		JPanel p0 = new JPanel();
		p0.setLayout(new BoxLayout(p0,BoxLayout.Y_AXIS));
		p0.setBackground(Color.WHITE);

		//String[] soundEffects = new String[]{"shutter-click.mp3"};

		String[] songs = new String[]{"game1.mp3","game2.mp3","game3.mp3","game4.mp3","game5.mp3"};

		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
//		effects = new JComboBox<String>(soundEffects);
//		p1.add(effects);

//		play = new JButton("Play!");
//		play.addActionListener(this);
//		p1.add(play);

		p0.add(p1);
		p0.add(Box.createVerticalStrut(100));

//		JPanel p3 = new JPanel();
//		p3.setBorder(new TitledBorder("Background Music"));
//		p3.setBackground(Color.WHITE);
//		startstop = new JButton("Start");
//		startstop.addActionListener(this);
//		p3.add(startstop);
//		next = new JButton("Next");
//		next.addActionListener(this);
//		next.setEnabled(false);
//		p3.add(next);

//		p0.add(p3);

//		setBackground(Color.WHITE);

		sound=new JayLayer("audio/","audio/",false);
		//sound.addPlayList();
		//sound.addSongs(0,songs);
		//sound.addSoundEffects(soundEffects);
//		sound.changePlayList(0);
//		sound.addJayLayerListener(this);
//
//		add(p0);
		// TODO Add more customizations to the panel

	}

	public static void playShutterSound() {
		String[] soundEffects = new String[]{"shutter-click.mp3"};

		JayLayer soundTest = new JayLayer("audio/","audio/",false);
		soundTest.addSoundEffects(soundEffects);
		soundTest.playSoundEffect(0);
//		sound.playSoundEffect(0);

	}

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("action performed is being called");
		String source = arg0.getActionCommand();
		if (source.equals("Play!")) {
//			int i = effects.getSelectedIndex();
//			if (i >= 0)
//				sound.playSoundEffect(i);
		} else if (source.equals("Start") || source.equals("Next")) {
			sound.nextSong();
		} else if (source.equals("Stop")) {
			sound.stopSong();
		}

	}


//	@Override
//	public void songEnded() {
//		// TODO Auto-generated method stub
//
//	}
//
//
//	@Override
//	public void playlistEnded() {
//		// TODO Auto-generated method stub
//
//	}
//
//
//	@Override
//	public void musicStarted() {
//		startstop.setText("Stop");
//		next.setEnabled(true);
//	}
//
//
//	@Override
//	public void musicStopped() {
//		startstop.setText("Start");
//		next.setEnabled(false);
//	}

	//File shutterSound;


//
//	public void setup() {
//
//	}

//	public static void playShutterSound() {
//		File shutterSound = new File("./shutter-click.mp3");
//		//		
//		//		AudioInputStream audioIn = AudioSystem.getAudioInputStream(shutterSound.toURI().toURL());  
//		//	    Clip clip = AudioSystem.getClip();
//		//	    clip.open(audioIn);
//		//	    clip.start();
//
//		//		try{
//		//			Clip clip = AudioSystem.getClip();
//		//			clip.open(AudioSystem.getAudioInputStream(shutterSound));
//		//			clip.start();
//		//
//		//			Thread.sleep(clip.getMicrosecondLength()/1000);
//		//		}catch(Exception e)
//		//		{
//		//		}
//	}

}

