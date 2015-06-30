package ru.newnope.planets.solarsystem;

import java.util.ArrayList;
import java.util.List;

import ru.newnope.planets.physics.PhysicsThread;
import ru.newnope.planets.solarsystem.objects.*;

public class SolarSystem {
	
	public final List<SpaceObject> objects = new ArrayList<>();
	public final PhysicsThread physics = new PhysicsThread(this);
	
	public SolarSystem() {
		objects.add(new Stars());
		Sun sun = new Sun();
		objects.add(sun);
		objects.add(new PlaneMercury(sun));
		objects.add(new PlanetVenus(sun));
		PlanetEarth earth = new PlanetEarth(sun);
		objects.add(earth);
		objects.add(new SecondaryMoon(earth));
		objects.add(new PlanetMars(sun));
		objects.add(new PlanetJupiter(sun));
		objects.add(new PlanetSaturn(sun));
		objects.add(new PlanetUranus(sun));
		objects.add(new PlanetNeptune(sun));
	}
	
	public void init() throws Exception{
		physics.start();
		for(SpaceObject obj : objects)
			obj.init();
	}
	
	public void render(float framePart) {
		for(SpaceObject obj : objects)
			obj.render(framePart);
	}
	
	public void updatePhysics() {
		for(SpaceObject obj : objects)
			obj.update();
	}
	
}
