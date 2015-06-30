package ru.newnope.planets.solarsystem;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.render.Coord3D;
import ru.newnope.planets.render.RenderUtils;
import ru.newnope.planets.render.Texture;

public abstract class SpaceObject {
	
	public float prevPosX = 0, posX = 0, prevPosY = 0, posY = 0, prevPosZ = 0, posZ = 0, prevRot = 0, rot = 0;
	public final float size;
	public final Texture texture;
	private List<Integer> glLists = new ArrayList<>();
	public final boolean hasAtmosphere = this instanceof IHasAtmosphere;
	public boolean ignoreLight = false, renderInside = false;
	
	public SpaceObject(String textureName, float size) {
		this.texture = new Texture(textureName);
		this.size = size;
	}
	
	public abstract PhysicsProvider getPhysics();
	
	public void update() {
		Coord3D pos = getPhysics().updatePosition();
		if(pos!=null){
			prevPosX = posX;
			prevPosY = posY;
			prevPosZ = posZ;
			prevRot = rot;
			posX = pos.x;
			posY = pos.y;
			posZ = pos.z;
			rot = pos.rot;
		}
	}
	
	public void init() throws Exception{
		texture.upload();
		glLists.add(RenderUtils.prepareSphere(texture.id, size));
		if(hasAtmosphere){
			IHasAtmosphere atm = (IHasAtmosphere)this;
			Texture tex = atm.getAtmosphereTexture();
			tex.upload();
			glLists.add(RenderUtils.prepareSphere(tex.id, atm.getAtmosphereSize()));
		}
	}
	
	public void render(float framePart) {
		float x = prevPosX + (prevPosX - posX) * framePart;
		float y = prevPosY + (prevPosY - posY) * framePart;
		float z = prevPosZ + (prevPosZ - posZ) * framePart;
		FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(-x).put(-y).put(-z).put(0.0f).flip();
		float rot = prevRot + (prevRot - this.rot) * framePart;
		GL11.glPushMatrix();
		if(renderInside)
			GL11.glDisable(GL11.GL_CULL_FACE);
		if(ignoreLight)
			GL11.glDisable(GL11.GL_LIGHTING);
		else
			GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(rot, 0, 0, 1);
		for(int list : glLists)
			GL11.glCallList(list);
		if(ignoreLight)
			GL11.glEnable(GL11.GL_LIGHTING);
		if(renderInside)
			GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
}
