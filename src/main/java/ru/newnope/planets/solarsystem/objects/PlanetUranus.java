package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.render.Texture;
import ru.newnope.planets.solarsystem.IHasRings;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlanetUranus extends SpaceObject implements IHasRings{

	private final Texture rings = new Texture("uranus_rings.png");
	
	private final PhysicsProvider physics;
	
	public PlanetUranus(Sun sun) {
		super("uranus.png", 1.4f);
		this.physics = new RotationPhysicsProvider(sun, 55, .0035f, -5f);
	}

	@Override
	public Texture getRingsTexture() {
		return rings;
	}

	@Override
	public float getRingsSize() {
		return 8f;
	}

	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
}
