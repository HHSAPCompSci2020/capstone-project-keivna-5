package worldSetting;

import processing.core.PApplet;

/**
 * Got from GitHub, hopefully look like really cool moving water
 * @author BenTommyE
 *
 */
public class MovingWater {

	private float[][] particle;
	private float[][] particlesSpeed;

	private float[][] particlesNew;
	private float[][] particlesSpeedNew;

//	private float particelGain = (float) 0.00999;
//	private float forceGain = (float) 0.999;  // Speed

	private int funberOfPArticles;
	private float xStart, yStart, zStart;
//	private float size;
//
//	private float CameraMovments = 0;
//	private int CameraMovInt = 0;

	private float zoom = 10;
	private float offset = 0;

	private float waveOrNot = -5;

	public MovingWater (float xPos, float yPos, float zPos, float size) {
		xStart = xPos;
		yStart = yPos;
		zStart = zPos;
		funberOfPArticles = (int) size;
		
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
		g.translate(xStart, yStart, zStart);
		g.noFill();
		g.stroke(15, 50, 70); // same blue as the other water
		g.rotateX(g.PI/2);
		updateMesh();
		drawCoorinats(g);
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

	      //force1 = constrain(force1, -1, 1);

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
	    offset += .1;
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

	private void drawCoorinats(PApplet g) {
	  g.strokeWeight(3);
//	  g.stroke(255, 0, 0);
	  g.line(0, 0, 0, 100, 0, 0);

//	  g.stroke(0, 255, 0);
	  g.line(0, 0, 0, 0, 100, 0);
	}

	private void drawMesh(PApplet g) {
//	  g.noStroke();
//	  g.fill(200);
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
