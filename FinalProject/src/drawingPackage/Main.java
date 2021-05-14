package drawingPackage;
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
		DrawingSurface drawing = new DrawingSurface();
		//PApplet.main("Main"); 
		PApplet.runSketch(new String[]{""}, drawing);
		
		//for messing with window or canvas
		//ie add swing
		//dont use this for 3D
		
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
