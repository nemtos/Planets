package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class SecondaryMoon extends SpaceObject{

	private final PhysicsProvider physics;
	
	public SecondaryMoon(PlanetEarth earth) {
		super("moon.png", .2f);
		this.physics = new RotationPhysicsProvider(earth, 2, .1f, -.1f);
		((RotationPhysicsProvider)this.physics).tilt = -0.65f;
	}

	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}

}
