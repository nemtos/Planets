package ru.newnope.planets.solarsystem;

import java.util.ArrayList;
import java.util.List;

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
		float rot = prevRot + (prevRot - this.rot) * framePart;
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(rot, 0, 0, 1);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		for(int list : glLists)
			GL11.glCallList(list);
		GL11.glPopMatrix();
	}
	
}
