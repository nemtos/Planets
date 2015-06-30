package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlaneMercury extends SpaceObject{
	
	private final PhysicsProvider physics;
	
	public PlaneMercury(Sun sun) {
		super("mercury.png", .6f);
		this.physics = new RotationPhysicsProvider(sun, 10, .04f, -5f);
	}
	
	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
}
