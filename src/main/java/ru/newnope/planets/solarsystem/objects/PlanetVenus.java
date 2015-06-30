package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.render.Texture;
import ru.newnope.planets.solarsystem.IHasAtmosphere;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlanetVenus extends SpaceObject implements IHasAtmosphere{

private final Texture clouds = new Texture("venus_atm.png");
	
	private final PhysicsProvider physics;
	
	public PlanetVenus(Sun sun) {
		super("venus.png", 1f);
		this.physics = new RotationPhysicsProvider(sun, 14, .041f, -5f);
	}

	@Override
	public Texture getAtmosphereTexture() {
		return clouds;
	}

	@Override
	public float getAtmosphereSize() {
		return 1.08f;
	}

	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
}
