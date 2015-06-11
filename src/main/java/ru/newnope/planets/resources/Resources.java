package ru.newnope.planets.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.ImageIOImageData;

import ru.newnope.planets.Config;

public class Resources {
	
	private static final File assetsDir = new File(Config.resDir);
	
	public static InputStream getStream(String name) throws Exception {
		InputStream stream = Resources.class.getResourceAsStream(name);
		if(stream==null)
			stream = new FileInputStream(new File(assetsDir, name));
		return stream;
	}
	
	public static BufferedImage getImage(String name) throws Exception {
		return ImageIO.read(getStream(name));
	}
	
	public static ByteBuffer getImageBuf(String name) throws Exception {
		return new ImageIOImageData().imageToByteBuffer(getImage(name), false, false, null);
	}
	
}
