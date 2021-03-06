package ru.newnope.planets.physics;

import ru.newnope.planets.Planets;
import ru.newnope.planets.render.Coord3D;
import ru.newnope.planets.solarsystem.SpaceObject;

public class RotationPhysicsProvider extends PhysicsProvider{

	public final SpaceObject center;
	public final float radius, speed, speedSelf;
	private Coord3D coord = new Coord3D();
	private float angle = 0;
	public float tilt = 0;
	
	public RotationPhysicsProvider(SpaceObject center, float radius, float speed, float speedSelf) {
		this.center = center;
		this.radius = radius;
		this.speed = speed;
		this.speedSelf = speedSelf;
	}
	
	@Override
	public Coord3D updatePosition() {
		if(!Planets.pausePhys){
			angle+=speed;
			coord.x = center.posX + (float)Math.cos(angle)*radius;
			coord.y = center.posY + (float)Math.sin(angle)*radius;
			coord.z = center.posZ + (float)Math.cos(angle)*radius*tilt;
			coord.rot+=speedSelf;
		}
		return coord;
	}

}
