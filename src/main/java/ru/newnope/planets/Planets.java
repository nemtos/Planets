package ru.newnope.planets;

import java.nio.ByteBuffer;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import ru.newnope.planets.physics.PhysicsException;
import ru.newnope.planets.render.RenderUtils;
import ru.newnope.planets.resources.Resources;
import ru.newnope.planets.solarsystem.SolarSystem;

public class Planets {

	private boolean shouldExit = false;
	public static boolean working = true;
	public static short ups = 20;
	private float distance = -50, rotX = 45, rotZ = 0;
	private int width, height;
	private int clickX = 0, clickY = 0;
	
	private Planets(){}
	
	public static void main(String[] args) {
		new Planets().start(args);
	}
	
	private void start(String[] args) {
		try{
			Config.readFromArgs(args);
			SolarSystem ss = new SolarSystem();
			Display.setTitle("Planets");
			Display.setDisplayMode(new DisplayMode(Config.width, Config.height));
			Display.setResizable(true);
			Display.setIcon(new ByteBuffer[] { Resources.getImageBuf("icon-16.png"), Resources.getImageBuf("icon-32.png") });
			Display.create(new PixelFormat(8, 8, 0, Config.samples));
			if(Config.vSync)
				Display.setVSyncEnabled(true);
			long time = System.currentTimeMillis(); //For FPS counter
			int fps = 0;
			float framePart = 0;
			width = Display.getWidth();
			height = Display.getHeight();
			RenderUtils.init(width, height);
			ss.init();
			Keyboard.create();
			Mouse.create();
			while(!(Display.isCloseRequested() || shouldExit)){
				working = Display.isVisible();
				if(working){ //Draw only if display visible
					checkSize();
					handleKeyboard();
					handleMouse();
					GL11.glPushMatrix();
					moveCamera();
					ss.render(framePart);
					GL11.glPopMatrix();
				}
				Display.update(); //Must be called even if display not visible
				if(!Config.vSync)
					Display.sync(Config.fpsLimit);
				if(working){ //Count FPS only if display visible
					long delta = System.currentTimeMillis() - time;
					if (delta >= 1000) {
						Display.setTitle(String.format("Planets (%d fps, %d ups)", ++fps, ups));
						time = System.currentTimeMillis();
						fps = 0;
					}else{
						framePart = delta/1000F;
						fps++;
					}
				}
				if(ss.physics.error)
					throw new PhysicsException();
			}
		}catch(Throwable th){
			JOptionPane.showMessageDialog(null, th.getMessage(), "Error (stack trace in console)", 0);
			if(!(th instanceof PhysicsException))
				th.printStackTrace();
		}finally{
			if(Display.isCreated())
				Display.destroy();
			System.exit(0);
		}
	}
	
	private void checkSize() {
		if(!(width==Display.getWidth() && height==Display.getHeight())){
			width = Display.getWidth();
			height = Display.getHeight();
			RenderUtils.setupView(width, height);
		}
	}
	
	private void moveCamera() {
		GL11.glTranslatef(0, 0, distance);
	    GL11.glRotatef(rotX, -1, 0, 0);
	    GL11.glRotatef(rotZ, 0, 0, -1);
	}
	
	private void handleKeyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			shouldExit = true;
		}
	}
	
	private void handleMouse() {
		int dWheel = Mouse.getDWheel();
        if(dWheel!=0){
            if(dWheel>0)
        		distance++;
            else
            	distance--;
        }
        if(Mouse.isButtonDown(0)){
        	rotX += (clickY - Mouse.getY())/4f;
        	rotZ += (clickX - Mouse.getX())/4f;
        }else if(Mouse.isButtonDown(1)){
        	distance += (clickY - Mouse.getY())/4f;
        }
    	clickX = Mouse.getX();
    	clickY = Mouse.getY();
    	if(distance>-10)
    		distance = -10;
    	else if(distance<-100)
    		distance = -100;
	}

}
