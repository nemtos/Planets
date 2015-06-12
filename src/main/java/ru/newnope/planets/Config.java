package ru.newnope.planets;

import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class Config {
	
	public static boolean vSync = true;
	public static int fpsLimit = 0;
	public static String resDir = "resources";
	
	public static void readFromArgs(String[] args) {
		OptionParser op = new OptionParser();
		op.allowsUnrecognizedOptions();
		ArgumentAcceptingOptionSpec<Integer> argFps = op.accepts("fpsLimit").withOptionalArg().ofType(Integer.class);
		ArgumentAcceptingOptionSpec<String> argResDir = op.accepts("resDir").withOptionalArg().ofType(String.class);
		OptionSet os = op.parse(args);
		if(os.has(argFps)){
			vSync = false;
			fpsLimit = os.valueOf(argFps);
		}
		if(os.has(argResDir))
			resDir = os.valueOf(argResDir);
	}
	
}
