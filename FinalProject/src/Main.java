import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.newt.opengl.GLWindow;

import processing.awt.PSurfaceAWT;
import processing.core.*;
import processing.opengl.PSurfaceJOGL;

public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingSurface drawing = new DrawingSurface();
		//PApplet.main("Main"); 
		PApplet.runSketch(new String[]{""}, drawing);
//		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
//		//PSurfaceJOGL surf = (PSurfaceJOGL) drawing.getSurface();
//
//		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
////		GLWindow canvas = (GLWindow) surf.getNative();
//		
//		JFrame window = (JFrame)canvas.getFrame();
//
//		window.setSize(800, 600);
//		window.setMinimumSize(new Dimension(100,100));
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setResizable(true);
//
//		window.setVisible(true);	
//		canvas.requestFocus();
	}
}
