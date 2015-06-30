package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.DummyPhysicsProvider;
import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class Stars extends SpaceObject {

	public Stars(){
		super("stars.png", 101F);
		renderInside = true;
	}

	@Override
	public PhysicsProvider getPhysics() {
		return DummyPhysicsProvider.instance;
	}

}
