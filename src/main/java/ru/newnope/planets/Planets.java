package ru.newnope.planets;

import java.nio.ByteBuffer;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ru.newnope.planets.physics.PhysicsException;
import ru.newnope.planets.resources.Resources;
import ru.newnope.planets.solarsystem.SolarSystem;

public class Planets {

	private static boolean shouldExit = false;
	public static boolean working = false;
	public static short ups = 20;
	
	public static void main(String args[]) {
		try{
			Config.readFromArgs(args);
			SolarSystem ss = new SolarSystem();
			Display.setTitle("Planets");
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setResizable(true);
			Display.setIcon(new ByteBuffer[] { Resources.getImageBuf("icon-16.png"), Resources.getImageBuf("icon-32.png") });
			Display.create();
			if(Config.vSync)
				Display.setVSyncEnabled(true);
			long time = System.currentTimeMillis(); //For FPS counter
			int fps = 0;
			float framePart = 0;
			ss.init(); //Initialize all before start drawing
			while(!(Display.isCloseRequested() || shouldExit)){
				working = Display.isVisible();
				if(working){ //Draw only if display visible
					handleKeyboard();
					ss.render(framePart);
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

	private static void handleKeyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			shouldExit = true;
		}
	}

}
