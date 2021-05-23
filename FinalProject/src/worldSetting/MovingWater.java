package worldSetting;

import processing.core.PApplet;

/**
 * Got from GitHub, hopefully look like really cool moving water
 * @author BenTommyE, edited by Katia, found by Elise
 *
 */
public class MovingWater extends Element{
	
//	private int[] WATER_COLOR = new int[] {15, 50, 70}; //dark
	private int[] WATER_COLOR = new int[] {30, 100, 140}; //lighter

	private float[][] particle;
	private float[][] particlesSpeed;
	private float[][] particlesNew;
	private float[][] particlesSpeedNew;

	private int funberOfPArticles;
	private float zoom;
	private float waveOrNot;

	public MovingWater (float xPos, float yPos, float zPos, float size) {
		super(xPos, yPos, zPos, size);
		
		funberOfPArticles = (int) size;
		zoom = 10;
		waveOrNot = -5;
		
		particle = new float[funberOfPArticles][funberOfPArticles];
		particlesSpeed = new float[funberOfPArticles][funberOfPArticles];
		particlesNew = new float[funberOfPArticles][funberOfPArticles];
		particlesSpeedNew = new float[funberOfPArticles][funberOfPArticles];
	
		for (int x = 1; x<funberOfPArticles-1; x++) {
			for (int y = 1; y<funberOfPArticles-1; y++) {
				particle[x][y] = 0.0f;
				particlesNew[x][y] = 0.0f;
				particlesSpeed[x][y] = 0.0f;
				particlesSpeedNew[x][y] = 0.0f;
		    }
		}
	}
	
	public void display(PApplet g) {
		g.pushMatrix();
		g.fill(WATER_COLOR[0], WATER_COLOR[1], WATER_COLOR[2]);
		g.rotateX(g.PI/2);
		updateMesh();
		g.translate(getX(), getY(), getZ());
		drawMesh(g);
		g.popMatrix();
	}

	private void updateMesh() {
	  for (int x = 1; x<funberOfPArticles-2; x++) {
	    for (int y = 1; y<funberOfPArticles-2; y++) {
	      //under 
	      float force1 = 0.0f;
	      force1 += particle[x-1][y-1] - particle[x][y];
	      force1 += particle[x-1][y] - particle[x][y];
	      force1 += particle[x-1][y+1] - particle[x][y];
	      //over
	      force1 += particle[x+1][y-1] - particle[x][y];
	      force1 += particle[x+1][y] - particle[x][y];
	      force1 += particle[x+1][y+1] - particle[x][y];
	      //sidene
	      force1 += particle[x][y-1] - particle[x][y];
	      force1 += particle[x][y+1] - particle[x][y];

	      force1 -= particle[x][y+1] / 8;

	      particlesSpeedNew[x][y] = (float) (0.995 * particlesSpeedNew[x][y] + force1/100);

	      particlesNew[x][y] = particle[x][y] + particlesSpeedNew[x][y];
	    }
	  }

	  for (int x = 1; x<funberOfPArticles-1; x++) {
	    for (int y = 1; y<funberOfPArticles-1; y++) {
	      particle[x][y] = particlesNew[x][y];
	    }
	  }
	  if (waveOrNot != 0) {
	    int mouseX = 350;
	    int mouseY = 350;
	    int width = 1000;
	    int height = 1000;
	    int MouseXIndex = 2 + (funberOfPArticles-4) * mouseX / width;
	    int MouseYIndex = 2 + (funberOfPArticles-4) * mouseY / height;
	    particlesSpeedNew[MouseXIndex][MouseYIndex] = waveOrNot;
	    particlesSpeedNew[MouseXIndex+1][MouseYIndex+1] = waveOrNot;
	    particlesSpeedNew[MouseXIndex+1][MouseYIndex] = waveOrNot;
	    particlesSpeedNew[MouseXIndex+1][MouseYIndex-1] = waveOrNot;
	    particlesSpeedNew[MouseXIndex][MouseYIndex-1] = waveOrNot;
	    particlesSpeedNew[MouseXIndex-1][MouseYIndex+1] = waveOrNot;
	    particlesSpeedNew[MouseXIndex-1][MouseYIndex] = waveOrNot;
	    particlesSpeedNew[MouseXIndex-1][MouseYIndex-1] = waveOrNot;

	    particle[MouseXIndex][MouseYIndex] = waveOrNot*10;
	    particle[MouseXIndex+1][MouseYIndex+1] = waveOrNot*5;
	    particle[MouseXIndex+1][MouseYIndex] = waveOrNot*10;
	    particle[MouseXIndex+1][MouseYIndex-1] = waveOrNot*5;
	    particle[MouseXIndex][MouseYIndex-1] = waveOrNot*10;
	    particle[MouseXIndex-1][MouseYIndex+1] = waveOrNot*5;
	    particle[MouseXIndex-1][MouseYIndex] = waveOrNot*10;
	    particle[MouseXIndex-1][MouseYIndex-1] = waveOrNot*5;
	  }
	}

	private void drawMesh(PApplet g) {
		for (int x = 0; x<funberOfPArticles-1; x++) {
			for (int y = 0; y<funberOfPArticles-1; y++) {
				g.beginShape();
				g.vertex( (x)*zoom, (y)*zoom, particle[x][y] );
				g.vertex( (x+1)*zoom, (y)*zoom, particle[x+1][y] );
				g.vertex( (x+1)*zoom, (y+1)*zoom, particle[x+1][y+1] );
				g.vertex( (x)*zoom, (y+1)*zoom, particle[x][y+1] );
				g.endShape();
			}
		}
	}
}
