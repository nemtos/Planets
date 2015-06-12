package ru.newnope.planets.solarsystem;

import java.util.ArrayList;
import java.util.List;

import ru.newnope.planets.physics.PhysicsThread;

public class SolarSystem {
	
	public final List<SpaceObject> objects = new ArrayList<>();
	public final PhysicsThread physics = new PhysicsThread(this);
	
	public SolarSystem() {
		
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
