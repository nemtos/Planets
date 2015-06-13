package ru.newnope.planets.physics;

import ru.newnope.planets.render.Coord3D;

public class DummyPhysicsProvider extends PhysicsProvider{

	public static DummyPhysicsProvider instance = new DummyPhysicsProvider();
	
	@Override
	public Coord3D updatePosition() {
		return null;
	}
	
}
