package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.DummyPhysicsProvider;
import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class Sun extends SpaceObject {
	
	public Sun() {
		super("sun.png", 5F);
		this.ignoreLight = true;
	}

	@Override
	public PhysicsProvider getPhysics() {
		return DummyPhysicsProvider.instance;
	}
	
}
