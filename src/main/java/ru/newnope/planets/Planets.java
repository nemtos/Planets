package ru.newnope.planets;

import org.lwjgl.opengl.Display;

public class Planets {

	public static void main(String args[]) {
		try {
			Display.setTitle("Hello World");
			Display.create();
			while (!Display.isCloseRequested()) {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Display.destroy();
		}
	}

}
