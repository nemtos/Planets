package ru.newnope.planets.physics;

import ru.newnope.planets.Planets;
import ru.newnope.planets.solarsystem.SolarSystem;

public class PhysicsThread extends Thread {
	
	public boolean error = false;
	private final SolarSystem ss;
	
	public PhysicsThread(SolarSystem ss) {
		super("Physics");
		this.ss = ss;
	}
	
	@Override
	public void run() {
		try{
			long utime = System.currentTimeMillis();
			short ups = 0;
			while(true){
				long time = System.currentTimeMillis();
				if(Planets.working){
					ss.updatePhysics();
					Planets.physTicked = true;
					if (System.currentTimeMillis() - utime >= 1000) {
						utime = System.currentTimeMillis();
						Planets.ups = ups;
						ups = 0;
					}else ups++;
				}
				long sleep = time + 49 - System.currentTimeMillis();
				if(sleep>0)
					Thread.sleep(sleep);
				else
					System.out.println(String.format("Physics is updating too slow. Missed %d ms", -sleep));
			}
		}catch(Throwable th){
			error = true;
			th.printStackTrace();
		}
	}
	
}
