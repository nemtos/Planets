package ru.newnope.planets;

import java.nio.ByteBuffer;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import ru.newnope.planets.resources.Resources;

public class Planets {

	public static void main(String args[]) {
		Config.readFromArgs(args);
		try{
			Display.setTitle("Planets");
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.setIcon(new ByteBuffer[] { Resources.getImageBuf("icon-16.png"), Resources.getImageBuf("icon-32.png") });
			Display.setResizable(true);
			Display.create();
			if(Config.vSync)
				Display.setVSyncEnabled(true);
			long time = System.currentTimeMillis(); //For FPS counter
			int fps = 0;
			while(!Display.isCloseRequested()){
				boolean visible = Display.isVisible();
				if(visible){ //Draw only if display visible
					
				}
				Display.update(); //Must be called even if display not visible
				if(!Config.vSync)
					Display.sync(Config.fpsLimit);
				if(visible){ //Count FPS only if display visible
					if (System.currentTimeMillis() - time >= 1000) {
						Display.setTitle(String.format("Planets (%d fps)", ++fps));
						time = System.currentTimeMillis();
						fps = 0;
					}else fps++;
				}
			}
		}catch(Throwable th){
			JOptionPane.showMessageDialog(null, th.getMessage(), "Error (stack trace in console)", 0);
			th.printStackTrace();
		}finally{
			Display.destroy();
		}
	}

}
