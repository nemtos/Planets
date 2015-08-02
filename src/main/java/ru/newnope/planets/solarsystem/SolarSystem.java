package ru.newnope.planets.solarsystem;

import ru.newnope.planets.physics.PhysicsThread;
import ru.newnope.planets.solarsystem.objects.PlaneMercury;
import ru.newnope.planets.solarsystem.objects.PlanetEarth;
import ru.newnope.planets.solarsystem.objects.PlanetJupiter;
import ru.newnope.planets.solarsystem.objects.PlanetMars;
import ru.newnope.planets.solarsystem.objects.PlanetNeptune;
import ru.newnope.planets.solarsystem.objects.PlanetSaturn;
import ru.newnope.planets.solarsystem.objects.PlanetUranus;
import ru.newnope.planets.solarsystem.objects.PlanetVenus;
import ru.newnope.planets.solarsystem.objects.SecondaryMoon;
import ru.newnope.planets.solarsystem.objects.Stars;
import ru.newnope.planets.solarsystem.objects.Sun;

import java.util.ArrayList;
import java.util.List;

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
		objects.forEach(obj -> obj.render(framePart));
	}
	
	public void updatePhysics() {
		objects.forEach(SpaceObject::update);
	}
}
