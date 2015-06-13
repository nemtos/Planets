package ru.newnope.planets.render;

public class Coord3D {
	
	public float x, y, z, rot;
	
	public Coord3D(float x, float y, float z, float rot) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.rot = rot;
	}
	
	public Coord3D(){ 
		this(0, 0, 0, 0);
	}
}
