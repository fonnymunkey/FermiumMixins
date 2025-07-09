package fermiummixins.wrapper;

import net.minecraft.client.Minecraft;

import java.io.File;

public abstract class FancyMenuWrapper {
	
	public static File getGameDir() {
		return Minecraft.getMinecraft().gameDir;
	}
}