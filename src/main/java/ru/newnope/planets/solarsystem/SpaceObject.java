package ru.newnope.planets.solarsystem;

import javafx.geometry.Point3D;
import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.render.RenderUtils;
import ru.newnope.planets.render.Texture;

public abstract class SpaceObject {
	
	public double prevPosX = 0, posX = 0, prevPosY = 0, posY = 0, prevPosZ = 0, posZ = 0;
	public final int size;
	public final Texture texture;
	public final boolean hasAtmosphere = this instanceof IHasAtmosphere;
	
	public SpaceObject(String textureName, int size) {
		this.texture = new Texture(textureName);
		this.size = size;
	}
	
	abstract PhysicsProvider getPhysics();
	
	public void update() {
		Point3D pos = getPhysics().updatePosition();
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		posX = pos.getX();
		posY = pos.getY();
		posZ = pos.getZ();
	}
	
	public void init() throws Exception{
		texture.upload();
		if(hasAtmosphere)
			((IHasAtmosphere)this).getAtmosphereTexture().upload();
	}
	
	public void render(float framePart) {
		double x = prevPosX + (prevPosX - posX) * framePart;
		double y = prevPosY + (prevPosY - posY) * framePart;
		double z = prevPosZ + (prevPosZ - posZ) * framePart;
		RenderUtils.drawSphere(texture.id, x, y, z, size);
		if(hasAtmosphere){
			IHasAtmosphere atm = (IHasAtmosphere)this;
			RenderUtils.drawSphere(atm.getAtmosphereTexture().id, x, y, z, atm.getAtmosphereSize());
		}
	}
	
}
