package ru.newnope.planets.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import ru.newnope.planets.Config;

public class RenderUtils {
	
	private static Sphere sphere;
	
	public static void setupView(int width, int height) {
		GL11.glPopAttrib();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glClearDepth(1D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
	    GL11.glViewport(0, 0, width, height);
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GLU.gluPerspective(60F, (float)width / height, 0.1F, 1000F);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    GL11.glLoadIdentity();
	}
	
	public static void init(int width, int height) {
		setupView(width, height);
		sphere = new Sphere();
		sphere.setDrawStyle(GLU.GLU_FILL);
		sphere.setTextureFlag(true);
		sphere.setNormals(GLU.GLU_SMOOTH);
	}
	
	public static int prepareSphere(int texId, float size) {
		int list = GL11.glGenLists(1);
		GL11.glNewList(list, GL11.GL_COMPILE);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);
		sphere.draw(size, Config.details, Config.details);
		GL11.glEndList();
		return list;
	}
	
}
