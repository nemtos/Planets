package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.render.Texture;
import ru.newnope.planets.solarsystem.IHasRings;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlanetSaturn extends SpaceObject implements IHasRings{

	private final Texture rings = new Texture("saturn_rings.png");
	
	private final PhysicsProvider physics;
	
	public PlanetSaturn(Sun sun) {
		super("saturn.png", 3f);
		this.physics = new RotationPhysicsProvider(sun, 20, .008f, -5f);
	}

	@Override
	public Texture getRingsTexture() {
		return rings;
	}

	@Override
	public float getRingsSize() {
		return 10f;
	}

	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
	
	
}
